package rs.ac.bg.fon.CinemaCommon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import rs.ac.bg.fon.CinemaCommon.domain.Admin;
import rs.ac.bg.fon.CinemaCommon.domain.GenericEntity;

public class AdminTest {

	private Admin admin;
	@Mock
	ResultSet resultSet;
	
	@BeforeEach
	void setUp() throws Exception{
		admin = new Admin();
	}
	
	@AfterEach
	void tearDown() throws Exception{
		admin = null;
		resultSet = null;
	}
	
	@Test
	void testNonParameterizedConstructror(){
		admin = new Admin();
		assertNotNull(admin);
	}
	
	@Test
	void testParameterizedConstructor() {
		admin = new Admin(1, "name", "lastname", "username", "password");
		assertNotNull(admin);
		assertEquals(1, admin.getAdminID());
		assertEquals("name", admin.getName());
		assertEquals("lastname", admin.getSurname());
		assertEquals("username", admin.getUsername());
		assertEquals("password", admin.getPassword());
	}
	
	@Test
	void testUserId() {
		admin.setAdminID(1);
		assertEquals(1, admin.getAdminID());
	}
	
	@Test
	void testName() {
		admin.setName("name");
		assertEquals("name", admin.getName());
	}
	
	@Test
	void testLastname() {
		admin.setSurname("lastname");
		assertEquals("lastname", admin.getSurname());
	}
	
	@Test
	void testUsername() {
		admin.setUsername("username");
		assertEquals("username", admin.getUsername());
	}
	
	@Test
	void testPassword() {
		admin.setPassword("password");
		assertEquals("password", admin.getPassword());
	}
	
	@Test
	void testToString() {
		admin.setUsername("username");
		assertEquals("username", admin.toString());
	}
	
	@Test
	void testGetTableName() {
		assertEquals("admin", admin.getTableName());
	}
	
	@Test
	void testGetColumnNamesForInsert() {
		assertEquals(" name, surname, username, password ", admin.getColumnNamesForInsert());
	}
	
	@Test
	void testGetInsertValues() {
		admin = new Admin(1, "name", "lastname", "username", "password");
		assertEquals("'name','lastname','username','password'" , admin.getInsertValues());
	}
	
	@Test
	void testSetId() {
		admin.setId(1);
		assertEquals(1, admin.getAdminID());
	}
	
	@Test
	void testGetDeleteString() {
		admin.setAdminID(1);
		assertEquals(" adminID= 1", admin.getDeleteString());
	}
	
	@Test
	void testGetUpdateString() {
		admin.setAdminID(1);
		assertEquals(" adminID= 1", admin.getUpdateString());
	} 
	
	@Test
	void testGetAlias() {
		assertEquals("a", admin.getAlias());
	}
	
	@Test 
	void testGetForeignKey() {
		assertEquals("", admin.getForeignKey());
	}
	
	@Test 
	void testGetSecondForeignKey() {
		assertEquals("", admin.getSecondForeignKey());
	}
	
	@Test 
	void testGetPrimaryKey() {
		assertEquals("adminId", admin.getPrimaryKey());
	}
	
	@Test
	void testGetList() {
		assertNull(admin.getList());
	}
	
	@Test
	void testGetUpdateValues() {
		admin = new Admin(1, "name", "lastname", "username", "password");
		assertEquals("AdminID=1,  Name='name',  Surname='lastname',  Username='username', Password='password'", admin.getUpdateValues(admin));
	}
		
	@Test
	void testReadList(){
        try {
        	resultSet = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet.getInt("AdminID")).thenReturn(1);
            Mockito.when(resultSet.getString("Name")).thenReturn("name");
            Mockito.when(resultSet.getString("Surname")).thenReturn("lastname");
            Mockito.when(resultSet.getString("Username")).thenReturn("username");
            Mockito.when(resultSet.getString("Password")).thenReturn("password");
        	Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        	
            List<GenericEntity> admins = admin.readList(resultSet);
            Admin a = (Admin)admins.get(0);

            assertNotNull(admins);
            assertNotNull(a);
    		assertEquals(1, a.getAdminID());
    		assertEquals("name", a.getName());
    		assertEquals("lastname", a.getSurname());
    		assertEquals("username", a.getUsername());
    		assertEquals("password", a.getPassword());
            
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}
}
