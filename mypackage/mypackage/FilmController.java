package mypackage;


import java.util.List;

public class FilmController {
    private FilmDAO filmDAO;

    // Constructor
    public FilmController() {
        this.filmDAO = new FilmDAO();
    }

    // Crear una nueva película
    public void createFilm(String title, String description) {
        Film film = new Film(0, title, description, 0, 0);
        filmDAO.create(film);
    }

    // Obtener todas las películas
    public List<Film> getAllFilms() {
        return filmDAO.readAll();
    }

    // Obtener una película por ID
    public Film getFilmById(int filmId) {
        return filmDAO.read(filmId);
    }

    // Actualizar una película
    public void updateFilm(Film film) {
        filmDAO.update(film);
    }

    // Eliminar una película
    public void deleteFilm(int filmId) {
        filmDAO.delete(filmId);
    }
}

