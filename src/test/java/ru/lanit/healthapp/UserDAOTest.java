package ru.lanit.healthapp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.hibernate.Session;

import ru.lanit.healthapp.repositories.UserDAO;
import ru.lanit.healthapp.repositories.UserDAOImpl;

@ExtendWith(SpringExtension.class)
@Tag("DAO")
public class UserDAOTest {

	@MockBean
	private SessionFactory sessionFactory;
	@MockBean
	private Session session;
	
	private UserDAO userDAO;
	
	@BeforeEach
	public void setUp() throws Exception {		
		Mockito.when(this.sessionFactory.getCurrentSession()).thenReturn(this.session);
		this.userDAO = new UserDAOImpl(this.sessionFactory);
	}
	
	@Test
	public void should_returnEmptyList_forUnmatchingUser() {
		Query query = Mockito.mock(Query.class);
		Mockito.when(this.session.getNamedQuery("findByEmail")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(new ArrayList());
		
		List list = userDAO.findByEmail("foo@bar.com");
		assertAll("Users", 
				() -> assertNotEquals(list, null),
				() -> assertEquals(list.size(), 0));
	}

}
