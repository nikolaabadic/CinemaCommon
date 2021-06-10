package rs.ac.bg.fon.CinemaCommon.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Admin implements GenericEntity {
    private int adminID;
    private String name;
    private String surname;
    private String username;
    private String password;

    public Admin() {
    }

    public Admin(int adminID, String name, String surname, String username, String password) {
        this.adminID = adminID;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public String getTableName() {
        return "admin";
    }

    @Override
    public String getColumnNamesForInsert() {
        return " name, surname, username, password ";
    }

    @Override
    public String getDeleteString() {
        return " adminID= " + adminID;
    }
    
    @Override
    public String getUpdateString() {
        return " adminID= " + adminID;
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(name).append("',")
                .append("'").append(surname).append("',")
                .append("'").append(username).append("',")
                .append("'").append(password).append("'");
        return sb.toString();
    }

    @Override
    public void setId(int id) {
        this.adminID=id;
    }

    @Override
    public List<GenericEntity> readList(ResultSet rs) throws SQLException {
        List<GenericEntity> list = new ArrayList<>();
        
        while(rs.next()){
            list.add(new Admin( rs.getInt("AdminID"),
                               rs.getString("Name"),
                               rs.getString("Surname"),
                               rs.getString("Username"),
                               rs.getString("Password")      
            ));
        }        
        
        return list;
    }

    @Override
    public String getUpdateValues(GenericEntity entity) {
        Admin admin = (Admin)entity;
        StringBuilder sb = new StringBuilder();
        sb.append( "AdminID=").append(admin.getAdminID()).append(", ")
          .append(" Name='").append(admin.getName()).append("', ")
          .append(" Surname='").append(admin.getSurname()).append("', ")
          .append(" Username='").append(admin.getUsername()).append("',")
          .append(" Password='").append(admin.getPassword()).append("'");
        return sb.toString();
    }

    @Override
    public List<GenericEntity> getList() {
        return null;
    }


    @Override
    public String getAlias() {
        return "a";
    }

    @Override
    public String getForeignKey() {
        return "";
    }

    @Override
    public String getPrimaryKey() {
        return "adminId";
    }

    @Override
    public String getSecondForeignKey() {
        return "";
    }
}
