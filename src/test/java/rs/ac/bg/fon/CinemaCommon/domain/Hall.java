package rs.ac.bg.fon.CinemaCommon.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Hall implements GenericEntity{
    int hallID;
    String name;
    int capacity;

    public Hall() {
    }

    public Hall(int salaID, String Name, int capacity) {
        this.hallID = salaID;
        this.name = Name;
        this.capacity = capacity;
    }

    public int getHallID() {
        return hallID;
    }

    public void setHallID(int hallID) {
        this.hallID = hallID;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

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
    public String getInsertValues(int id) {
        return "";
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
