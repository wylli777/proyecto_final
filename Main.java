import java.util.List;
import java.util.Scanner;

import mypackage.Film;
import mypackage.FilmDAO;
import mypackage.Inventory;
import mypackage.InventoryDAO;
import mypackage.Rental;
import mypackage.RentalDAO;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instanciar los controladores
        FilmDAO filmDAO = new FilmDAO();
        InventoryDAO inventoryDAO = new InventoryDAO();
        RentalDAO rentalDAO = new RentalDAO();

        boolean running = true;

        while (running) {
            System.out.println("\n************** Menú de Gestión **************");
            System.out.println("1. Gestión de Películas");
            System.out.println("2. Gestión de Inventarios");
            System.out.println("3. Gestión de Rentas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    // Menú de Gestión de Películas
                    manageFilms(filmDAO, scanner);
                    break;
                case 2:
                    // Menú de Gestión de Inventarios
                    manageInventories(inventoryDAO, scanner);
                    break;
                case 3:
                    // Menú de Gestión de Rentas
                    manageRentals(rentalDAO, scanner);
                    break;
                case 4:
                    // Salir
                    running = false;
                    System.out.println("¡Gracias por usar el sistema! ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida, por favor intente nuevamente.");
                    break;
            }
        }

        scanner.close();
    }

    private static void manageFilms(FilmDAO filmDAO, Scanner scanner) {
        boolean running = true;

        while (running) {
            System.out.println("\n*** Gestión de Películas ***");
            System.out.println("1. Crear Película");
            System.out.println("2. Ver Película");
            System.out.println("3. Ver Todas las Películas");
            System.out.println("4. Actualizar Película");
            System.out.println("5. Eliminar Película");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    // Crear Película
                    System.out.print("Ingrese el ID de la película: ");
                    int filmId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el título de la película: ");
                    String title = scanner.nextLine();
                    System.out.print("Ingrese el año de estreno: ");
                    int releaseYear = scanner.nextInt();
                    scanner.nextLine();
                    Film newFilm = new Film(filmId, title, title, releaseYear, filmId);
                    filmDAO.create(newFilm);
                    break;
                case 2:
                    // Ver Película
                    System.out.print("Ingrese el ID de la película a consultar: ");
                    int idToView = scanner.nextInt();
                    Film film = filmDAO.read(idToView);
                    if (film != null) {
                        System.out.println("Película encontrada: " + film);
                    } else {
                        System.out.println("Película no encontrada.");
                    }
                    break;
                case 3:
                    // Ver Todas las Películas
                    List<Film> films = filmDAO.readAll();
                    if (!films.isEmpty()) {
                        films.forEach(System.out::println);
                    } else {
                        System.out.println("No hay películas registradas.");
                    }
                    break;
                case 4:
                    // Actualizar Película
                    System.out.print("Ingrese el ID de la película a actualizar: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el nuevo título de la película: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Ingrese el nuevo año de estreno: ");
                    int newReleaseYear = scanner.nextInt();
                    Film updatedFilm = new Film(idToUpdate, newTitle, newReleaseYear);
                    filmDAO.update(updatedFilm);
                    break;
                case 5:
                    // Eliminar Película
                    System.out.print("Ingrese el ID de la película a eliminar: ");
                    int idToDelete = scanner.nextInt();
                    filmDAO.delete(idToDelete);
                    break;
                case 6:
                    // Volver al Menú Principal
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida, por favor intente nuevamente.");
                    break;
            }
        }
    }

    private static void manageInventories(InventoryDAO inventoryDAO, Scanner scanner) {
        boolean running = true;

        while (running) {
            System.out.println("\n*** Gestión de Inventarios ***");
            System.out.println("1. Crear Inventario");
            System.out.println("2. Ver Inventario");
            System.out.println("3. Ver Todos los Inventarios");
            System.out.println("4. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    // Crear Inventario
                    System.out.print("Ingrese el ID del inventario: ");
                    int inventoryId = scanner.nextInt();
                    System.out.print("Ingrese el ID de la película: ");
                    int filmId = scanner.nextInt();
                    System.out.print("Ingrese el ID de la tienda: ");
                    int storeId = scanner.nextInt();
                    Inventory newInventory = new Inventory(inventoryId, filmId, storeId);
                    inventoryDAO.create(newInventory);
                    break;
                case 2:
                    // Ver Inventario
                    System.out.print("Ingrese el ID del inventario a consultar: ");
                    int idToView = scanner.nextInt();
                    Inventory inventory = inventoryDAO.read(idToView);
                    if (inventory != null) {
                        System.out.println("Inventario encontrado: " + inventory);
                    } else {
                        System.out.println("Inventario no encontrado.");
                    }
                    break;
                case 3:
                    // Ver Todos los Inventarios
                    List<Inventory> inventories = inventoryDAO.readAll();
                    if (!inventories.isEmpty()) {
                        inventories.forEach(System.out::println);
                    } else {
                        System.out.println("No hay inventarios registrados.");
                    }
                    break;
                case 4:
                    // Volver al Menú Principal
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida, por favor intente nuevamente.");
                    break;
            }
        }
    }

    private static void manageRentals(RentalDAO rentalDAO, Scanner scanner) {
        boolean running = true;

        while (running) {
            System.out.println("\n*** Gestión de Rentas ***");
            System.out.println("1. Crear Renta");
            System.out.println("2. Ver Renta");
            System.out.println("3. Ver Todas las Rentas");
            System.out.println("4. Actualizar Renta");
            System.out.println("5. Eliminar Renta");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    // Crear Renta
                    System.out.print("Ingrese el ID de la renta: ");
                    int rentalId = scanner.nextInt();
                    System.out.print("Ingrese el ID del cliente: ");
                    int customerId = scanner.nextInt();
                    System.out.print("Ingrese el ID del inventario: ");
                    int inventoryId = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese la fecha de renta: ");
                    String rentalDate = scanner.nextLine();
                    System.out.print("Ingrese la fecha de devolución: ");
                    String returnDate = scanner.nextLine();
                    Rental newRental = new Rental(rentalId, customerId, inventoryId, rentalDate, returnDate);
                    rentalDAO.create(newRental);
                    break;
                case 2:
                    // Ver Renta
                    System.out.print("Ingrese el ID de la renta a consultar: ");
                    int rentalIdToView = scanner.nextInt();
                    Rental rental = rentalDAO.read(rentalIdToView);
                    if (rental != null) {
                        System.out.println("Renta encontrada: " + rental);
                    } else {
                        System.out.println("Renta no encontrada.");
                    }
                    break;
                case 3:
                    // Ver Todas las Rentas
                    List<Rental> rentals = rentalDAO.readAll();
                    if (!rentals.isEmpty()) {
                        rentals.forEach(System.out::println);
                    } else {
                        System.out.println("No hay rentas registradas.");
                    }
                    break;
                case 4:
                    // Actualizar Renta
                    System.out.print("Ingrese el ID de la renta a actualizar: ");
                    int rentalIdToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese la nueva fecha de renta: ");
                    String newRentalDate = scanner.nextLine();
                    System.out.print("Ingrese la nueva fecha de devolución: ");
                    String newReturnDate = scanner.nextLine();
                    Rental updatedRental = new Rental(rentalIdToUpdate, rentalIdToUpdate, rentalIdToUpdate, newRentalDate, newReturnDate);
                    rentalDAO.update(updatedRental);
                    break;
                case 5:
                    // Eliminar Renta
                    System.out.print("Ingrese el ID de la renta a eliminar: ");
                    int rentalIdToDelete = scanner.nextInt();
                    rentalDAO.delete(rentalIdToDelete);
                    break;
                case 6:
                    // Volver al Menú Principal
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida, por favor intente nuevamente.");
                    break;
            }
        }
    }
}
