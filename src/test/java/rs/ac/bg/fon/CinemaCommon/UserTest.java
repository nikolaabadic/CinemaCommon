package rs.ac.bg.fon.CinemaCommon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import rs.ac.bg.fon.CinemaCommon.domain.Film;
import rs.ac.bg.fon.CinemaCommon.domain.GenericEntity;
import rs.ac.bg.fon.CinemaCommon.domain.Term;
import rs.ac.bg.fon.CinemaCommon.domain.User;

public class UserTest {

	private User user;
	@Mock
	ResultSet resultSet;
	
	@BeforeEach
	void setUp() throws Exception{
		user = new User();
	}
	
	@AfterEach
	void tearDown() throws Exception{
		user = null;
		resultSet = null;
	}
	
	@Test
	void testNonParameterizedConstructror(){
		user = new User();
		assertNotNull(user);
	}
	
	void testParameterizedConstructor() {
		user = new User(1, "name", "lastname", "username", "password");
		assertNotNull(user);
		assertEquals(1, user.getUserID());
		assertEquals("name", user.getName());
		assertEquals("lastname", user.getSurname());
		assertEquals("username", user.getUsername());
		assertEquals("password", user.getPassword());
	}
	
	@Test
	void testUserId() {
		user.setUserID(1);
		assertEquals(1, user.getUserID());
	}
	
	@Test
	void testName() {
		user.setName("name");
		assertEquals("name", user.getName());
	}
	
	@Test
	void testLastname() {
		user.setSurname("lastname");
		assertEquals("lastname", user.getSurname());
	}
	
	@Test
	void testUsername() {
		user.setUsername("username");
		assertEquals("username", user.getUsername());
	}
	
	@Test
	void testPassword() {
		user.setPassword("password");
		assertEquals("password", user.getPassword());
	}
	
	@Test
	void testToString() {
		user.setUsername("username");
		assertEquals("username", user.toString());
	}
	
	@Test
	void testGetTableName() {
		assertEquals("user", user.getTableName());
	}
	
	@Test
	void testGetColumnNamesForInsert() {
		assertEquals(" name, surname, username, password ", user.getColumnNamesForInsert());
	}
	
	@Test
	void testGetInsertValues() {
		user = new User(1, "name", "lastname", "username", "password");
		assertEquals("'name','lastname','username','password'" , user.getInsertValues());
	}
	
	@Test
	void testSetId() {
		user.setId(1);
		assertEquals(1, user.getUserID());
	}
	
	@Test
	void testGetDeleteString() {
		user.setUserID(1);
		assertEquals(" userID= 1", user.getDeleteString());
	}
	
	@Test
	void testGetUpdateString() {
		user.setUserID(1);
		assertEquals(" userID= 1", user.getUpdateString());
	} 
	
	@Test
	void testGetAlias() {
		assertEquals("u", user.getAlias());
	}
	
	@Test 
	void testGetForeignKey() {
		assertEquals("", user.getForeignKey());
	}
	
	@Test 
	void testGetSecondForeignKey() {
		assertEquals("", user.getSecondForeignKey());
	}
	
	@Test 
	void testGetPrimaryKey() {
		assertEquals("userID", user.getPrimaryKey());
	}
	
	@Test
	void testGetList() {
		assertNull(user.getList());
	}
	
	@Test
	void testGetUpdateValues() {
		user = new User(1, "name", "lastname", "username", "password");
		assertEquals("UserID=1,  Name='name',  Surname='lastname',  Username='username', Password='password'", user.getUpdateValues(user));
	}
		
	@Test
	void testReadList(){
        try {
        	resultSet = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet.getInt("UserID")).thenReturn(1);
            Mockito.when(resultSet.getString("Name")).thenReturn("name");
            Mockito.when(resultSet.getString("Surname")).thenReturn("lastname");
            Mockito.when(resultSet.getString("Username")).thenReturn("username");
            Mockito.when(resultSet.getString("Password")).thenReturn("password");
        	Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        	
            List<GenericEntity> users = user.readList(resultSet);
            User u = (User)users.get(0);

            assertNotNull(users);
            assertNotNull(u);
    		assertEquals(1, u.getUserID());
    		assertEquals("name", u.getName());
    		assertEquals("lastname", u.getSurname());
    		assertEquals("username", u.getUsername());
    		assertEquals("password", u.getPassword());
            
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}
	
}
