package mypackage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO {
    private Connection connection;

    // Constructor que establece la conexión a la base de datos
    public FilmDAO() {
        try {
            this.connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sakila?useSSL=false&serverTimezone=America/New_York", "root", "wylli"); // Cambia estos datos de conexión según sea necesario
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Crear una nueva película
    public void create(Film film) {
        String sql = "INSERT INTO film (title, description, release_year, language_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, film.getTitle());
            stmt.setString(2, film.getDescription());
            stmt.setInt(3, film.getReleaseYear());
            stmt.setInt(4, film.getLanguageId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Leer todas las películas
    public List<Film> readAll() {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT * FROM film";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int filmId = rs.getInt("film_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int releaseYear = rs.getInt("release_year");
                int languageId = rs.getInt("language_id");

                Film film = new Film(filmId, title, description, releaseYear, languageId);
                films.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return films;
    }

    // Leer una película por su ID
    public Film read(int filmId) {
        Film film = null;
        String sql = "SELECT * FROM film WHERE film_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, filmId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                int releaseYear = rs.getInt("release_year");
                int languageId = rs.getInt("language_id");

                film = new Film(filmId, title, description, releaseYear, languageId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return film;
    }

    // Actualizar una película
    public void update(Film film) {
        String sql = "UPDATE film SET title = ?, description = ?, release_year = ?, language_id = ? WHERE film_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, film.getTitle());
            stmt.setString(2, film.getDescription());
            stmt.setInt(3, film.getReleaseYear());
            stmt.setInt(4, film.getLanguageId());
            stmt.setInt(5, film.getFilmId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar una película por su ID
    public void delete(int filmId) {
        String sql = "DELETE FROM film WHERE film_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, filmId);
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
