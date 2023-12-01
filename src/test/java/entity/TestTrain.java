//package entity;
//
//import com.google.api.gax.core.FixedCredentialsProvider;
//import com.google.auth.oauth2.ServiceAccountCredentials;
//import com.google.cloud.dialogflow.v2.*;
//import entity.chat.Message;
//
//import java.io.FileInputStream;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//public class TestTrain {
//
//    private static final String PROJECT_ID = "your-project-id";
//    private static final String INTENT_DISPLAY_NAME = "OrderPizza";
//    private static final String ENTITY_TYPE_DISPLAY_NAME = "PizzaToppings";
//
//    public static void main(String[] args) throws Exception {
//        SessionsSettings sessionsSettings =
//                SessionsSettings.newBuilder().setCredentialsProvider(FixedCredentialsProvider.create(ServiceAccountCredentials.fromStream(new FileInputStream("path/to/your/credentials.json")))).build();
//
//        try (SessionsClient sessionsClient = SessionsClient.create(sessionsSettings)) {
//            String session = SessionName.of(PROJECT_ID, "123456").toString();
//
//            // Create a new intent
//            Intent intent = createIntent(PROJECT_ID, INTENT_DISPLAY_NAME);
//
//            // Create a new entity type
//            EntityType entityType = createEntityType(PROJECT_ID, ENTITY_TYPE_DISPLAY_NAME);
//
//            // Add training phrases to the intent
//            String[] trainingPhrases = {"My skin has bumps and they are itchy.", "I'm feeling hard to breathe", "My skin is bumpy and itchy and I can't breathe"};
//            List<Intent.TrainingPhrase> list =  new ArrayList<>();
//            for (String phrase : trainingPhrases) {
//                list.add(Intent.TrainingPhrase.newBuilder().addParts(Intent.TrainingPhrase.Part.newBuilder().setText(phrase).build()).build());
//            }
//
//
//            intent =
//                    Intent.newBuilder().setDisplayName("Allergy").
//                            addAllTrainingPhrases(list).build();
//
//            // Add responses to the intent
//            intent = Intent.Message.newBuilder().setText(Intent.Message.Text.newBuilder().addText("Sure, we can help you with that!").build();
//
//
//
//
//
//
//            // Create an entity entry with synonyms
//            EntityType.Entity entityEntry = EntityType.Entity.newBuilder()
//                    .setValue("Pepperoni")
//                    .addAllSynonyms(Arrays.asList("Pepperoni", "Pepperoni pizza", "Pizza with pepperoni"))
//                    .build();
//
//            // Add the entity entry to the entity type
//            entityType.addEntities(entityEntry);
//
//            // Build and execute the intent and entity creation requests
//            Intent updatedIntent = sessionsClient.updateIntent(intent);
//            EntityType updatedEntityType = sessionsClient.updateEntityType(entityType);
//
//            System.out.println("Intent updated: " + updatedIntent);
//            System.out.println("Entity type updated: " + updatedEntityType);
//        }
//    }
//
//    private static Intent createIntent(String projectId, String displayName) {
//        Intent intent = Intent.newBuilder().setDisplayName(displayName).build();
//        Intent intentRequest = Intent.newBuilder().setParent(AgentName.of(projectId)).setIntent(intent).build();
//        return intentRequest;
//    }
//
//    private static EntityType createEntityType(String projectId, String displayName) {
//        EntityType entityType = EntityType.newBuilder()
//                .setDisplayName(displayName)
//                .setKind(EntityType.Kind.KIND_MAP)
//                .build();
//        EntityType entityTypeRequest = EntityType.newBuilder()
//                .setParent(AgentName.of(projectId))
//                .setEntityType(entityType)
//                .build();
//        return entityTypeRequest;
//    }
//}
