import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;

import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class Main {

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("vehicles");
        MongoCollection mongoCollection = mongoDatabase.getCollection("cars");

//        save(mongoCollection);
//        read(mongoCollection);
//        readByParam(mongoCollection, "Mark", "BMW");
//        readByParamAll(mongoCollection, "Mark", "BMW");
//        delete(mongoCollection, "Mark", "Fiat");
//        deleteAll(mongoCollection, "Mark", "BMW");
//        update(mongoCollection, "Model", "A1", "A2",
//                "Color", "Black");

        MongoCollection mongoCollectionBike = mongoDatabase.getCollection("bike");
        saveBike(mongoCollectionBike);

    }

    private static void saveBike(MongoCollection mongoCollectionBike) {
        Document document = new Document();
        document.put("name", "Rower Jana");

        Document documentPerson = new Document();
        documentPerson.put("Name","Jan");
        documentPerson.put("Surname","Kowalski");

        document.put("owner", documentPerson);

        mongoCollectionBike.insertOne(document);
    }

    private static void update(MongoCollection mongoCollection, String param, Object value,
                               Object newValue, String color, Object colorValue) {
        Bson eq = Filters.eq(param, value);
        Bson query = combine(set(param, newValue), set(color, colorValue));
        mongoCollection.updateOne(eq, query);
    }

    private static void deleteAll(MongoCollection mongoCollection, String param, Object value) {
        Document document = new Document();
        document.put(param, value);
        mongoCollection.deleteMany(document);
    }

    private static void delete(MongoCollection mongoCollection, String param, Object value) {
        Document document = new Document();
        document.put(param, value);
        mongoCollection.deleteOne(document);
    }

    private static void readByParamAll(MongoCollection mongoCollection, String param, Object value) {
        Document document = new Document();
        document.put(param, value);
        MongoCursor iterator = mongoCollection.find(document).iterator(); //pokazuje na której aktualnej pozycję jestem
        while (iterator.hasNext()) {
            Document next = (Document) iterator.next();
            System.out.println(next.toJson());
        }
    }

    private static void readByParam(MongoCollection mongoCollection, String param, Object value) {
        Document document = new Document();
        document.put(param, value);
        Document first = (Document) mongoCollection.find(document).first();
        System.out.println(first.toJson());
    }

    private static void read(MongoCollection mongoCollection) {
        Document first = (Document) mongoCollection.find().first();
        System.out.println(first.toJson());
    }

    private static void save(MongoCollection mongoCollection) {
        Document document1 = new Document();
        document1.put("Mark", "BMW");
        document1.put("Model", "2");

//        Document document2 = new Document();
//        document2.put("Mark", "Fiat");
//        document2.put("Model", "126p");
//        document2.put("Color", "Red");
//
//        Document document3 = new Document();
//        document3.put("Mark", "Audi");
//        document3.put("Model", "A1");
//        document3.put("Color", Arrays.asList("red", "black", "gray"));

//        mongoCollection.insertMany(Arrays.asList(document1, document2, document3));
        mongoCollection.insertMany(Arrays.asList(document1));
    }
}
