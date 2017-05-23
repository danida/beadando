package helper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import application.beadando3.DAO.*;
import application.beadando3.model.*;
import application.beadando3.services.implementations.FeaturesModelServiceImplementation;
import application.beadando3.services.implementations.InterfacesModelServiceImplementation;
import application.beadando3.services.implementations.LoginModelServicesImplementation;
import application.beadando3.services.implementations.RouterModelServiceImplementation;

public class DatabaseDummyData {
	
	private static List<RouterModel> routerlist = new ArrayList<>();
	private static List<InterfacesModel> interfaceslist = new ArrayList<>();
	private static List<LoginModel> userlist = new ArrayList<>();
	private static List<FeaturesModel> featureslist = new ArrayList<>();
	
	public static void init(){
	
	
	
	
	routerlist.add(new RouterModel(null, "R1", "1841", "FCZ123456", LocalDateTime.now(), "danida", "0x2142",
			"blabla.bin", "10.10.10.1", "12.2.32."));
	routerlist.add(new RouterModel(null, "R2", "1941", "FCZ123423", LocalDateTime.now(), "dnovak", "0x2102",
			"blabla.bin", "10.10.10.2", "12.2.32."));
	routerlist.add(new RouterModel(null, "R3", "2041", "FCZ133413", LocalDateTime.now(), "sanyi", "0x2142",
			"blabla.bin", "10.10.10.3", "12.2.32."));
	routerlist.add(new RouterModel(null, "R4", "2041", "FCZ124459", LocalDateTime.now(), "danida", "0x2100",
			"blabla.bin", "10.10.10.4", "12.2.32."));
	
	interfaceslist.add( new InterfacesModel(null, 1, "Giga0/1", "2312:1231:2222:BEEF:1111", "192.168.1.1"));
	interfaceslist.add( new InterfacesModel(null, 1, "Giga1/1", "2312:1231:BEEF:BEEF:1111", "192.168.2.1"));
	interfaceslist.add(new InterfacesModel(null, 1, "Giga0/0", "2312:1231:2222:0000:1111", "192.168.3.1"));
	
	userlist.add(new LoginModel(null,"admin","admin12345",1));
	userlist.add(new LoginModel(null,"danida","12345",0));
	
	featureslist.add(new FeaturesModel("ASR1001",1,1,1,1,1,1,1,1,1000));
	featureslist.add(new FeaturesModel("2021",1,1,1,1,1,0,1,1,100));
	featureslist.add(new FeaturesModel("1941",1,1,1,0,0,0,1,0,80));
	featureslist.add(new FeaturesModel("1841",1,1,1,0,0,0,1,0,40));
	featureslist.add(new FeaturesModel("871",1,1,1,0,1,0,0,0,20));
	
	RouterModelServiceImplementation rms = new RouterModelServiceImplementation();
	InterfacesModelServiceImplementation ims = new InterfacesModelServiceImplementation();
	LoginModelServicesImplementation lms = new LoginModelServicesImplementation();
	FeaturesModelServiceImplementation fms = new FeaturesModelServiceImplementation();
	
	for (RouterModel router : routerlist){
		rms.save(router);
	}
	for (InterfacesModel interfacemodel : interfaceslist){
		ims.save(interfacemodel);
	}
	for (LoginModel loginmodel : userlist){
		lms.save(loginmodel);
	}
	for (FeaturesModel feature : featureslist){
		fms.save(feature);
	}
	
	}
	
	
	
}
