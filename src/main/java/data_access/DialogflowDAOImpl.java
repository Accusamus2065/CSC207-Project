package data_access;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dialogflow.v2.*;

import entity.people.DoctorUserFactory;
import io.github.cdimascio.dotenv.Dotenv;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class DialogflowDAOImpl {
    private final String PROJECT_ID;
    private final String LANGUAGE_CODE;
    private final String CREDENTIALS_FILE_PATH;

    private final String databaseName;
    private final SessionsClient sessionsClient;
    private final ManagedChannel managedChannel;
    private final CredentialsProvider provider;
    private final IntentsClient intentsClient;
    private final Dotenv dotenv;
    private final DoctorDAOImpl dao;

    public DialogflowDAOImpl(String databaseName) throws IOException {
        this.databaseName = databaseName;
        dao = new DoctorDAOImpl(new DoctorUserFactory(), databaseName);
        // load credentials
        this.dotenv = Dotenv.configure().load();
        this.PROJECT_ID = dotenv.get("PROJECT_ID");
        this.LANGUAGE_CODE = dotenv.get("LANGUAGE_CODE");
        this.CREDENTIALS_FILE_PATH = dotenv.get("CREDENTIALS_FILE_PATH");

        // Set up Google Cloud credentials
        assert CREDENTIALS_FILE_PATH != null;
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(CREDENTIALS_FILE_PATH));
        this.provider = FixedCredentialsProvider.create(credentials);

        // Create a SessionSettings object
        SessionsSettings sessionSettings = SessionsSettings.newBuilder().setCredentialsProvider(provider).build();
        // Create a SessionsClient using a managed channel
        managedChannel = ManagedChannelBuilder.forTarget("dialogflow.googleapis.com:443").build();
        sessionsClient = SessionsClient.create(sessionSettings);
        intentsClient = IntentsClient.create(IntentsSettings.newBuilder().setCredentialsProvider(provider).build());
    }


    public List<Object> detectIntent(String userInput) {

        // Build the session name
        String sessionId = UUID.randomUUID().toString();
        SessionName session = SessionName.of(PROJECT_ID, sessionId);

        // Create a TextInput object
        TextInput textInput = TextInput.newBuilder().setText(userInput).setLanguageCode(LANGUAGE_CODE).build();

        // Create a QueryInput object
        QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

        // Create a DetectIntentRequest object
        DetectIntentRequest request = DetectIntentRequest.newBuilder()
                .setSession(session.toString())
                .setQueryInput(queryInput)
                .build();

        // Call the detectIntent method
        DetectIntentResponse response = sessionsClient.detectIntent(request);

        // Get the response text from the result
        QueryResult queryResult = response.getQueryResult();

        List<Object> tuple = new ArrayList<>();
        tuple.add(queryResult.getFulfillmentText());
        tuple.add(getDocNames(queryResult.getIntent().getDisplayName()));
        return tuple;
    }

    private List<String> getDocNames(String intent) {
        return dao.getBySpecialty(intent);
    }



    public void setIntentNEntities(String intentName, List<String> phrases, List<String> messages) {
        EntityType entityType = EntityType.newBuilder()
                .setDisplayName("Allergy")
                .setKind(EntityType.Kind.KIND_MAP)
                .build();

        // (BUILDER) Create an intent with training phrases and messages
        Intent.Builder intentBuilder = Intent.newBuilder().setDisplayName(intentName);
        for (String phrase: phrases) {
            Intent.TrainingPhrase trainingPhrase = Intent.TrainingPhrase.newBuilder()
                    .addParts(Intent.TrainingPhrase.Part.newBuilder().setText(phrase).build())
                    .build();

            intentBuilder.addTrainingPhrases(trainingPhrase);
        }
        Intent intent = intentBuilder.addMessages(
                        Intent.Message.newBuilder().setText(
                                Intent.Message.Text.newBuilder().addAllText(messages).build()
                        ).build()
                )
                .build();

        // Create the intent request
        CreateIntentRequest createIntentRequest = CreateIntentRequest.newBuilder()
                .setParent(Objects.requireNonNull("projects/"+dotenv.get("PROJECT_ID")+"/agent"))
                .setIntent(intent)
                .build();

        // Create the intent
        intentsClient.createIntent(createIntentRequest);
    }


    public void close() {
        // Clean up resources
        sessionsClient.close();
        managedChannel.shutdown();
    }
}
