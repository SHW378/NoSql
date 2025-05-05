package com.ca.mongojavaconnector;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Scanner;
import com.mongodb.MongoTimeoutException;

public class InsertScanner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            System.out.println();
            System.out.println("Ingrese la base de datos a usar (si no existe se creará)");
            String DBname = sc.nextLine();
            if (DBname.isEmpty()) {
                System.out.println("El nombre de la base de datos no puede estar vacío.");
                return;
            }
            MongoDatabase database = mongoClient.getDatabase(DBname);

            System.out.println("Ingrese la colección a usar (si no existe se creará)");
            String collectionName = sc.nextLine();
            if (collectionName.isEmpty()) {
                System.out.println("El nombre de la colección no puede estar vacío.");
                return;
            }
            MongoCollection<Document> collection = database.getCollection(collectionName);

            boolean continuar = true;

            while (continuar) {
                System.out.println("\n--Elige una opción--");
                System.out.println("1. Insertar un documento");
                System.out.println("2. Leer documentos");
                System.out.println("3. Actualizar un documento");
                System.out.println("4. Eliminar un documento");
                System.out.println("5. Salir del programa");

                String opcion = sc.nextLine();
                switch (opcion) {
                    case "1":
                        crearDocumento(sc, collection, collectionName);
                        break;
                    case "2":
                        leerDocumentos(collection);
                        break;
                    case "3":
                        UpdateDocumento(sc, collection);
                        break;
                    case "4":
                        eliminarDocumento(sc, collection);
                        break;
                    case "5":
                        System.out.println("Saliendo del programa...");
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elija entre 1 y 5.");
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

            System.out.println("Ingrese el valor del atributo de: " + atributo + ", de la colección: " + collectionName);
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
        System.out.println("Documento insertado con éxito.");
    }

    private static void leerDocumentos(MongoCollection<Document> collection) {
        System.out.println("\n--- Documentos en la colección ---");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }
    }

    private static void UpdateDocumento(Scanner sc, MongoCollection<Document> collection) {
        System.out.println("Ingrese el campo por el cual buscar el documento (por ejemplo, 'nombre'): ");
        String campoBusqueda = sc.nextLine().trim();
        if (campoBusqueda.isEmpty()) {
            System.out.println("El campo de búsqueda no puede estar vacío.");
            return;
        }

        System.out.println("Ingrese el valor del campo de búsqueda: ");
        String valorBusqueda = sc.nextLine().trim();
        if (valorBusqueda.isEmpty()) {
            System.out.println("El valor del campo de búsqueda no puede estar vacío.");
            return;
        }

        Document filtro = new Document(campoBusqueda, valorBusqueda);

        Document documentoExistente = collection.find(filtro).first();
        if (documentoExistente == null) {
            System.out.println("No se encontró ningún documento con el criterio especificado.");
            return;
        }

        System.out.println("Documento encontrado: " + documentoExistente.toJson());

        System.out.println("Ingrese el campo que desea actualizar: ");
        String campoActualizar = sc.nextLine().trim();
        if (campoActualizar.isEmpty()) {
            System.out.println("El campo a actualizar no puede estar vacío.");
            return;
        }

        System.out.println("Ingrese el nuevo valor para el campo '" + campoActualizar + "': ");
        String nuevoValor = sc.nextLine().trim();
        if (nuevoValor.isEmpty()) {
            System.out.println("El nuevo valor no puede estar vacío.");
            return;
        }

        Document actualizacion = new Document("$set", new Document(campoActualizar, nuevoValor));

        collection.updateOne(filtro, actualizacion);
        System.out.println("Documento actualizado con éxito.");
    }

    private static void eliminarDocumento(Scanner sc, MongoCollection<Document> collection) {
        System.out.println("Ingrese el campo por el cual buscar el documento a eliminar (por ejemplo, 'nombre'): ");
        String campoBusqueda = sc.nextLine().trim();
        if (campoBusqueda.isEmpty()) {
            System.out.println("El campo de búsqueda no puede estar vacío.");
            return;
        }

        System.out.println("Ingrese el valor del campo de búsqueda: ");
        String valorBusqueda = sc.nextLine().trim();
        if (valorBusqueda.isEmpty()) {
            System.out.println("El valor del campo de búsqueda no puede estar vacío.");
            return;
        }

        Document filtro = new Document(campoBusqueda, valorBusqueda);

        Document documentoExistente = collection.find(filtro).first();
        if (documentoExistente == null) {
            System.out.println("No se encontró ningún documento con el criterio especificado.");
            return;
        }

        collection.deleteOne(filtro);
        System.out.println("Documento eliminado con éxito.");
    }
}