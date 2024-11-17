import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConnection {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static MongoClient mongoClient = null;

    public static MongoDatabase getDatabase(String dbName) {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(CONNECTION_STRING);
        }
        return mongoClient.getDatabase(dbName);
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    public static void insertDocument(String dbName, String collectionName, Document doc) {
        MongoDatabase database = getDatabase(dbName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(doc);
        System.out.println("Document inserted");
    }

    public static Document readDocument(String dbName, String collectionName, String id) {
        MongoDatabase database = getDatabase(dbName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document document = collection.find(new Document("_id", id)).first();
        System.out.println(document);
        return collection.find(new Document("_id", id)).first();
    }

    public static void updateDocument(String dbName, String collectionName, String id, Document updatedDoc) {
        MongoDatabase database = getDatabase(dbName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.updateOne(new Document("_id", id), new Document("$set", updatedDoc));
        System.out.println("Document updated");
    }

    public static void deleteDocument(String dbName, String collectionName, String id) {
        MongoDatabase database = getDatabase(dbName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.deleteOne(new Document("_id", id));
        System.out.println("Document deleted");
    }

}