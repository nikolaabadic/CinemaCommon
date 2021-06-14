package rs.ac.bg.fon.CinemaCommon.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Film class.
 * @author Nikola Abadic
 *
 */
public class Film implements GenericEntity{
	/**
	 * Film identification number.
	 */
    private int filmID;
    /**
     * String value for the film title.
     */
    private String name;
    /**
     * Integer value for the year in which the film has been published.
     */
    private int year;
    /**
     * Integer value for the film duration (in minutes).
     */
    private int duration;
    /**
     * String value for the film language.
     */
    private String language;
    /**
     * List of terms in which the film will be show.
     */
    private List<Term> terms;

    /**
     * Non-parameterized constructor used for initialization of Film type objects.
     */
    public Film() {
    }

    /**
     * Parameterized constructor used for initialization of Film type objects and setting given values.
     * @param filmID Integer value for the film identification number.
     * @param Name	String value for the film title.
     * @param year	Integer value for the year in which the film was published.
     * @param duration	Integer value for the film duration (in minutes).
     * @param language String value for the film language. 
     */
    public Film(int filmID, String Name, int year, int duration, String language) {
        this.filmID = filmID;
        this.name = Name;
        this.year = year;
        this.duration = duration;
        this.language = language;
    }
    
    /**
     * Returns the film language.
     * @return String value of the film language.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the film language.
     * @param language Film language as a String.
     */
    public void setLanguage(String language) {
        this.language = language;
    }
    
    /**
     * Returns the film identification number.
     * @return Integer value of the film identification number.
     */
    public int getFilmID() {
        return filmID;
    }

    /**
     * Sets the film identification number.
     * @param filmID Integer value for the film identification number.
     */
    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    /**
     * Returns the film title.
     * @return String value of the film title.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the film title.
     * @param name Film title as a String.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns the year in which the film was published.
     * @return Film year as an integer.
     */
    public int getYear() {
        return year;
    }
    
    /**
     * Sets the year in which the film was published.
     * @param year Film year as an integer.
     */
    public void setYear(int year) {
        this.year = year;
    }
    
    /**
     * Returns the film duration in minutes.
     * @return Film duration in minutes as an integer.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the film duration.
     * @param duration Integer value of the film duration in minutes.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Returns a list of Terms in which the film will be shown.
     * @return List of Terms in which the film will be shown.
     */
    public List<Term> getTerms() {
        return terms;
    }

    /**
     * Sets the list of Terms in which the film will be shown.
     * @param terms List of Terms in which the film will be shown.
     */
    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }
    
    /**
     * Returns the film title.
     */
    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getTableName() {
        return "Film";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "Name,Year,Duration,Language";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(name).append("'").append(",")
                .append(year).append(",")
                .append(duration).append(",")
                .append("'").append(language).append("'");
        return sb.toString();
    }

    @Override
    public void setId(int id) {
        this.filmID = id;
    }    

    @Override
    public String getDeleteString() {
        return " FilmID=" + filmID;
    }
    
    @Override
    public String getUpdateString() {
        return " filmID= " + filmID;
    }
    
    @Override
    public List<GenericEntity> readList(ResultSet rs) throws SQLException {
        List<GenericEntity> list = new ArrayList<>();

        while(rs.next()){
            list.add(new Film( rs.getInt("FilmID"),
                               rs.getString("Name"),
                               rs.getInt("Year"),
                               rs.getInt("Duration"),
                               rs.getString("Language")      
            ));
        }
        
        return list;
    }

    @Override
    public String getUpdateValues(GenericEntity entity) {
        Film film = (Film)entity;
        StringBuilder sb = new StringBuilder();
        sb.append( "FilmID=").append(film.getFilmID()).append(", ")
          .append(" Name='").append(film.getName()).append("', ")
          .append(" Duration=").append(film.getDuration()).append(", ")
          .append(" Language='").append(film.getLanguage()).append("',")
          .append(" Year=").append(film.getYear());
        return sb.toString();
    }

    @Override
    public List<GenericEntity> getList() {
        List<GenericEntity> list = new ArrayList<>();
        for(Term t : terms){
            list.add(t);
        }
        return list;
    }

    @Override
    public String getAlias() {
        return " f";
    }

    @Override
    public String getForeignKey() {
        return "";
    }

    @Override
    public String getPrimaryKey() {
        return "filmID";
    }

    @Override
    public String getSecondForeignKey() {
        return "";
    }
}
