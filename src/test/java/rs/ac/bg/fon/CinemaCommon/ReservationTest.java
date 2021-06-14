package rs.ac.bg.fon.CinemaCommon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import rs.ac.bg.fon.CinemaCommon.domain.Film;
import rs.ac.bg.fon.CinemaCommon.domain.GenericEntity;
import rs.ac.bg.fon.CinemaCommon.domain.Hall;
import rs.ac.bg.fon.CinemaCommon.domain.Reservation;
import rs.ac.bg.fon.CinemaCommon.domain.Term;
import rs.ac.bg.fon.CinemaCommon.domain.User;

public class ReservationTest {
	private Reservation reservation;
	private Term term;
	private User user;
	
	@Mock
	ResultSet resultSet;
	
	@BeforeEach
	void setUp() throws Exception{
		Date date = new Date();
		user = new User(1, "name", "lastname", "username", "password");
		term = new Term(1, new Date(), "2D", new Film(), new Hall());
		reservation = new Reservation(term, user, date, 2);
	}
	
	@AfterEach
	void tearDown() throws Exception{
		reservation = null;
		resultSet = null;
	}
	
	@Test
	void testNonParameterizedConstructror(){
		reservation = new Reservation();
		assertNotNull(reservation);
	}
	
	@Test
	void testParameterizedConstructor() {
		Date date = new Date();
		reservation = new Reservation(term, user, date, 2);
		assertNotNull(reservation);
		assertEquals(term, reservation.getTerm());
		assertEquals(user, reservation.getUser());
		assertEquals(date, reservation.getReservationDate());
		assertEquals(2, reservation.getNumberOfTickets());
	}
	
	@Test
	void testTerm() {
		reservation.setTerm(term);
		assertEquals(term, reservation.getTerm());
	}
	
	@Test
	void testUser() {
		reservation.setUser(user);
		assertEquals(user, reservation.getUser());
	}
	
	@Test
	void testDate() {
		Date date = new Date();
		reservation.setReservationDate(date);
		assertEquals(date, reservation.getReservationDate());
	}
	
	@Test
	void testNumberOfTickets() {
		reservation.setNumberOfTickets(2);
		assertEquals(2, reservation.getNumberOfTickets());
	}
	@Test
	void testGetTableName() {
		assertEquals("Reservation", reservation.getTableName());
	}
	
	@Test
	void testGetColumnNamesForInsert() {
		assertEquals(" Date, Number, TermID, UserID", reservation.getColumnNamesForInsert());
	}
	
	@Test
	void testGetInsertValues() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatedDate = sdf.format(date);
		reservation = new Reservation(term, user, date, 2);
		assertEquals("'"+ formatedDate+"',2,1,1" , reservation.getInsertValues());
	}
	
	@Test
	void testSetId() {
		assertThrows(java.lang.UnsupportedOperationException.class, 
				() -> reservation.setId(1));
	}
	
	@Test
	void testGetDeleteString() {
		assertEquals(" TermID=1 && UserID=1", reservation.getDeleteString());
	}
	
	@Test
	void testGetUpdateString() {
		assertEquals(" TermID=1 && UserID=1", reservation.getUpdateString());
	} 
	
	@Test
	void testGetAlias() {
		assertEquals("r", reservation.getAlias());
	}
	
	@Test 
	void testGetForeignKey() {
		assertEquals("userID", reservation.getForeignKey());
	}
	
	@Test 
	void testGetSecondForeignKey() {
		assertEquals("termID", reservation.getSecondForeignKey());
	}
	
	@Test 
	void testGetPrimaryKey() {
		assertEquals("termID", reservation.getPrimaryKey());
	}
	
	@Test
	void testGetList() {
		assertNull(reservation.getList());
	}
	
	@Test
	void testGetUpdateValues() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatedDate = sdf.format(date);
		reservation = new Reservation(term, user, date, 2);
		assertEquals(" UserID=1,  TermID=1,  ReservationDate='" + formatedDate + "',"
				+ "  NumberOfTickets=2", reservation.getUpdateValues(reservation));
	}
		
	@Test
	void testReadList(){
        try {
        	resultSet = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet.getInt("TermID")).thenReturn(1);
            Mockito.when(resultSet.getInt("UserID")).thenReturn(1);
            Mockito.when(resultSet.getTimestamp("Date")).thenReturn(new Timestamp(0));
            Mockito.when(resultSet.getInt("Number")).thenReturn(2);
        	Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        	
            List<GenericEntity> reservations = reservation.readList(resultSet);
            Reservation r = (Reservation)reservations.get(0);

            assertNotNull(reservations);
            assertNotNull(r);
    		assertEquals(1, r.getTerm().getTermID());
      		assertEquals(1, r.getUser().getUserID());
    		assertEquals(2, r.getNumberOfTickets());
    		
    		Date date = new Date(new Timestamp(0).getTime());
    		assertEquals(date, r.getReservationDate());
    		
    		            
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}
}
