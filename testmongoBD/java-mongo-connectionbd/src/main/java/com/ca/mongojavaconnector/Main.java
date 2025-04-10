package com.ca.mongojavaconnector;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        
        //Crear la cadena de conexion/connection string
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            // Obtener base de datos (se crea si no existe)
            MongoDatabase database = mongoClient.getDatabase("Hola_Mundo");
 
            // Obtener colección (se crea si no existe)
            MongoCollection<Document> collection = database.getCollection("Users");
 
            // Insertar un documento de prueba
            Document doc = new Document("nombre", "Andrea")
                                .append("edad", 45);
            collection.insertOne(doc);
 
            System.out.println("Documento insertado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}