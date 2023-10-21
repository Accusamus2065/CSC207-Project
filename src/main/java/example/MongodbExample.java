package example;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongodbExample {
    public static void main(String[] args) {
        insertExample();
    }

    private static void insertExample() {
        try (MongoClient client = MongoClients.create("mongodb+srv://YaBoiMG:admin@cluster0.19bebzh.mongodb.net/?retryWrites=true&w=majority")) {
            // Access database
            MongoDatabase db = client.getDatabase("sampleDB");
            // Access collection
            MongoCollection<Document> col = db.getCollection("sampleCollection");
            // Add the doc that you want to pass on
            Document sampleDoc = new Document("id", "1").append("name", "Eric");
            // Add doc to the database
            col.insertOne(sampleDoc);
            System.out.println("Add add add");
        } catch (Exception e) {
            throw new RuntimeException("Unable to connect mongoDB", e);
        }
    }
}
