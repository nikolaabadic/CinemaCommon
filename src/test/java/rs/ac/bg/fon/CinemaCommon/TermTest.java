package rs.ac.bg.fon.CinemaCommon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import rs.ac.bg.fon.CinemaCommon.domain.Film;
import rs.ac.bg.fon.CinemaCommon.domain.GenericEntity;
import rs.ac.bg.fon.CinemaCommon.domain.Hall;
import rs.ac.bg.fon.CinemaCommon.domain.Term;

public class TermTest {
	private Term term;
	private Film film;
	private Hall hall;
	
	@Mock
	ResultSet resultSet;
	
	@BeforeEach
	void setUp() throws Exception{
		Date date = new Date();
		film = new Film(1, "name", 2021, 180, "eng");
		hall = new Hall(1, "name", 100);
		term = new Term(1, date, "2D", film, hall);
	}
	
	@AfterEach
	void tearDown() throws Exception{
		term = null;
		resultSet = null;
	}

	@Test
	void testNonParameterizedConstructror(){
		term = new Term();
		assertNotNull(term);
	}
	
	@Test
	void testParameterizedConstructror(){
		Date date = new Date();
		term = new Term(1, date, "2D", film, hall);
		assertNotNull(term);
		assertEquals(1, term.getTermID());
		assertEquals(date, term.getDate());
		assertEquals("2D", term.getProjectionType());
		assertEquals(film, term.getFilm());
		assertEquals(hall, term.getHall());
	}
	
	@Test
	void testTermID() {
		term.setTermID(1);
		assertEquals(1, term.getTermID());
	}
	
	@Test
	void testDate() {
		Date date = new Date();
		term.setDate(date);
		assertEquals(date, term.getDate());
	}
	
	@Test
	void testFilm() {
		term.setFilm(film);
		assertEquals(film, term.getFilm());
	}
	
	@Test
	void testHall() {
		term.setHall(hall);
		assertEquals(hall, term.getHall());
	}
	
	@Test
	void testProjectionType() {
		term.setProjectionType("2D");
		assertEquals("2D", term.getProjectionType());
	}
	
	@Test
	void testToString() {
		assertEquals("name " + term.getDate() + " name 2D", term.toString());	
	}
	
	
	Date dt = java.util.Date.from(LocalDate.of(2021, 1, 1).atStartOfDay(                       
	        ZoneId.of( "America/Montreal" )).toInstant());
	@ParameterizedTest
	@CsvSource({
		"2D, 1, 2021-01-01, true",
		"3D, 1, 2021-01-01, false",
		"2D, 2, 2021-01-01, false",
		"2D, 1, 2021-01-02, false"
	})
	void testEquals(String projectionType, int hallID, LocalDate termDate, boolean result) {
		term.setDate(dt);
		Hall hall2 = new Hall(hallID, "name", 100);
		Term term2 = new Term(2, java.util.Date.from(
			    termDate                          
			    .atStartOfDay(                       
			        ZoneId.of( "America/Montreal" )   
			    )                                     
			    .toInstant()                         
			), projectionType, new Film(), hall2);
		
		assertEquals(result, term.equals(term2));
	}
	
	@Test
	void testGetTableName() {
		assertEquals("Term", term.getTableName());
	}
	
	@Test
	void testGetColumnNamesForInsert() {
		assertEquals(" Date,Type,FilmID,HallID ", term.getColumnNamesForInsert());
	}
	
	@Test
	void testGetInsertValues() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatedDate = sdf.format(term.getDate());
		assertEquals("'"+ formatedDate+"','2D',1,1", term.getInsertValues());
	}
	
	@Test
	void testSetId() {
		term.setId(1);
		assertEquals(1, term.getTermID());
	}
	
	@Test
	void testGetDeleteString() {
		assertEquals(" TermID=1", term.getDeleteString());
	}
	
	@Test
	void testGetUpdateString() {
		assertEquals(" TermID=1", term.getUpdateString());
	} 
	
	@Test
	void testGetAlias() {
		assertEquals(" t", term.getAlias());
	}
	
	@Test 
	void testGetForeignKey() {
		assertEquals("filmID", term.getForeignKey());
	}
	
	@Test 
	void testGetSecondForeignKey() {
		assertEquals("hallID", term.getSecondForeignKey());
	}
	
	@Test 
	void testGetPrimaryKey() {
		assertEquals("termID", term.getPrimaryKey());
	}
	
	@Test
	void testGetList() {
		assertNull(term.getList());
	}
	
	@Test
	void testGetUpdateValues() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatedDate = sdf.format(term.getDate());
		assertEquals("FilmID=1,  HallID=1,  Date='" + formatedDate + "',"
				+ "  Type='2D'", term.getUpdateValues(term));
	}
		
	@Test
	void testReadList() throws Exception{
        try {
        	resultSet = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet.getInt("TermID")).thenReturn(1);
            Mockito.when(resultSet.getInt("FilmID")).thenReturn(1);
            Mockito.when(resultSet.getInt("HallID")).thenReturn(1);
            Mockito.when(resultSet.getTimestamp("Date")).thenReturn(new Timestamp(0));
            Mockito.when(resultSet.getString("Type")).thenReturn("2D");
        	Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        	
            List<GenericEntity> terms = term.readList(resultSet);
            Term t = (Term)terms.get(0);

            assertNotNull(terms);
            assertNotNull(t);
    		assertEquals(1, t.getTermID());
    		assertEquals(1, t.getHall().getHallID());
    		assertEquals(1, t.getFilm().getFilmID());
    		assertEquals("2D", t.getProjectionType());
    		
    		Date date = new Date(new Timestamp(0).getTime());
    		assertEquals(date, t.getDate());    		
    		            
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}
}
