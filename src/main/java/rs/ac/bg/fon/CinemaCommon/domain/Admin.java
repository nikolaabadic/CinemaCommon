package rs.ac.bg.fon.CinemaCommon.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Admin class.
 * @author Nikola Abadic
 *
 */
public class Admin implements GenericEntity {
	/**
	 * Administrator identification number.
	 */
    private int adminID;
    /**
     * Administrator's first name.
     */
    private String name;
    /**
     * Administrator's last name.
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
     * Non-parameterized constructor used for initialization of Admin type objects.
     */
    public Admin() {
    }

    /**
     * Parameterized constructor used for initialization of Admin type objects.
     * @param adminID User identification number as an integer.
     * @param name User's first name as a String.
     * @param surname User's last name as a String.
     * @param username Profile username as a String.
     * @param password Profile password as a String.
     */
    public Admin(int adminID, String name, String surname, String username, String password) {
        this.adminID = adminID;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }
    
    /**
     * Returns profile password.
     * @return Password as a String.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets profile password.
     * @param password Password as a String.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /** 
     * Returns administrator identification number.
     * @return Administrator identification number as an integer.
     */
    public int getAdminID() {
        return adminID;
    }

    /**
     * Sets the administrator identification number.
     * @param adminID Administrator identification number as an integer.
     */
    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }
    /**
     * Returns administrator's first name.
     * @return Administrator's first name as a String.
     */
    public String getName() {
        return name;
    }
    /** 
     * Sets administrator's first name.
     * @param name Administrator's first name as a String.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns administrator's last name.
     * @return Administrator's last name as a String.
     */
    public String getSurname() {
        return surname;
    }
    /**
     * Sets administrator's last name.
     * @param surname Administrator's last name as a String.
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
