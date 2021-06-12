package rs.ac.bg.fon.CinemaCommon.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 * Interface that is implemented by all domain classes. Contains methods 
 * which enable the execution of system operations.
 * 
 * @author Nikola Abadic
 *
 */
public interface GenericEntity extends Serializable{
	
	/**
	 * Returns a String value that represents the database table name for specific object.
	 * 
	 * @return Database table name as a String.
	 */
    String getTableName();

	/**
	 * Returns a String value that represents a list of column names from the database table.
	 * 
	 * @return Database column names as a String.
	 */
    String getColumnNamesForInsert();
    
	/**
	 * Returns a String value that represents a condition on which a row will be deleted from the database.
	 * 
	 * @return Condition for deleting as a String.
	 */
    String getDeleteString();
    
	/**
	 * Returns a String value that represents a condition on which a row will be updated in the database.
	 * 
	 * @return Condition for updating as a String.
	 */
    String getUpdateString();    
        
    /**
     * Returns a String value that represents a list of key-value pairs which will be updated in the database.
     * @param entity Object which will be updated in the database.
     * @return Key-value pairs of update values as a String.
     */
    String getUpdateValues(GenericEntity entity);

	/**
	 * Returns a String value that represents a list of key-value pairs which will be inserted in the database.
	 * 
	 * @return Key-value pairs of insert values as a String.
	 */
    String getInsertValues();
        
	/**
	 * Returns a String value that represents an alias for the table name from the database.
	 * 
	 * @return Table name alias as a String.
	 */
    String getAlias();
    
	/**
	 * Returns a String value that represents the foreign key column name from the database table.
	 * 
	 * @return Foreign key column name as a String.
	 */
    String getForeignKey();
    
	/**
	 * Returns a String value that represents the second foreign key column name from the database table.
	 * 
	 * @return Second foreign key column name as a String.
	 */
    String getSecondForeignKey();
    
	/**
	 * Returns a String value that represents the primary key column name from the database table.
	 * 
	 * @return Primary key column name as a String.
	 */
    String getPrimaryKey();

    /**
     * Sets the given value to the primary key.
     * @param id New primary key value.
     */
    void setId(int id);
    
    /**
     * Returns a list of objects, if the class has a List type attribute. If not, it returns null.
     * @return List of objects equaled to the class attribute.
     */
    List<GenericEntity> getList();
        
    /**
     * Returns a list of objects read from the given result set.
     * @param rs Result set which represents a SQL query result.
     * @return List of objects read from the given result set.
     * @throws java.lang.Exception if an unmatched key value is provided.
     */
    List<GenericEntity> readList(ResultSet rs) throws Exception;
}
