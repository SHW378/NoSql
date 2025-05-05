package com.ca.mongojavaconnector;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.mongodb.MongoTimeoutException;

public class InsertScanner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            System.out.println();
            System.out.println("Ingrese la base de datos a usar (si no existe se creara)");
            String DBname = sc.nextLine();
            if (DBname.isEmpty()) {
                System.out.println("El nombre de la base de datos no puede estar vacío.");
                return;
            }
            MongoDatabase database = mongoClient.getDatabase(DBname);

            System.out.println("Ingrese la colección a usar (si no existe se creara)");
            String collectionName = sc.nextLine();
            if (collectionName.isEmpty()) {
                System.out.println("El nombre de la colección no puede estar vacío.");
                return;
            }
            MongoCollection<Document> collection = database.getCollection(collectionName);

            boolean continuar = false;

            while (continuar) {
                System.out.println("--Elige una opción--");
                System.out.println("1. Insertar un documento");
                System.out.println("2. Salir del programa");
                System.out.println("3. Update un documento");

                String opcion = sc.nextLine();
                switch (opcion) {
                    case "1":
                        crearDocumento(sc, collection, collectionName);
                        break;
                    case "2":
                        continuar = true;
                        break;
                    case "3":
                        UpdateDocumento(sc, collection);
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elija 1 o 2.");
                        break;
                }
            }
        } catch (MongoTimeoutException e) {
            System.err.println("Error en la conexión a MongoDB.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    private static void UpdateDocumento(Scanner sc, MongoCollection<Document> collection) {
        
    }

    private static void crearDocumento(Scanner sc, MongoCollection<Document> collection, String collectionName) {
        Document doc = new Document();
        boolean continuarAgregando = true;

        while (continuarAgregando) {
            System.out.println("Ingrese el nombre del atributo para: " + collectionName);
            String atributo = sc.nextLine();
            if (atributo.isEmpty()) {
                System.out.println("El nombre del atributo no puede estar vacío.");
                continue;
            }

            System.out
                    .println("Ingrese el valor del atributo de: " + atributo + ", de la collección: " + collectionName);
            String input = sc.nextLine();
            Object valor;
            try {
                valor = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                valor = input;
            }

            if (valor.toString().isEmpty()) {
                System.out.println("El valor del atributo no puede estar vacío.");
                continue;
            }

            doc.append(atributo, valor);

            while (true) {
                System.out.println("¿Desea agregar otro atributo? (s/n)");
                String continuar = sc.nextLine().toLowerCase();
                if (continuar.equals("s")) {
                    break;
                } else if (continuar.equals("n")) {
                    continuarAgregando = false;
                    break;
                } else {
                    System.out.println("Opción no válida. Por favor, ingrese 's' o 'n'.");
                }
            }
        }
        collection.insertOne(doc);
    }
}