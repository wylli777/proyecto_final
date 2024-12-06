import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mypackage.Customer;
import mypackage.DatabaseConnection;

public class CustomerDAO implements iDatapost<Customer> {

    /**
     * Crea un nuevo cliente en la base de datos.
     *
     * @param customer El cliente a crear.
     */
    @Override
    public void create(Customer customer) {
        String sql = "INSERT INTO customer (store_id, first_name, last_name, address_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customer.getStoreId());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getLastName());
            stmt.setInt(4, customer.getAddressId());
            stmt.executeUpdate();

            System.out.println("Cliente creado con éxito!");

        } catch (SQLException e) {
            System.err.println("Error al crear cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Obtiene un cliente por su ID.
     *
     * @param id El ID del cliente.
     * @return El cliente con el ID especificado.
     */
    @Override
    public Customer read(int id) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";  // Cambio "id" por "customer_id"
        Customer customer = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    customer = mapResultSet(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener cliente: " + e.getMessage());
            e.printStackTrace();
        }

        return customer;
    }

    /**
     * Obtiene todos los clientes de la base de datos.
     *
     * @return Una lista de clientes.
     */
    @Override
    public List<Customer> readAll() {
        String sql = "SELECT * FROM customer";
        List<Customer> customers = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                customers.add(mapResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
            e.printStackTrace();
        }

        return customers;
    }

    /**
     * Actualiza un cliente en la base de datos.
     *
     * @param customer El cliente con los nuevos datos.
     */
    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customer SET store_id = ?, first_name = ?, last_name = ?, address_id = ? WHERE customer_id = ?";  // Cambio "id" por "customer_id"

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customer.getStoreId());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getLastName());
            stmt.setInt(4, customer.getAddressId());
            stmt.setInt(5, customer.getId());  // Aquí usamos el ID del cliente
            stmt.executeUpdate();

            System.out.println("Cliente actualizado con éxito!");

        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Elimina un cliente de la base de datos.
     *
     * @param id El ID del cliente a eliminar.
     */
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";  // Cambio "id" por "customer_id"

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Cliente eliminado con éxito!");

        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Mapea un ResultSet a un objeto Customer.
     *
     * @param rs El ResultSet con los datos del cliente.
     * @return El objeto Customer mapeado.
     */
    private Customer mapResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("customer_id");  // Cambio "id" por "customer_id"
        int storeId = rs.getInt("store_id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        int addressId = rs.getInt("address_id");

        return new Customer(id, storeId, firstName, lastName, addressId);
    }
}
