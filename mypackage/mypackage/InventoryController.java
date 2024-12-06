package mypackage;

import java.util.List;

public class InventoryController {
    private InventoryDAO inventoryDAO;

    // Constructor
    public InventoryController() {
        this.inventoryDAO = new InventoryDAO();
    }

    // Crear un nuevo inventario
    public void createInventory(int filmId, int storeId) {
        // Crea una nueva instancia de Inventory, sin el inventoryId ya que este será generado automáticamente (por ejemplo, con AUTO_INCREMENT en DB)
        Inventory inventory = new Inventory(0, filmId, storeId);
        inventoryDAO.create(inventory);
    }

    // Obtener todos los inventarios
    public List<Inventory> getAllInventories() {
        return inventoryDAO.readAll();
    }

    // Obtener un inventario por ID
    public Inventory getInventoryById(int inventoryId) {
        return inventoryDAO.read(inventoryId);
    }

    // Actualizar un inventario
    public void updateInventory(Inventory inventory) {
        inventoryDAO.update(inventory);
    }

    // Eliminar un inventario
    public void deleteInventory(int inventoryId) {
        inventoryDAO.delete(inventoryId);
    }
}
