package rs.ac.bg.fon.CinemaCommon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

public class FilmTest{
	
	private Film film;
	
	@BeforeEach
	void setUp() throws Exception{
		film = new Film();
	}
	
	@AfterEach
	void tearDown() throws Exception{
		film = null;
		resultSet = null;
	}
	
	@Test
	void testNonParameterizedConstructror(){
		film = new Film();
		assertNotNull(film);
	}
	
	@Test 
	void testParameterizedConstructor() {
		film = new Film(1, "test", 2021, 180, "eng");
		assertNotNull(film);
		assertEquals(1, film.getFilmID());
		assertEquals("test", film.getName());
		assertEquals(2021, film.getYear());
		assertEquals(180, film.getDuration());
		assertEquals("eng", film.getLanguage());
	}
	
	@Test
	void testName() {
		film.setName("test");
		assertEquals("test", film.getName());
	}
	
	@Test
	void testFilmID() {
		film.setFilmID(1);
		assertEquals(1, film.getFilmID());
	}
	
	@Test
	void testDuration() {
		film.setDuration(100);
		assertEquals(100, film.getDuration());
	}
	
	@Test
	void testLanguage() {
		film.setLanguage("test");
		assertEquals("test", film.getLanguage());
	}
	
	@Test
	void testYear() {
		film.setYear(2021);
		assertEquals(2021, film.getYear());
	}
	
	@Test
	void testTerms() {
		List<Term> terms = new ArrayList<>();
		terms.add(new Term(1, new Date(), "2D", film, null));
		
		film.setTerms(terms);
		assertEquals(terms, film.getTerms());
	}
	
	@Test
	void testToString() {
		film.setName("test");
		assertEquals("test", film.toString());
	}
	
	@Test
	void testGetTableName() {
		assertEquals("Film", film.getTableName());
	}
	
	@Test
	void testGetColumnNamesForInsert() {
		assertEquals("Name,Year,Duration,Language", film.getColumnNamesForInsert());
	}
	
	@Test
	void testGetInsertValues() {
		film = new Film(1, "test", 2021, 180, "eng");
		assertEquals("'test',2021,180,'eng'" , film.getInsertValues());
	}
	
	@Test
	void testSetId() {
		film.setId(1);
		assertEquals(1, film.getFilmID());
	}
	
	@Test
	void testGetDeleteString() {
		film.setFilmID(1);
		assertEquals(" FilmID=1", film.getDeleteString());
	}
	
	@Test
	void testGetUpdateString() {
		film.setFilmID(1);
		assertEquals(" filmID= 1", film.getUpdateString());
	} 
	
	@Test
	void testGetAlias() {
		assertEquals(" f", film.getAlias());
	}
	
	@Test 
	void testGetForeignKey() {
		assertEquals("", film.getForeignKey());
	}
	
	@Test 
	void testGetSecondForeignKey() {
		assertEquals("", film.getSecondForeignKey());
	}
	
	@Test 
	void testGetPrimaryKey() {
		assertEquals("filmID", film.getPrimaryKey());
	}
	
	@Test
	void testGetList() {
		List<Term> terms = new ArrayList<>();
		terms.add(new Term(1, new Date(), "2D", film, null));
		
		film.setTerms(terms);
		assertEquals(terms, film.getList());
	}
	
	@Test
	void testGetUpdateValues() {
		film = new Film(1, "test", 2021, 180, "eng");
		assertEquals("FilmID=1,  Name='test',  Duration=180,  Language='eng', Year=2021", film.getUpdateValues(film));
	}
	
	@Mock
	ResultSet resultSet;
	
	@Test
	void testReadList(){
        try {
        	resultSet = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet.getInt("FilmID")).thenReturn(1);
            Mockito.when(resultSet.getString("Name")).thenReturn("test");
            Mockito.when(resultSet.getString("Language")).thenReturn("eng");
            Mockito.when(resultSet.getInt("Year")).thenReturn(2021);
            Mockito.when(resultSet.getInt("Duration")).thenReturn(180);
        	Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        	
            List<GenericEntity> films = film.readList(resultSet);
            Film f = (Film)films.get(0);

            assertNotNull(films);
            assertNotNull(f);
    		assertEquals("test", f.getName());
    		assertEquals(1, f.getFilmID());
    		assertEquals(2021, f.getYear());
    		assertEquals(180, f.getDuration());
    		assertEquals("eng", f.getLanguage());
            
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}
}
