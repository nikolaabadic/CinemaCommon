package rs.ac.bg.fon.CinemaCommon.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User implements GenericEntity{
    private int userID;
    private String name;
    private String surname;
    private String username;
    private String password;

    public User() {
    }

    public User(int userID, String name, String surname, String username, String password) {
        this.userID = userID;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
        return "user";
    }

    @Override
    public String getColumnNamesForInsert() {
        return " name, surname, username, password ";
    }

    @Override
    public String getDeleteString() {
        return " userID= " + userID;
    }

    @Override
    public String getUpdateString() {
        return " userID= " + userID;
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
        this.userID=id;
    }

    @Override
    public List<GenericEntity> readList(ResultSet rs) throws SQLException {
        List<GenericEntity> list = new ArrayList<>();
        
        while(rs.next()){
            list.add(new User( rs.getInt("UserID"),
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
        User user = (User)entity;
        StringBuilder sb = new StringBuilder();
        sb.append( "UserID=").append(user.getUserID()).append(", ")
          .append(" Name='").append(user.getName()).append("', ")
          .append(" Surname='").append(user.getSurname()).append("', ")
          .append(" Username='").append(user.getUsername()).append("',")
          .append(" Password='").append(user.getPassword()).append("'");
        return sb.toString();
    }

    @Override
    public List<GenericEntity> getList() {
        return null;
    }

    @Override
    public String getAlias() {
        return "u";
    }

    @Override
    public String getForeignKey() {
        return "";
    }

    @Override
    public String getPrimaryKey() {
        return "userID";
    }

    @Override
    public String getSecondForeignKey() {
        return "";
    }
}
