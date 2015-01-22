package mastercase.datastructures;

import mastercase.Assumptions;

public class Destination {
	
	public final int id; 
	public final String code; 
	final int flightTime; //in minutes
	final int distance;
	public final int dailyDemand; 
	
	/**
	 * @param id
	 * @param code
	 * @param flightTime
	 */
	public Destination(int id, String code, int distance, int flightTime, int dailyDemand) {
		super();
		this.id = id;
		this.code = code;
		this.flightTime = flightTime;
		this.distance = distance;
		this.dailyDemand = dailyDemand;
	}
	
	
	public int getCycleTime() {
		return 2*flightTime + 2*Assumptions.turnTime;
	}
	
	
	public int getStandardPrice() {
		return distance;
	}
	
	public int getDemandFor(int minutes) {
		return (int)(minutes*(double)dailyDemand/(24*60));
	}

}
