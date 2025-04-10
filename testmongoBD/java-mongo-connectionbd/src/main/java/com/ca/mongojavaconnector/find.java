package com.ca.mongojavaconnector;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class find {
    public static void main(String[] args) {

        // Crear la cadena de conexion/connection String
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            // Obtener base de datos (se crea si no existe)
            MongoDatabase database = mongoClient.getDatabase("Hola_Mundo");

            // Obtener colección (se crea si no exite)
            MongoCollection<Document> collection = database.getCollection("Personas");

            // Hacer el find()
            Document filter = new Document("nombre", "Carlos Pérez");
            Document personaDocument = collection.find(filter).first();

            if (personaDocument != null) {
                Persona persona = new Persona(
                        personaDocument.getString("nombre"),
                        personaDocument.getInteger("edad"),
                        personaDocument.getString("ciudad"),
                        personaDocument.getString("email")
                );
                System.out.println(persona);
            } else {
                System.out.println("No se encontro la persona en el documento");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
