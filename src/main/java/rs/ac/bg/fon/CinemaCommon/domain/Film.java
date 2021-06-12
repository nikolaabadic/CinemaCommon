package rs.ac.bg.fon.CinemaCommon.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Film implements GenericEntity{
    private int filmID;
    private String name;
    private int year;
    private int duration;
    private String language;
    private List<Term> terms;

    public Film() {
    }

    public Film(int filmID, String Name, int year, int duration, String language) {
        this.filmID = filmID;
        this.name = Name;
        this.year = year;
        this.duration = duration;
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }
    
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
