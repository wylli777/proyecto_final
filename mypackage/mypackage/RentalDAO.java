package mypackage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalDAO implements iDatapost<Rental> {

    // Crea una nueva renta en la base de datos
    @Override
    public void create(Rental rental) {
        String sql = "INSERT INTO rental (rental_id, rental_date, inventory_id, customer_id, return_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rental.getRentalId());
            stmt.setString(2, rental.getRentalDate());
            stmt.setInt(3, rental.getInventoryId());
            stmt.setInt(4, rental.getCustomerId());
            stmt.setString(5, rental.getReturnDate());

            stmt.executeUpdate();
            System.out.println("Renta creada con éxito!");

        } catch (SQLException e) {
            System.err.println("Error al crear renta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Obtiene una renta por su ID
    @Override
    public Rental read(int id) {
        String sql = "SELECT * FROM rental WHERE rental_id = ?";
        Rental rental = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    rental = mapResultSet(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener renta: " + e.getMessage());
            e.printStackTrace();
        }

        return rental;
    }

    // Obtiene todas las rentas de la base de datos
    @Override
    public List<Rental> readAll() {
        String sql = "SELECT * FROM rental";
        List<Rental> rentals = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                rentals.add(mapResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener rentas: " + e.getMessage());
            e.printStackTrace();
        }

        return rentals;
    }

    // Actualiza una renta en la base de datos
    @Override
    public void update(Rental rental) {
        String sql = "UPDATE rental SET rental_date = ?, inventory_id = ?, customer_id = ?, return_date = ? WHERE rental_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rental.getRentalDate());
            stmt.setInt(2, rental.getInventoryId());
            stmt.setInt(3, rental.getCustomerId());
            stmt.setString(4, rental.getReturnDate());
            stmt.setInt(5, rental.getRentalId());

            stmt.executeUpdate();
            System.out.println("Renta actualizada con éxito!");

        } catch (SQLException e) {
            System.err.println("Error al actualizar renta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Elimina una renta de la base de datos
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM rental WHERE rental_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Renta eliminada con éxito!");

        } catch (SQLException e) {
            System.err.println("Error al eliminar renta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Mapea un ResultSet a un objeto Rental
    private Rental mapResultSet(ResultSet rs) throws SQLException {
        int rentalId = rs.getInt("rental_id");
        String rentalDate = rs.getString("rental_date");
        int inventoryId = rs.getInt("inventory_id");
        int customerId = rs.getInt("customer_id");
        String returnDate = rs.getString("return_date");

        return new Rental(rentalId, customerId, inventoryId, rentalDate, returnDate);
    }
}
