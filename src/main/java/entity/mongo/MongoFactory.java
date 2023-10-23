package entity.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.github.cdimascio.dotenv.Dotenv;

public interface MongoFactory {
    static MongoClient setUpMongoClient() {
        Dotenv dotenv = Dotenv.configure().load();

        String connectionString = dotenv.get("CONNECTION_STRING");

        ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();

        assert connectionString != null;

        MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(
                new ConnectionString(connectionString)).serverApi(serverApi).build();
        return MongoClients.create(settings);
    }
}
