package mypackage;


import java.util.List;

public class RentalController {
    private RentalDAO rentalDAO;

    // Constructor
    public RentalController() {
        this.rentalDAO = new RentalDAO();
    }

    // Crear una nueva renta
    public void createRental(int filmId, int customerId) {
        Rental rental = new Rental(filmId, customerId, customerId, null, null);
        rentalDAO.create(rental);
    }

    // Obtener todas las rentas
    public List<Rental> getAllRentals() {
        return rentalDAO.readAll();
    }

    // Obtener una renta por ID
    public Rental getRentalById(int rentalId) {
        return rentalDAO.read(rentalId);
    }

    // Actualizar una renta
    public void updateRental(Rental rental) {
        rentalDAO.update(rental);
    }

    // Eliminar una renta
    public void deleteRental(int rentalId) {
        rentalDAO.delete(rentalId);
    }
}
