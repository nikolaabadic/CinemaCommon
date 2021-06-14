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

import rs.ac.bg.fon.CinemaCommon.domain.GenericEntity;
import rs.ac.bg.fon.CinemaCommon.domain.Hall;

public class HallTest {
	private Hall hall;
	@Mock
	ResultSet resultSet;
	
	@BeforeEach
	void setUp() throws Exception{
		hall = new Hall();
	}
	
	@AfterEach
	void tearDown() throws Exception{
		hall = null;
		resultSet = null;
	}
	
	@Test
	void testNonParameterizedConstructror(){
		hall = new Hall();
		assertNotNull(hall);
	}
	
	@Test
	void testParameterizedConstructor() {
		hall = new Hall(1, "name", 10);
		assertNotNull(hall);
		assertEquals(1, hall.getHallID());
		assertEquals("name", hall.getName());
		assertEquals(10, hall.getCapacity());
	}
	
	@Test
	void testHallID() {
		hall.setHallID(1);
		assertEquals(1, hall.getHallID());
	}
	
	@Test
	void testName() {
		hall.setName("name");
		assertEquals("name", hall.getName());
	}
	
	@Test
	void testCapacity() {
		hall.setCapacity(10);
		assertEquals(10, hall.getCapacity());
	}
	
	@Test 
	void testToString() {
		hall.setName("name");
		assertEquals("name", hall.toString());
	}

	@Test
	void testGetTableName() {
		assertEquals("hall", hall.getTableName());
	}
	
	@Test
	void testGetColumnNamesForInsert() {
		assertEquals(" name,capacity ", hall.getColumnNamesForInsert());
	}
	
	@Test
	void testGetInsertValues() {
		hall = new Hall(1, "name", 10);
		assertEquals("'name',10" , hall.getInsertValues());
	}
	
	@Test
	void testSetId() {
		hall.setId(1);
		assertEquals(1, hall.getHallID());
	}
	
	@Test
	void testGetDeleteString() {
		hall.setHallID(1);
		assertEquals(" HallID=1", hall.getDeleteString());
	}
	
	@Test
	void testGetUpdateString() {
		hall.setHallID(1);
		assertEquals(" hallID= 1", hall.getUpdateString());
	} 
	
	@Test
	void testGetAlias() {
		assertEquals(" h", hall.getAlias());
	}
	
	@Test 
	void testGetForeignKey() {
		assertEquals("", hall.getForeignKey());
	}
	
	@Test 
	void testGetSecondForeignKey() {
		assertEquals("", hall.getSecondForeignKey());
	}
	
	@Test 
	void testGetPrimaryKey() {
		assertEquals("hallID", hall.getPrimaryKey());
	}
	
	@Test
	void testGetList() {
		assertNull(hall.getList());
	}
	
	@Test
	void testGetUpdateValues() {
		hall = new Hall(1, "name", 10);
		assertEquals("HallID=1,  Name='name',  Capacity=10", hall.getUpdateValues(hall));
	}
		
	@Test
	void testReadList(){
        try {
        	resultSet = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet.getInt("HallID")).thenReturn(1);
            Mockito.when(resultSet.getString("Name")).thenReturn("name");
            Mockito.when(resultSet.getInt("Capacity")).thenReturn(10);
        	Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        	
            List<GenericEntity> halls = hall.readList(resultSet);
            Hall h = (Hall)halls.get(0);

            assertNotNull(halls);
            assertNotNull(h);
    		assertEquals(1, h.getHallID());
    		assertEquals("name", h.getName());
    		assertEquals(10, h.getCapacity());
            
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}
}
