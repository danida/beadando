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
	public void testlistAdmins(){
		when(dao.getAllAdmins()).thenReturn(Arrays.asList(user1));
		List<LoginModel> admins = service.listAdmins();
		assertEquals(admins,Arrays.asList(user1));
	}
	@Test
	public void testlistUsers(){
		when(dao.getAllUsers()).thenReturn(Arrays.asList(user2));
		List<LoginModel> admins = service.listUsers();
		assertEquals(admins,Arrays.asList(user2));
	}
	@Test
	public void testtryToAuthenticate(){
		when(dao.getUserByUsername(user1.getUser())).thenReturn(Arrays.asList(user1));
		when(dao.getPasswordForUser(user1.getUser())).thenReturn("YWRtaW4xMjM0NQ==");
		assertEquals(service.tryToAuthenticate(user1),user1);

		
	}
	@Test
	public void testvalidateLoginModel(){
		
		assertEquals(true,service.validateLoginModel(user1));
		
	}
	@Test
	public void testcheckDuplicatesLoginModel(){
		when(dao.getUserById(user1.getId())).thenReturn(Arrays.asList(user1));
		assertEquals(service.checkDuplicatesLoginModel(user1),true);
		
	}
	@Test
	public void testencryptPassword(){
		assertEquals("YWRtaW4xMjM0NQ==",service.encryptPassword("admin12345"));
		
	}
	
}
