package application.beadando3;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import application.beadando3.DAO.RouterModelDAO;
import application.beadando3.model.RouterModel;
import application.beadando3.services.implementations.RouterModelServiceImplementation;

@RunWith(MockitoJUnitRunner.class)

public class TestRouterModel {

	@Mock
	private RouterModelDAO dao;

	private RouterModelServiceImplementation service;
	 

	private RouterModel router1;
	private RouterModel router2;

	@Before
	public void setUp() {
		service= new RouterModelServiceImplementation(dao);
		router1 = new RouterModel(1, "R10", "1841", "FCZ123456", LocalDateTime.now(), "danida", "0x2142",
				"blabla.bin", "10.10.10.1", "12.2.32.");
		router2 = new RouterModel(null, "R20", "1941", "FCZ123453", LocalDateTime.now(), "danida", "0x2142",
				"blabla.bin", "10.10.10.2", "12.2.32.");
		service.setDao(dao);

	}

	@Test
	public void testNumberbyPlatform() {
		when(dao.getnumberByPlatform("1841")).thenReturn("1");

		assertEquals("1", service.getNumberbyPlatform("1841"));
	}

	@Test
	public void testCount() {
		when(dao.count()).thenReturn("2");
		assertEquals("2", service.count());
	}

	@Test
	public void testFindAll() {
		when(dao.findAll()).thenReturn(Arrays.asList(router1, router2));
		List<RouterModel> routers = dao.findAll();
		verify(dao, times(1)).findAll();
		assertEquals(routers, service.getAll());

	}

	@Test
	public void testSave() {
		service.save(router1);
		verify(dao, times(1)).create(router1);

	}

	@Test
	public void testUpdate() {
		when(dao.getRouterModelbyId(router1.getId())).thenReturn(Arrays.asList(router1));
		service.update(router1);
		verify(dao, times(1)).edit(router1);
	}

	@Test
	public void testDelete() {
		when(dao.getRouterModelbyId(router1.getId())).thenReturn(Arrays.asList(router1));
		service.delete(router1);
		verify(dao, times(1)).remove(router1);
	}

	@Test
	public void testGetPlatforms() {
		when(dao.getPlatforms()).thenReturn(Arrays.asList("1841","1941"));
		List<String> list= service.getPlatforms();
		assertEquals(list,Arrays.asList("1841","1941"));
	}

}
