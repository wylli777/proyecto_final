import java.util.List;

public interface iDatapost<T> {

    T read(int id); // Método para obtener un registro por su ID

    List<T> readAll(); // Método para obtener todos los registros

    void create(T entity); // Método para crear un nuevo registro

    void update(T entity); // Método para actualizar un registro existente

    void delete(int id); // Método para eliminar un registro por su ID
}
