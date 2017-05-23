package application.beadando3.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "Features")

@NamedQueries({
	@NamedQuery (name  = "FeaturesModel.getbyPlatformName", query="select t from FeaturesModel t where platform_name = :platform" ),
	@NamedQuery (name  = "FeaturesModel.findAll", query="select t from FeaturesModel t" ),
	@NamedQuery (name  ="FeaturesModel.count", query="select count(t.id) from FeaturesModel t" ),
	@NamedQuery (name  ="FeaturesModel.findPlatforms", query="select distinct(f.platform_name) from FeaturesModel f" ),


})
public class FeaturesModel {
	@Id
	String platform_name;
	@Column
	@Basic
	Integer EIGRP;
	@Column
	@Basic
	Integer OSPF;
	@Column
	@Basic
	Integer RIP;
	@Column
	@Basic
	Integer BGP;
	@Column
	@Basic
	Integer NAT;
	@Column
	@Basic
	Integer MPLS;
	@Column
	@Basic
	Integer QOS;
	@Column
	@Basic
	Integer NETFLOW;
	@Column
	@Basic
	Integer MAXI;
	
	
	public FeaturesModel(String platform_name, Integer eIGRP, Integer oSPF, Integer rIP, Integer bGP, Integer nAT,
			Integer mPLS, Integer qOS, Integer nETFLOW, Integer mAXI) {
		super();
		this.platform_name = platform_name;
		EIGRP = eIGRP;
		OSPF = oSPF;
		RIP = rIP;
		BGP = bGP;
		NAT = nAT;
		MPLS = mPLS;
		QOS = qOS;
		NETFLOW = nETFLOW;
		MAXI = mAXI;
	}
	public String getPlatform_name() {
		return platform_name;
	}
	public void setPlatform_name(String platform_name) {
		this.platform_name = platform_name;
	}
	public Integer getEIGRP() {
		return EIGRP;
	}
	public void setEIGRP(Integer eIGRP) {
		EIGRP = eIGRP;
	}
	public Integer getOSPF() {
		return OSPF;
	}
	public void setOSPF(Integer oSPF) {
		OSPF = oSPF;
	}
	public Integer getRIP() {
		return RIP;
	}
	public void setRIP(Integer rIP) {
		RIP = rIP;
	}
	public Integer getBGP() {
		return BGP;
	}
	public void setBGP(Integer bGP) {
		BGP = bGP;
	}
	public Integer getNAT() {
		return NAT;
	}
	public void setNAT(Integer nAT) {
		NAT = nAT;
	}
	public Integer getMPLS() {
		return MPLS;
	}
	public void setMPLS(Integer mPLS) {
		MPLS = mPLS;
	}
	public Integer getQOS() {
		return QOS;
	}
	public void setQOS(Integer qOS) {
		QOS = qOS;
	}
	public Integer getNETFLOW() {
		return NETFLOW;
	}
	public void setNETFLOW(Integer nETFLOW) {
		NETFLOW = nETFLOW;
	}
	public Integer getMAXI() {
		return MAXI;
	}
	public void setMAXI(Integer mAXI) {
		MAXI = mAXI;
	}
	
	
	
	
}
