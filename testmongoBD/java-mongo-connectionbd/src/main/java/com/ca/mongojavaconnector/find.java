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

            // Filtro de para la collección
            Document filter = new Document("nombre", "Carlos Pérez");

            // Find() con el filtro ya aplicado
            Document personafind = collection.find(filter).first();

            if (personafind != null) {
                Persona persona = new Persona(
                        personafind.getString("nombre"),
                        personafind.getInteger("edad"),
                        personafind.getString("ciudad"),
                        personafind.getString("email"));
                System.out.println(persona);
            } else {
                System.out.println("No se encontro la persona en el documento");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
