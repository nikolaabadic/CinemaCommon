package rs.ac.bg.fon.CinemaCommon.domain;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.omg.CORBA.ORB;

/**
 * Movie theater projection class.
 * @author Nikola Abadic
 *
 */
public class Term implements GenericEntity{
	/**
	 * Term identification number.
	 */
    private int termID;
    /**
     * Term date in which a film will be shown.
     */
    private Date date;
    /**
     * Film projection type (2D or 3D).
     */
    private String projectionType;
    /**
     * Film which will be shown.
     */
    private Film film;
    /**
     * Hall in which the film will be shown.
     */
    private Hall hall;
    
    /**
     * Non-parameterized constructor used for initialization of Term type objects.
     */
    public Term() {
    }

    /**
     * Parameterized constructor used for initialization of Term type objects.
     * @param termID Term identification number as an integer.
     * @param date Term date as java.util.Date.
     * @param projectionType Projection type as a String (2D or 3D).
     * @param film Film which will be shown.
     * @param hall Hall in which the film will be shown.
     */
    public Term(int termID, Date date, String projectionType, Film film, Hall hall) {
        this.termID = termID;
        this.date = date;
        this.projectionType = projectionType;
        this.film = film;
        this.hall = hall;
    }

    /**
     * Returns a Hall object in which the film will be shown.
     * @return Hall object in which the film will be shown.
     */
    public Hall getHall() {
        return hall;
    }

    /**
     * Sets the hall in which the film will be shown.
     * @param hall Hall object in which the film will be shown. 
     */
    public void setHall(Hall hall) {
        this.hall = hall;
    }

    /**
     * Returns the term identification number
     * @return Term identification number as an integer.
     */
    public int getTermID() {
        return termID;
    }

    /**
     * Sets the term identification number.
     * @param termID Term identification number as an integer.
     */
    public void setTermID(int termID) {
        this.termID = termID;
    }

    /**
     * Returns the term date in which the film will be shown.
     * @return java.util.Date in which the film will be shown.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the term date in which the film will be shown.
     * @param date java.util.Date in which the film will be shown.
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    /**
     * Returns the term projection type (2D or 3D). 
     * @return Term projection type as a String (2D or 3D).
     */
    public String getProjectionType() {
        return projectionType;
    }

    /**
     * Sets the term projection type (2D or 3D).
     * @param projectionType Projection type as a String (2D or 3D).
     */
    public void setProjectionType(String projectionType) {
        this.projectionType = projectionType;
    }
    
    /**
     * Returns a Film object which will be shown.
     * @return Film object which will be shown.
     */
    public Film getFilm() {
        return film;
    }
    
    /**
     * Sets the film which will be shown.
     * @param film Film object which will be shown.
     */
    public void setFilm(Film film) {
        this.film = film;
    }

    /**
     * Returns a String which contains all the information about the term.
     */
    @Override
    public String toString() {
        return film.getName() + " " + date + " " + hall.getName() + " " + projectionType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    /**
     * Compares two terms and returns true if they are equal, and false if not.
     * Terms are compared by their date, hall and projection type.
     * 
     * @return
     *  <ul>
	 *		<li>true - if both given object are type Term, and have the same date, hall and projection type.</li>
	 * 		<li>false - in all other cases.</li>
	 * </ul>
     */
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
}
