package application.beadando3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;


import application.beadando3.DAO.InterfacesModelDAO;
import application.beadando3.model.InterfacesModel;
import application.beadando3.services.implementations.InterfacesModelServiceImplementation;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)

public class TestInterfacesModelService {

	@Mock
	private InterfacesModelDAO dao;
	
	private InterfacesModelServiceImplementation service;
	
	private InterfacesModel interface1;
	private InterfacesModel interface2;
	
	@Before
	public void setUp(){
		service = new InterfacesModelServiceImplementation(dao);
		interface1 = new InterfacesModel(1, 1, "GigabitEthernet0/0", "23:32:BA:12:32:EF", "10.10.10.1");
		interface2 = new InterfacesModel(2, 1, "GigabitEthernet0/1", "32:11:EF:44:22:EE", "192.168.1.2");
	}
	
	
	@Test 
	public void testFindAll(){
		when(dao.findAll()).thenReturn(Arrays.asList(interface1,interface2));
		List<InterfacesModel> list = dao.findAll();
		verify(dao,times(1)).findAll();
		assertEquals(list,service.getAll());
		
	}
	@Test
	public void testSave(){
		service.save(interface1);
		verify(dao,times(1)).create(interface1);
	}
	@Test
	public void testUpdate(){
		when(dao.getInterfacesbyId(interface1.getId())).thenReturn(interface1);
		service.update(interface1);
		verify(dao, times(1)).edit(interface1);
		
	}
	@Test
	public void testDelete(){
		when(dao.getInterfacesbyId(interface1.getId())).thenReturn(interface1);
		service.delete(interface1);
		verify(dao, times(1)).remove(interface1);
	}
	@Test
	public void testCount(){
		when(dao.count()).thenReturn("2");
		assertEquals("2", service.count());
	}
	@Test 
	public void testGetInterfacesbyRouterId(){
		when(dao.getInterfacesByRouterId(1)).thenReturn(Arrays.asList(interface1,interface2));
		List<InterfacesModel> list = dao.getInterfacesByRouterId(1);
		assertEquals(list, service.getInterfacebyRouterModel(1));
		
	}
	@Test
	public void testvalidateInterfacesModel(){
		boolean value = service.validateInterfacesModel(interface1);
		assertEquals(true,value);
		
	}
	@Test
	public void testcheckDuplicatesInterfacesModel(){
		when(dao.getInterfacesbyId(interface1.getId())).thenReturn(interface1);
		assertEquals(service.checkDuplicatesInterfacesModel(interface1), true);
	}
	
}
