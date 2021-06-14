package rs.ac.bg.fon.CinemaCommon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import rs.ac.bg.fon.CinemaCommon.domain.Film;
import rs.ac.bg.fon.CinemaCommon.domain.GenericEntity;
import rs.ac.bg.fon.CinemaCommon.domain.Term;

public abstract class GenericEntityTest {
	
	protected GenericEntity film;
	protected ResultSet ResultSet;
		
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
		assertEquals(1, ((Film)film).getFilmID());
	}
	
	@Test
	void testGetDeleteString() {
		((Film)film).setFilmID(1);
		assertEquals(" FilmID=1", film.getDeleteString());
	}
	
	@Test
	void testGetUpdateString() {
		((Film)film).setFilmID(1);
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
		terms.add(new Term(1, new Date(), "2D", ((Film)film), null));
		
		((Film)film).setTerms(terms);
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
        	
            List<GenericEntity> films = ((Film)film).readList(resultSet);
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
