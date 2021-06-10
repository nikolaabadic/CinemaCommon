package rs.ac.bg.fon.CinemaCommon.domain;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Term implements GenericEntity{
    private int termID;
    private Date date;
    private String projectionType;
    private Film film;
    private Hall hall;

    public Term() {
    }

    public Term(int termID, Date date, String projectionType, Film film, Hall hall) {
        this.termID = termID;
        this.date = date;
        this.projectionType = projectionType;
        this.film = film;
        this.hall = hall;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProjectionType() {
        return projectionType;
    }

    public void setProjectionType(String projectionType) {
        this.projectionType = projectionType;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return film.getName() + " " + date + " " + hall.getName() + " " + projectionType;
    }

    @Override
    public String getTableName() {
        return "Term";
    }

    @Override
    public String getColumnNamesForInsert() {
        return " Date,Type,FilmID,HallID ";
    }

    @Override
    public String getDeleteString() {
        return " TermID=" +termID;
    }

    @Override
    public String getUpdateString() {
        return " TermID=" +termID;
    }
    
    

    @Override
    public String getInsertValues() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(sdf.format(date)).append("',")
                .append("'").append(projectionType).append("'").append(",")
                .append(film.getFilmID()).append(",")
                .append(hall.getHallID());
        return sb.toString();
    }

    @Override
    public void setId(int id) {
        this.termID = id;
    }

    @Override
    public List<GenericEntity> readList(ResultSet rs) throws Exception {
        List<GenericEntity> list = new ArrayList<>();
        
        while(rs.next()){
            Term term = new Term();
            term.setTermID(rs.getInt("TermID"));
            
            java.sql.Timestamp dateSQL=rs.getTimestamp("Date");
            term.setDate(new java.util.Date(dateSQL.getTime()));
            term.setProjectionType(rs.getString("Type"));
            
            Film film = new Film();
            film.setFilmID(rs.getInt("FilmID"));
            term.setFilm(film);
            
            Hall hall = new Hall();
            hall.setHallID(rs.getInt("HallID"));
            term.setHall(hall);
            
            list.add(term);            
        }        
        
        return list;
    }

    @Override
    public String getUpdateValues(GenericEntity entity) {
        Term term = (Term)entity;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append( "FilmID=").append(term.getFilm().getFilmID()).append(", ")
          .append(" HallID=").append(term.getHall().getHallID()).append(", ")
          .append(" Date='").append(sdf.format(term.getDate())).append("', ")
          .append(" Type='").append(term.getProjectionType()).append("'");
        return sb.toString();
    }

    @Override
    public List<GenericEntity> getList() {
        return null;
    }

    @Override
    public String getInsertValues(int id) {
        java.sql.Timestamp Date = new java.sql.Timestamp(date.getTime());
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(Date).append("',")
                .append("'").append(projectionType).append("'").append(",")
                .append(id).append(",")
                .append(hall.getHallID());
        return sb.toString();
    }

    @Override
    public String getAlias() {
        return " t";
    }

    @Override
    public String getForeignKey() {
        return "filmID";
    }

    @Override
    public String getPrimaryKey() {
        return "termID";
    }

    @Override
    public String getSecondForeignKey() {
        return "hallID";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Term other = (Term) obj;
        if (!Objects.equals(this.projectionType, other.projectionType)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.hall.getHallID(), other.hall.getHallID())) {
            return false;
        }
        return true;
    }   
}
