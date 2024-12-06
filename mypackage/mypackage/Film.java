package mypackage;

public class Film {
    private int filmId;
    private String title;
    private String description;
    private int releaseYear;
    private int languageId;

    // Constructor
    public Film(int filmId, String title, String description, int releaseYear, int languageId) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.languageId = languageId;
    }

    public Film(int idToUpdate, String newTitle, int newReleaseYear) {
        //TODO Auto-generated constructor stub
    }

    // Getters and Setters
    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Override
    public String toString() {
        return "Film{filmId=" + filmId + ", title='" + title + "', description='" + description + "', releaseYear=" + releaseYear + ", languageId=" + languageId + '}';
    }
}
