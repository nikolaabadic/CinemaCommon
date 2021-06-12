package rs.ac.bg.fon.CinemaCommon.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * Movie theatre ticket reservation class.
 * @author Nikola Abadic
 *
 */
public class Reservation implements GenericEntity{
	/**
	 * Term for which the reservation is created.
	 */
    private Term term;
    /**
     * Reservation owner.
     */
    private User user;
    /**
     * Date of reservation.
     */
    private Date reservationDate;
    /** 
     * Number of tickets reserved.
     */
    private int numberOfTickets;
    
    /**
     * Non-parameterized constructor used for initialization of Reservation type objects.
     */
    public Reservation() {
    }
    /**
     * Parameterized constructor used for initialization of Reservation type objects and setting given values.
     * @param term Term for which the reservation is created.
     * @param user Reservation owner as an User object.
     * @param reservationDate Reservation date as java.util.Date.  
     * @param numberOfTickets Number of tickets reserved as an integer.
     */
    public Reservation(Term term, User user, Date reservationDate, int numberOfTickets) {
        this.term = term;
        this.user = user;
        this.reservationDate = reservationDate;
        this.numberOfTickets=numberOfTickets;
    }

    /**
     * Returns the reservation date.
     * @return java.util.Date of reservation.
     */
    public Date getReservationDate() {
        return reservationDate;
    }
    
    /**
     * Sets the reservation date. 
     * @param reservationDate java.util.Date of reservation.
     */
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    /**
     * Returns a Term for which the reservation was created.
     * @return Term object for which the reservation was created. 
     */
    public Term getTerm() {
        return term;
    }

    /**
     * Sets the term for which the reservation was created. 
     * @param term Term object for which the reservation was created.
     */
    public void setTerm(Term term) {
        this.term = term;
    }

    /**
     * Returns the reservation owner.
     * @return User object which created the reservation.
     */
    public User getUser() {
        return user;
    }
    
    /**
     * Sets the reservation owner.
     * @param user User object which created the reservation.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns the number of reserved tickets created.
     * @return Number of tickets as an integer.
     */
    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    /**
     * Sets the number of reserved tickets created.
     * @param numberOfTickets Number of tickets as an integer.
     */
    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    @Override
    public String getTableName() {
        return "Reservation";
    }

    @Override
    public String getColumnNamesForInsert() {
        return " Date, Number, TermID, UserID";
    }

    @Override
    public String getDeleteString() {
        return " TermID=" +term.getTermID() + " && UserID="+user.getUserID();
    }
    @Override
    public String getUpdateString() {
        return " TermID=" +term.getTermID() + " && UserID="+user.getUserID();
    }
    
    @Override
    public String getInsertValues() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(sdf.format(reservationDate)).append("',")
                .append(numberOfTickets).append(",")
                .append(term.getTermID()).append(",")
                .append(user.getUserID());
        return sb.toString();
    }

    @Override
    public void setId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GenericEntity> readList(ResultSet rs) throws SQLException {
        List<GenericEntity> list = new ArrayList<>();
        
        while(rs.next()){
            Reservation reservation = new Reservation();
            reservation.setNumberOfTickets(rs.getInt("Number"));
            
            java.sql.Timestamp dateSQL=rs.getTimestamp("Date");
            reservation.setReservationDate(new java.util.Date(dateSQL.getTime()));
            
            Term term = new Term();
            term.setTermID(rs.getInt("TermID"));
            reservation.setTerm(term);
            
            User user = new User();
            user.setUserID(rs.getInt("UserID"));
            reservation.setUser(user);
            
            list.add(reservation);            
        }        
        
        return list;
    }

    @Override
    public String getUpdateValues(GenericEntity entity) {
        Reservation reservation = (Reservation)entity;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append(" UserID=").append(reservation.getUser().getUserID()).append(", ")
          .append(" TermID=").append(reservation.getTerm().getTermID()).append(", ")
          .append(" ReservationDate='").append(sdf.format(reservation.getReservationDate())).append("', ")
          .append(" NumberOfTickets=").append(reservation.getNumberOfTickets());
        return sb.toString();
    }

    @Override
    public List<GenericEntity> getList() {
        return null;
    }

    @Override
    public String getAlias() {
        return "r";
    }

    @Override
    public String getForeignKey() {
        return "userID";
    }

    @Override
    public String getPrimaryKey() {
        return "termID";
    }

    @Override
    public String getSecondForeignKey() {
        return "termID";
    }
}
