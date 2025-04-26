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
            System.out.println();
            System.out.println("Ingrese la base de datos a usar (si no existe se creara)");
            MongoDatabase database = mongoClient.getDatabase(sc.nextLine());
            System.out.println("Ingrese la colección a usar (si no existe se creara)");
            MongoCollection<Document> collection = database.getCollection(sc.nextLine());

            Document doc = new Document();

            for (String continuar = "s"; continuar.equalsIgnoreCase("s"); ) {
                System.out.println("Ingrese el nombre del atributo: ");
                String atributo = sc.nextLine();
                System.out.println("Ingrese el valor del atributo: ");
                String input = sc.nextLine();

                Object valor;
                try {
                    valor = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    valor = input;
                }
                doc.append(atributo, valor);

                for (;;) {
                    System.out.println("¿Desea agregar otro atributo? (s/n)");
                    continuar = sc.nextLine().toLowerCase();
                    if (continuar.equals("s") || continuar.equals("n")) {
                        break;
                    }
                    System.out.println("Caracter inválido. Por favor, ingrese 's' para continuar o 'n' para salir.");
                }
            }

            collection.insertOne(doc);
            System.out.println("Documento Insertado con éxito");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}