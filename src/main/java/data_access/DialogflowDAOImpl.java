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
import java.util.List;
import java.util.UUID;


public class DialogflowDAOImpl {
    private final String PROJECT_ID;
    private final String LANGUAGE_CODE;
    private final String CREDENTIALS_FILE_PATH;

    private final SessionsClient sessionsClient;
    private final ManagedChannel managedChannel;

    private final DoctorDAOImpl dao = new DoctorDAOImpl(new DoctorUserFactory());

    public DialogflowDAOImpl() throws IOException {
        // load credentials
        Dotenv dotenv = Dotenv.configure().load();
        this.PROJECT_ID = dotenv.get("PROJECT_ID");
        this.LANGUAGE_CODE = dotenv.get("LANGUAGE_CODE");
        this.CREDENTIALS_FILE_PATH = dotenv.get("CREDENTIALS_FILE_PATH");


        // Set up Google Cloud credentials
        assert CREDENTIALS_FILE_PATH != null;
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(CREDENTIALS_FILE_PATH));
        CredentialsProvider credentialsProvider = FixedCredentialsProvider.create(credentials);

        // Create a SessionSettings object
        SessionsSettings sessionSettings = SessionsSettings.newBuilder().setCredentialsProvider(credentialsProvider).build();
        // Create a SessionsClient using a managed channel
        managedChannel = ManagedChannelBuilder.forTarget("dialogflow.googleapis.com:443").build();
        sessionsClient = SessionsClient.create(sessionSettings);
    }

    public String detectIntent(String userInput) {
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
        return queryResult.getIntent().getDisplayName() + ","+ queryResult.getFulfillmentText();
    }

    public List<String> getDoctors(String intent) {
        return dao.getBySpecialty(intent);
    }

    public void close() {
        // Clean up resources
        sessionsClient.close();
        managedChannel.shutdown();
    }
}
