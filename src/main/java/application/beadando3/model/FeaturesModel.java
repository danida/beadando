package application.beadando3.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author danida
 *
 */
@javax.persistence.Entity
@Table(name = "Features")

@NamedQueries({
	@NamedQuery (name  = "FeaturesModel.getbyPlatformName", query="select f from FeaturesModel f where platform_name = :platform" ),
	@NamedQuery (name  = "FeaturesModel.findAll", query="select f from FeaturesModel f" ),
	@NamedQuery (name  ="FeaturesModel.count", query="select count(f.id) from FeaturesModel f" ),
	@NamedQuery (name  ="FeaturesModel.findPlatforms", query="select distinct(f.platform_name) from FeaturesModel f" ),
	@NamedQuery (name ="FeaturesModel.findMaxByPlatform", query = "select f.MAXI from FeaturesModel f where platform_name = :platform")

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
	
	
	/**
	 * Default constructor.
	 */
	public FeaturesModel() {
		super();
	}
	/**
	 * Non-default constructor.
	 * @param platform_name - Platform of the router
	 * @param eIGRP - EIGRP feature
	 * @param oSPF - OSPF feature
	 * @param rIP - RIP feature
	 * @param bGP - BGP feature
	 * @param nAT - NAT feature
	 * @param mPLS - MPLS feature
	 * @param qOS - QOS feature
	 * @param nETFLOW - NETFLOW feature
	 * @param mAXI - Maximum performance
	 */
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
	/**
	 * Getter of the platform.
	 * @return Returns the platform of the router
	 */
	public String getPlatform_name() {
		return platform_name;
	}
	/**
	 * Setter of the platform.
	 * @param platform_name - Platform of the router
	 */
	public void setPlatform_name(String platform_name) {
		this.platform_name = platform_name;
	}
	/**
	 * Getter of the EIGRP.
	 * @return Returns the EIGRP of the router
	 */
	public Integer getEIGRP() {
		return EIGRP;
	}
	/**
	 * Setter of the EIGRP.
	 * @param eIGRP - EIPGR of the router
	 */
	public void setEIGRP(Integer eIGRP) {
		EIGRP = eIGRP;
	}
	/**
	 * Getter of OSPF.
	 * @return Returns the OSPF of the router
	 */
	public Integer getOSPF() {
		return OSPF;
	}
	/**
	 * Setter of OSPF.
	 * @param oSPF - OSPF of the router
	 */
	public void setOSPF(Integer oSPF) {
		OSPF = oSPF;
	}
	/**
	 * Getter of RIP.
	 * @return Returns the RIP of the router
	 */
	public Integer getRIP() {
		return RIP;
	}
	/**
	 * Setter of RIP.
	 * @param rIP - OSPF of the router
	 */
	public void setRIP(Integer rIP) {
		RIP = rIP;
	}
	/**
	 * Getter of BGP.
	 * @return Returns the OSPF of the router
	 */
	public Integer getBGP() {
		return BGP;
	}
	/**
	 * Setter of OSPF.
	 * @param bGP - OSPF of the router
	 */
	public void setBGP(Integer bGP) {
		BGP = bGP;
	}
	/**
	 * Getter of NAT.
	 * @return Returns the NAT of the router
	 */
	public Integer getNAT() {
		return NAT;
	}
	/**
	 * Setter of NAT.
	 * @param nAT - NAT of the router
	 */
	public void setNAT(Integer nAT) {
		NAT = nAT;
	}
	/**
	 * Getter of MPLS.
	 * @return Returns the MPLS of the router
	 */
	public Integer getMPLS() {
		return MPLS;
	}
	/**
	 * Setter of MPLS.
	 * @param mPLS - MPLS of the router
	 */
	public void setMPLS(Integer mPLS) {
		MPLS = mPLS;
	}
	/**
	 * Getter of QOS.
	 * @return Returns the QOS of the router
	 */
	public Integer getQOS() {
		return QOS;
	}
	/**
	 * Setter of QOS.
	 * @param qOS - QOS of the router
	 */
	public void setQOS(Integer qOS) {
		QOS = qOS;
	}
	/**
	 * Getter of NETFLOW.
	 * @return Returns the NETFLOW of the router
	 */
	public Integer getNETFLOW() {
		return NETFLOW;
	}
	/**
	 * Setter of NETFLOW.
	 * @param nETFLOW - NETFLOW of the router
	 */
	public void setNETFLOW(Integer nETFLOW) {
		NETFLOW = nETFLOW;
	}
	/**
	 * Getter of maximum performance.
	 * @return Returns the OSPF of the router
	 */
	public Integer getMAXI() {
		return MAXI;
	}
	/**
	 * Setter of maximum perfomance.
	 * @param mAXI - Maximum performance of the router
	 */
	public void setMAXI(Integer mAXI) {
		MAXI = mAXI;
	}
	/*
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "FeaturesModel [platform_name=" + platform_name + ", EIGRP=" + EIGRP + ", OSPF=" + OSPF + ", RIP=" + RIP
				+ ", BGP=" + BGP + ", NAT=" + NAT + ", MPLS=" + MPLS + ", QOS=" + QOS + ", NETFLOW=" + NETFLOW
				+ ", MAXI=" + MAXI + "]";
	}
	
	
	
	
}
