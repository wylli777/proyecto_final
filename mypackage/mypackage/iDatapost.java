package mypackage;


import java.util.List;

public interface iDatapost<T> {
    // Métodos para CRUD
    void create(T object);
    T read(int id);
    List<T> readAll();
    void update(T object);
    void delete(int id);
}
