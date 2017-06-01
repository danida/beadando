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

import application.beadando3.DAO.TracerouteModelDAO;
import application.beadando3.model.TracerouteModel;
import application.beadando3.services.implementations.TracerouteModelServiceImplementation;


@RunWith(MockitoJUnitRunner.class)
public class TestTracerouteModelService {
	@Mock
	private TracerouteModelDAO dao;
	private TracerouteModelServiceImplementation service;
	private TracerouteModel traceroute1;
	private TracerouteModel traceroute2;
	
	@Before
	public void setUp() {
		service = new TracerouteModelServiceImplementation(dao);
		traceroute1 = new TracerouteModel(1,LocalDateTime.now(),"output","destination");
		traceroute2 = new TracerouteModel(2,LocalDateTime.now(),"output2","destination2");

	}
	@Test 
	public void testFindAll(){
		when(dao.findAll()).thenReturn(Arrays.asList(traceroute1, traceroute2));
		List<TracerouteModel> pings= Arrays.asList(traceroute1,traceroute2);
		assertEquals(pings, service.getAll());
		verify(dao, times(1)).findAll();
	}
	@Test
	public void testSave(){
		service.save(traceroute1);
		verify(dao, times(1)).create(traceroute1);
	
	}
	@Test
	public void testUpdate(){
		when(dao.getTracerouteModelbyId(traceroute1.getId())).thenReturn(Arrays.asList(traceroute1));
		service.update(traceroute1);
		verify(dao, times(1)).edit(traceroute1);
	}
	@Test
	public void testDelete(){
		when(dao.getTracerouteModelbyId(1)).thenReturn(Arrays.asList(traceroute1));
		service.delete(traceroute1);
		verify(dao, times(1)).remove(traceroute1);
	}
	@Test
	public void testCount(){
		when(dao.count()).thenReturn("2");
		assertEquals("2", service.count());
	}
	@Test
	public void testvalidateTracerouteModel() {
		assertEquals(service.validateTracerouteModel(traceroute1),true);
	}

	@Test
	public void testcheckDuplicatesTracerouteModel() {
		when(dao.getTracerouteModelbyId(traceroute1.getId())).thenReturn(Arrays.asList(traceroute1));
		assertEquals(service.checkDuplicatesTracerouteModel(traceroute1),true);
	}
}
