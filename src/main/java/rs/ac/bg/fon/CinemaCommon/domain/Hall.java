package rs.ac.bg.fon.CinemaCommon.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Movie theater hall class.
 * @author Nikola Abadic
 *
 */
public class Hall implements GenericEntity{
	/**
	 * Integer value for the hall identification number.
	 */
    int hallID;
    /**
     * Hall name.
     */
    String name;
    /**
     * Integer value for the number of seats in the hall.
     */
    int capacity;

    /**
     * Non-parameterized constructor used for initialization of Hall type objects.
     */
    public Hall() {
    }

    /**
     * Parameterized constructor used for initialization of Hall type objects.
     * @param salaID Hall identification number as an integer.
     * @param Name Hall name as a String.
     * @param capacity Number of seats as an integer.
     */
    public Hall(int salaID, String Name, int capacity) {
        this.hallID = salaID;
        this.name = Name;
        this.capacity = capacity;
    }
    
    /**
     * Returns the hall identification number.
     * @return Hall identification number as an integer.
     */
    public int getHallID() {
        return hallID;
    }
    
    /**
     * Sets the hall identification number.
     * @param hallID Hall identification number as an integer.
     */
    public void setHallID(int hallID) {
        this.hallID = hallID;
    }

    /**
     * Returns a String value of the hall name.
     * @return Hall name as a String.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the hall name.
     * @param Name Hall name as a String.
     */
    public void setName(String Name) {
        this.name = Name;
    }
    
    /**
     * Returns the number of seats.
     * @return Number of seats as an integer.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the number of seats in the hall.
     * @param capacity Number of seats as an integer.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns the hall name.
     */
    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getTableName() {
        return "hall";
    }

    @Override
    public String getColumnNamesForInsert() {
        return " name,capacity ";
    }

    @Override
    public String getDeleteString() {
        return " HallID=" + hallID;
    }

    @Override
    public String getUpdateString() {
        return " hallID= " + hallID;
    }
    
    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(name).append("',")
                .append(capacity);
        return sb.toString();
    }

    @Override
    public void setId(int id) {
        this.hallID = id;
    }

    @Override
    public List<GenericEntity> readList(ResultSet rs) throws SQLException {
        List<GenericEntity> list = new ArrayList<>();
        
        while(rs.next()){
            list.add(new Hall( rs.getInt("HallID"),
                               rs.getString("Name"),
                               rs.getInt("Capacity")
            ));
        }        
        
        return list;
    }

    @Override
    public String getUpdateValues(GenericEntity entity) {
        Hall hall = (Hall)entity;
        StringBuilder sb = new StringBuilder();
        sb.append( "HallID=").append(hall.getHallID()).append(", ")
          .append(" Name='").append(hall.getName()).append("', ")
          .append(" Capacity=").append(hall.getCapacity());
        return sb.toString();
    }

    @Override
    public List<GenericEntity> getList() {
        return null;
    }

    @Override
    public String getAlias() {
        return " h";
    }

    @Override
    public String getForeignKey() {
        return "";
    }

    @Override
    public String getPrimaryKey() {
        return "hallID";
    }

    @Override
    public String getSecondForeignKey() {
        return "";
    }
}
