package application.beadando3;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import javafx.scene.control.CheckBox;

import application.beadando3.DAO.FeaturesModelDAO;
import application.beadando3.model.FeaturesModel;
import application.beadando3.services.implementations.FeaturesModelServiceImplementation;


@RunWith(MockitoJUnitRunner.class)


public class TestFeaturesModelService {
	@Mock
	private FeaturesModelDAO dao;
	private FeaturesModelServiceImplementation service;
	private FeaturesModel feature1;
	private FeaturesModel feature2;
	private List<CheckBox> list = new ArrayList<CheckBox>();

	
	@Before
	public void setUp() {
		service = new FeaturesModelServiceImplementation(dao);
		feature1 = new FeaturesModel("7200",1,1,1,1,1,0,1,1,215);
		feature2 = new FeaturesModel("1941",1,1,1,0,0,0,1,0,153);
		service.setDao(dao);

	}
	@Test
	public void testSave() {
		service.save(feature1);
		verify(dao, times(1)).create(feature1);
	}
	@Test
	public void testCount() {
		when(dao.count()).thenReturn("2");
		assertEquals("2", service.count());
	}
	@Test
	public void testFindAll() {
		when(dao.findAll()).thenReturn(Arrays.asList(feature1, feature2));
		List<FeaturesModel> features= Arrays.asList(feature1,feature2);
		assertEquals(features, service.getAll());
		verify(dao, times(1)).findAll();
	}
	@Test
	public void testDelete(){
		when(dao.getFeaturesModelByFeatures_name("7200")).thenReturn(Arrays.asList(feature1));
		service.delete(feature1);
		verify(dao, times(1)).remove(feature1);
	}
	@Test
	public void testUpdate() {
		when(dao.getFeaturesModelByFeatures_name(feature1.getPlatform_name())).thenReturn(Arrays.asList(feature1));
		service.update(feature1);
		verify(dao, times(1)).edit(feature1);
	}
	@Test
	public void testlistAllPlatforms(){
		when(dao.findPlatforms()).thenReturn(Arrays.asList("7200","1941"));
		List<String> features= service.listAllPlatforms();
		assertEquals(Arrays.asList("7200","1941"), features);
	}
	@Test
	public void testgetFeatureModelList(){
		
		when(dao.getFeaturesModelByFeatures_name("1941")).thenReturn(Arrays.asList(feature2));
		FeaturesModel expected = service.getFeatureModelList("1941");
		assertEquals(expected, feature2);
		
	}
	@Test
	public void testValidateFeaturesModel1(){
		boolean value = service.validateFeaturesModel(feature1);
		assertEquals(true,value);
		
		
	}
	@Test
	public void testValidateFeaturesModel2(){
		boolean value = service.validateFeaturesModel(new FeaturesModel(null,1,1,1,1,1,0,1,1,215));
		assertEquals(false,value);

	}
	
	@Test
	public void testCalculatePerformance(){
		when(dao.getMaxibyPlatform("1841")).thenReturn(38); 
		double check = service.calculatePerforfmance(list, "1000", "1841");
		assertEquals(26.31, check,0.1);
	}
	@Test
	public void getFeatureModelList(){
		when(dao.getFeaturesModelByFeatures_name("1841")).thenReturn(Arrays.asList(feature1)); 
		FeaturesModel check = service.getFeatureModelList("1841");
		assertEquals(feature1,check);
	}
	
	
	
}
