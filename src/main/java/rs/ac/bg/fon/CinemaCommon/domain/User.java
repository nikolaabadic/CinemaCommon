package rs.ac.bg.fon.CinemaCommon.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Movie theater customer class.
 * @author Nikola Abadic
 *
 */
public class User implements GenericEntity{
	/**
	 * User identification number.
	 */
    private int userID;
    /**
     * User's first name.
     */
    private String name;
    /**
     * User's last name.
     */
    private String surname;
    /**
     * Profile username.
     */
    private String username;
    /**
     * Profile password.
     */
    private String password;

    /**
     * Non-parameterized constructor used for initialization of User type objects.
     */
    public User() {
    }

    /**
     * Parameterized constructor used for initialization of User type objects and setting given values.
     * @param userID User identification number as an integer.
     * @param name User's first name as a String.
     * @param surname User's last name as a String.
     * @param username Profile username as a String.
     * @param password Profile password as a String.
     */
    public User(int userID, String name, String surname, String username, String password) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    /**
     * Returns user's password.
     * @return Password as a String.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Sets user's password.
     * @param password Password as a String.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /** 
     * Returns user identification number.
     * @return User identification number as an integer.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the user identification number.
     * @param userID User identification number as an integer.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    /**
     * Returns user's first name.
     * @return User's first name as a String.
     */
    public String getName() {
        return name;
    }
    
    /** 
     * Sets user's first name.
     * @param name User first name as a String.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns user's last name.
     * @return User's last name as a String.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets user's last name.
     * @param surname User's last name as a String.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    /**
     * Returns profile username.
     * @return Username as a String.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets the profile username.
     * @param username Username as a String.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Returns profile username.
     */
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
