package application.beadando3;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import application.beadando3.DAO.LoginModelDAO;
import application.beadando3.model.LoginModel;
import application.beadando3.services.implementations.LoginModelServicesImplementation;

@RunWith(MockitoJUnitRunner.class)

public class TestLoginModelServices {
	@Mock
	private LoginModelDAO dao;
	private LoginModelServicesImplementation service;
	private LoginModel user1;
	private LoginModel user2;
	
	@Before
	public void setUp() {
		service = new LoginModelServicesImplementation(dao);
		user1 = new LoginModel(1,"admin","admin12345",1);
		user2 = new LoginModel(2,"notadmin","12345",0);
		service.setDao(dao);

	}
	@Test
	public void testSave() {
		service.save(user1);
		verify(dao, times(1)).create(user1);
	}
	@Test
	public void testCount() {
		when(dao.count()).thenReturn("2");
		assertEquals("2", service.count());
	}
	@Test
	public void testFindAll() {
		when(dao.findAll()).thenReturn(Arrays.asList(user1, user2));
		List<LoginModel> features= Arrays.asList(user1,user2);
		assertEquals(features, service.getAll());
		verify(dao, times(1)).findAll();
	}
	@Test
	public void testDelete(){
		when(dao.getUserById(1)).thenReturn(Arrays.asList(user1));
		service.delete(user1);
		verify(dao, times(1)).remove(user1);
	}
	@Test
	public void testUpdate() {
		when(dao.getUserById(user1.getId())).thenReturn(Arrays.asList(user1));
		service.update(user1);
		verify(dao, times(1)).edit(user1);
	}
	@Test
	public void testlistAdmins(){}
	@Test
	public void testlistUsers(){}
	@Test
	public void testtryToAuthenticate(){}
	@Test
	public void testvalidateLoginModel(){}
	@Test
	public void testcheckDuplicatesLoginModel(){}
	@Test
	public void testencryptPassword(){}
	
}
