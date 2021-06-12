package rs.ac.bg.fon.CinemaCommon.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation implements GenericEntity{
    private Term term;
    private User user;
    private Date reservationDate;
    private int numberOfTickets;

    public Reservation() {
    }

    public Reservation(Term term, User user, Date reservationDate, int numberOfTickets) {
        this.term = term;
        this.user = user;
        this.reservationDate = reservationDate;
        this.numberOfTickets=numberOfTickets;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

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
