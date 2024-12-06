package mypackage;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {
    private Connection connection;

    // Constructor que establece la conexión a la base de datos
    public InventoryDAO() {
        try {
            this.connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sakila?useSSL=false&serverTimezone=America/New_York", "root", "wylli"); // Cambia estos datos de conexión según sea necesario
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Crear un nuevo inventario
    public void create(Inventory inventory) {
        String sql = "INSERT INTO inventory (film_id, store_id) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, inventory.getFilmId());
            stmt.setInt(2, inventory.getStoreId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Leer todos los inventarios
    public List<Inventory> readAll() {
        List<Inventory> inventories = new ArrayList<>();
        String sql = "SELECT * FROM inventory";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int inventoryId = rs.getInt("inventory_id");
                int filmId = rs.getInt("film_id");
                int storeId = rs.getInt("store_id");

                Inventory inventory = new Inventory(inventoryId, filmId, storeId);
                inventories.add(inventory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventories;
    }

    // Leer un inventario por su ID
    public Inventory read(int inventoryId) {
        Inventory inventory = null;
        String sql = "SELECT * FROM inventory WHERE inventory_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, inventoryId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int filmId = rs.getInt("film_id");
                int storeId = rs.getInt("store_id");

                inventory = new Inventory(inventoryId, filmId, storeId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventory;
    }

    // Actualizar un inventario
    public void update(Inventory inventory) {
        String sql = "UPDATE inventory SET film_id = ?, store_id = ? WHERE inventory_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, inventory.getFilmId());
            stmt.setInt(2, inventory.getStoreId());
            stmt.setInt(3, inventory.getInventoryId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar un inventario por su ID
    public void delete(int inventoryId) {
        String sql = "DELETE FROM inventory WHERE inventory_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, inventoryId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cerrar la conexión cuando se haya terminado con la base de datos
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

