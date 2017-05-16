package application.beadando3.model;

import java.util.List;


public class DashboardModel {
	
	
	private int numberOfRouters;
	private List<String> reachableRouter;
	private String  searchRouter;
	
	public int getNumberOfRouters() {
		return numberOfRouters;
	}
	public void setNumberOfRouters(int numberOfRouters) {
		this.numberOfRouters = numberOfRouters;
	}
	public List<String> getReachableRouter() {
		return reachableRouter;
	}
	public void setReachableRouter(List<String> reachableRouter) {
		this.reachableRouter = reachableRouter;
	}
	public String getSearchRouter() {
		return searchRouter;
	}
	public void setSearchRouter(String searchRouter) {
		this.searchRouter = searchRouter;
	}
	
	
}
