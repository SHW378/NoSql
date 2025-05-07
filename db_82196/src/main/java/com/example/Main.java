package com.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

import java.util.Scanner;
import com.mongodb.MongoTimeoutException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            while (true) {
                System.out.println();
                String DBname = "DB_82196";
                MongoDatabase database = mongoClient.getDatabase(DBname);
                System.out.println("Base de datos en uso: " + DBname);
                System.out.println();
                MongoCollection<Document> collection = null;

                System.out.println("Collecciones disponibles:");
                MongoIterable<String> colecciones = database.listCollectionNames();
                boolean hayColecciones = false;
                for (String coleccion : colecciones) {
                    System.out.println("- " + coleccion);
                    hayColecciones = true;
                }
                if (!hayColecciones) {
                    System.out.println("No hay colecciones disponibles en esta base de datos.");
                    return;
                }
                System.out.println("Ingrese el nombre de la colección que desea usar:");
                String collectionName = sc.nextLine().trim();
                if (collectionName.isEmpty()) {
                    System.out.println("El nombre de la colección no puede estar vacío.");
                    continue;
                }
                boolean coleccionExiste = false;
                for (String coleccion : database.listCollectionNames()) {
                    if (coleccion.equals(collectionName)) {
                        coleccionExiste = true;
                        break;
                    }
                }
                if (!coleccionExiste) {
                    System.out.println("La colección '" + collectionName + "' no existe.");
                    continue;
                }
                System.out.println("Colección seleccionada: " + collectionName);
                collection = database.getCollection(collectionName);
                
                while (true) {
                    System.out.println("\n--Elige una opción--");
                    System.out.println("1. Cambiar collección");
                    System.out.println("2. Insertar un documento");
                    System.out.println("3. Leer documentos");
                    System.out.println("4. Actualizar un documento");
                    System.out.println("5. Eliminar un documento");
                    System.out.println("6. Salir del programa");

                    String opcion = sc.nextLine();

                    switch (opcion) {
                        case "1":
                            collection = seleccionarColeccion(sc, database);
                            break;
                        case "2":
                            if (collection == null) {
                                System.out.println("Primero debes seleccionar una colección.");
                            } else {
                                crearDocumento(sc, collection, collection.getNamespace().getCollectionName());
                            }
                            break;
                        case "3":
                            if (collection == null) {
                                System.out.println("Primero debes seleccionar una colección.");
                            } else {
                                leerDocumentos(collection);
                            }
                            break;
                        case "4":
                            if (collection == null) {
                                System.out.println("Primero debes seleccionar una colección.");
                            } else {
                                UpdateDocumento(sc, collection);
                            }
                            break;
                        case "5":
                            if (collection == null) {
                                System.out.println("Primero debes seleccionar una colección.");
                            } else {
                                eliminarDocumento(sc, collection);
                            }
                            break;
                        case "6":
                            System.out.println("Saliendo del programa...");
                            System.exit(0);
                        default:
                            System.out.println("Opción no válida. Por favor, elija entre 1 y 6.");
                            break;
                    }

                    if (opcion.equals("6")) {
                        break;
                    }
                }
            }
        } catch (MongoTimeoutException e) {
            System.err.println("Error en la conexión a MongoDB. Verifique que el servidor esté en ejecución.");
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    private static MongoCollection<Document> seleccionarColeccion(Scanner sc, MongoDatabase database) {
        System.out.println("\n--- Colecciones disponibles ---");
        MongoIterable<String> colecciones = database.listCollectionNames();
        boolean hayColecciones = false;
        for (String coleccion : colecciones) {
            System.out.println("- " + coleccion);
            hayColecciones = true;
        }

        if (!hayColecciones) {
            System.out.println("No hay colecciones disponibles en esta base de datos.");
            return null;
        }

        System.out.println("Ingrese el nombre de la colección que desea usar:");
        String collectionName = sc.nextLine().trim();
        if (collectionName.isEmpty()) {
            System.out.println("El nombre de la colección no puede estar vacío.");
            return null;
        }

        boolean coleccionExiste = false;
        for (String coleccion : database.listCollectionNames()) {
            if (coleccion.equals(collectionName)) {
                coleccionExiste = true;
                break;
            }
        }

        if (!coleccionExiste) {
            System.out.println("La colección '" + collectionName + "' no existe.");
            return null;
        }

        System.out.println("Colección seleccionada: " + collectionName);
        return database.getCollection(collectionName);
    }

    private static void crearDocumento(Scanner sc, MongoCollection<Document> collection, String collectionName) {

        Document doc = new Document();
        if (collectionName.equals("empleados")) {
            System.out.println("Ingrese el nombre del empleado: ");
            String nombreEmpleado = sc.nextLine();
            System.out.println("Ingrese el rol del empleado: ");
            String rolEmpleado = sc.nextLine();
            int edadEmpleado = 0;
            boolean edadValida = false; 
            while (!edadValida) {
                System.out.println("Ingrese la edad del empleado: ");
                try {
                    edadEmpleado = Integer.parseInt(sc.nextLine());
                    edadValida = true; 
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingrese un número válido para la edad.");
                }
            }

            doc.append("nombre", nombreEmpleado)
                    .append("rol", rolEmpleado)
                    .append("edad", edadEmpleado);
            System.out.println("Documento insertado en la colección 'empleados': " + doc.toJson());
            collection.insertOne(doc);

        } else if (collectionName.equals("proyecto")) {
            System.out.println("Ingrese el nombre del proyecto: ");
            String nombreProyecto = sc.nextLine();
            System.out.println("Ingrese la fecha de inicio del proyecto: ");
            String fechaInicioProyecto = sc.nextLine();
            System.out.println("Ingrese el estado del proyecto: ");
            String estadoProyecto = sc.nextLine();

            doc.append("nombre", nombreProyecto)
                    .append("fecha_inicio", fechaInicioProyecto)
                    .append("estado", estadoProyecto);
            System.out.println("Documento insertado en la colección 'proyecto': " + doc.toJson());
            collection.insertOne(doc);

        } else if (collectionName.equals("tareas")) {
            System.out.println("Ingrese el titulo de la tarea: ");
            String tituloTarea = sc.nextLine();
            System.out.println("Ingrese la fecha límite de la tarea: ");
            String fechaLimiteTarea = sc.nextLine();
            System.out.println("Ingrese la fecha de inicio de la tarea: ");
            String fechaInicioTarea = sc.nextLine();
            doc.append("titulo", tituloTarea)
                    .append("fecha_limite", fechaLimiteTarea)
                    .append("fecha_inicio", fechaInicioTarea);
            System.out.println("Documento insertado en la colección 'tareas': " + doc.toJson());
            collection.insertOne(doc);
        }
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
