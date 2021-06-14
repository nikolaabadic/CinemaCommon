package rs.ac.bg.fon.CinemaCommon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.CinemaCommon.domain.Film;
import rs.ac.bg.fon.CinemaCommon.domain.Term;

public class FilmTest extends GenericEntityTest{
	
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
		assertEquals(1, ((Film)film).getFilmID());
		assertEquals("test", ((Film)film).getName());
		assertEquals(2021, ((Film)film).getYear());
		assertEquals(180, ((Film)film).getDuration());
		assertEquals("eng", ((Film)film).getLanguage());
	}
	
	@Test
	void testName() {
		((Film)film).setName("test");
		assertEquals("test", ((Film)film).getName());
	}
	
	@Test
	void testFilmID() {
		((Film)film).setFilmID(1);
		assertEquals(1, ((Film)film).getFilmID());
	}
	
	@Test
	void testDuration() {
		((Film)film).setDuration(100);
		assertEquals(100, ((Film)film).getDuration());
	}
	
	@Test
	void testLanguage() {
		((Film)film).setLanguage("test");
		assertEquals("test", ((Film)film).getLanguage());
	}
	
	@Test
	void testYear() {
		((Film)film).setYear(2021);
		assertEquals(2021, ((Film)film).getYear());
	}
	
	@Test
	void testTerms() {
		List<Term> terms = new ArrayList<>();
		terms.add(new Term(1, new Date(), "2D", ((Film)film), null));
		
		((Film)film).setTerms(terms);
		assertEquals(terms, ((Film)film).getTerms());
	}
	
	@Test
	void testToString() {
		((Film)film).setName("test");
		assertEquals("test", ((Film)film).toString());
	}
}
