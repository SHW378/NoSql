import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;

public class find {
    public static void main(String[] args) {

        //Crear la cadena de conexion/connection String
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            // Obtener base de datos (se crea si no existe)
            MongoDatabase database = mongoClient.getDatabase("Hola_Mundo");

            // Obtener colección (se crea si no exite)
            MongoCollection<Document> collection = database.getCollection("Users");

            // Hacer el find()
            FindIterable<Document> documents = collection.find();

            // Iterar y mostrar los documentos
            for (Document doc : documents) {
                System.out.println(doc.toJson());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
