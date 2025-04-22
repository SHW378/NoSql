package com.ca.mongojavaconnector;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.Scanner;

public class InsertScanner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            System.out.println("Ingrese la base de datos a usar (si no existe se creara)");
            MongoDatabase database = mongoClient.getDatabase(sc.nextLine());
            System.out.println("Ingrese la colección a usar (si no existe se creara)");
            MongoCollection<Document> collection = database.getCollection(sc.nextLine());

            System.out.println("Ingrese el nombre: ");
            String nombre = sc.nextLine();

            System.out.println("Ingrese la edad");
            int edad = sc.nextInt();

            Document dc = new Document("nombre", nombre)
                                .append("edad", edad);

            collection.insertOne(dc);

            System.out.println("Documento insertado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }

    }

}
