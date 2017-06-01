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

import application.beadando3.DAO.FeaturesModelDAO;
import application.beadando3.DAO.PingModelDAO;
import application.beadando3.model.FeaturesModel;
import application.beadando3.model.PingModel;
import application.beadando3.services.implementations.FeaturesModelServiceImplementation;
import application.beadando3.services.implementations.PingModelServiceImplementation;

@RunWith(MockitoJUnitRunner.class)

public class TestPingModelService {

	@Mock
	private PingModelDAO dao;
	private PingModelServiceImplementation service;
	private PingModel ping1;
	private PingModel ping2;

	@Before
	public void setUp() {
		service = new PingModelServiceImplementation(dao);
		ping1 = new PingModel(1, LocalDateTime.now(), "output", "destination");
		ping2 = new PingModel(2, LocalDateTime.now(), "output2", "destination2");
		service.setDao(dao);

	}

	@Test
	public void testFindAll() {
		when(dao.findAll()).thenReturn(Arrays.asList(ping1, ping2));
		List<PingModel> pings = Arrays.asList(ping1, ping2);
		assertEquals(pings, service.getAll());
		verify(dao, times(1)).findAll();
	}

	@Test
	public void testSave() {
		service.save(ping1);
		verify(dao, times(1)).create(ping1);

	}

	@Test
	public void testUpdate() {
		when(dao.getPingModelbyId(ping1.getId())).thenReturn(Arrays.asList(ping1));
		service.update(ping1);
		verify(dao, times(1)).edit(ping1);
	}

	@Test
	public void testDelete() {
		when(dao.getPingModelbyId(1)).thenReturn(Arrays.asList(ping1));
		service.delete(ping1);
		verify(dao, times(1)).remove(ping1);
	}

	@Test
	public void testCount() {
		when(dao.count()).thenReturn("2");
		assertEquals("2", service.count());
	}

	@Test
	public void testvalidatePingModel() {
		assertEquals(service.validatePingModel(ping1),true);
	}

	@Test
	public void testcheckDuplicatesPingModel() {
		when(dao.getPingModelbyId(ping1.getId())).thenReturn(Arrays.asList(ping1));
		assertEquals(service.checkDuplicatesPingModel(ping1),true);
	}
}
