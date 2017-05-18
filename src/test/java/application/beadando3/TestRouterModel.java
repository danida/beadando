package application.beadando3;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import application.beadando3.DAO.RouterModelDAO;
import application.beadando3.model.RouterModel;
import application.beadando3.services.implementations.*;

@RunWith(MockitoJUnitRunner.class)

public class TestRouterModel {
	private RouterModelServiceImplementation service;
	@Mock
	private RouterModelDAO dao;
	
	private RouterModel router1 = new RouterModel(null, "R10","1841", "FCZ123456", "2017-09-12 23:12:11.0", "danida", "0x2142", "blabla.bin","10.10.10.1" ,"12.2.32.");
	private RouterModel router2 = new RouterModel(null, "R20","1941", "FCZ123453", "2017-09-12 22:11:11.0", "danida", "0x2142", "blabla.bin","10.10.10.2" ,"12.2.32.");

	
	@Before
	public void setUp(){
		service = new RouterModelServiceImplementation();
	}
	
	
	@Test
	public void testNumberbyPlatform() {
		when(dao.findAll()).thenReturn(Arrays.asList(router1,router2));
		assertEquals("1", service.getNumberbyPlatform("1841"));
	}
	
	@Test
	public void test() {
		when(dao.count()).thenReturn("2");
		assertEquals("2", service.count());
	}
}
