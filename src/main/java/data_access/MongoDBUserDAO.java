package data_access;

import com.mongodb.MongoCredential;
import com.mongodb.client.*;
import entity.User;
import entity.UserFactory;
import org.bson.Document;
import use_case.clear_users.ClearUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MongoDBUserDAO implements LoginUserDataAccessInterface {

    public MongoDBUserDAO()
    {
        // Connecting to the MongoDB server
        MongoClient mongoClient = MongoClients.create("mongodb+srv://<jihyuk>:<jihyuk01>@cluster0.19bebzh.mongodb.net/");

        // Accessing the database
        MongoDatabase database = mongoClient.getDatabase("your_database_name");

        // Accessing the collection
        MongoCollection<Document> collection = database.getCollection("COLLECTIONNAME");

        // Creating a Map object to store the data from MongoDB
        Map<String, Object> dataMap = new HashMap<>();

        // Retrieving data from MongoDB and saving it to the Map object
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> cursor = findIterable.iterator();
        while (cursor.hasNext()) {
            Document document = cursor.next();
            for (Map.Entry<String, Object> entry : document.entrySet()) {
                dataMap.put(entry.getKey(), entry.getValue());
            }
        }

        // Printing the data stored in the Map object
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // Closing the connection
        mongoClient.close();
    }





//    private final File csvFile;
//
//    private final Map<String, Integer> headers = new LinkedHashMap<>();
//
//    private final Map<String, User> accounts = new HashMap<>();
//
//    private UserFactory userFactory;
//
//    public MongoDBUserDAO(String csvPath, UserFactory userFactory) throws IOException {
//        this.userFactory = userFactory;
//
//        csvFile = new File(csvPath);
//        headers.put("username", 0);
//        headers.put("password", 1);
//        headers.put("creation_time", 2);
//
//        if (csvFile.length() == 0) {
//            save();
//        } else {
//
//            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
//                String header = reader.readLine();
//
//                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
//                assert header.equals("username,password,creation_time");
//
//                String row;
//                while ((row = reader.readLine()) != null) {
//                    String[] col = row.split(",");
//                    String username = String.valueOf(col[headers.get("username")]);
//                    String password = String.valueOf(col[headers.get("password")]);
//                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
//                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
//                    User user = userFactory.create(username, password, ldt);
//                    accounts.put(username, user);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void save(User user) {
//        accounts.put(user.getName(), user);
//        this.save();
//    }
//
//    @Override
//    public User get(String username) {
//        return accounts.get(username);
//    }
//
//    private void save() {
//        BufferedWriter writer;
//        try {
//            writer = new BufferedWriter(new FileWriter(csvFile));
//            writer.write(String.join(",", headers.keySet()));
//            writer.newLine();
//
//            for (User user : accounts.values()) {
//                String line = String.format("%s,%s,%s",
//                        user.getName(), user.getPassword(), user.getCreationTime());
//                writer.write(line);
//                writer.newLine();
//            }
//
//            writer.close();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public StringBuilder delete() {
//        StringBuilder ans = new StringBuilder();
//        for(String k: accounts.keySet()){
//            ans.append(k);
//            ans.append("\n");
//        }
//        accounts.clear();
//        BufferedWriter writer;
//        try {
//            writer = new BufferedWriter(new FileWriter(csvFile));
//            writer.write(String.join(",", headers.keySet()));
//            writer.newLine();
//            writer.write("");
//            writer.flush();
//
//            writer.close();
//            return ans;
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    /**
//     * Return whether a user exists with username identifier.
//     * @param identifier the username to check.
//     * @return whether a user exists with username identifier
//     */
//    @Override
//    public boolean existsByName(String identifier) {
//        return accounts.containsKey(identifier);
//    }

}
