package mastercase.datastructures;

import java.util.concurrent.atomic.AtomicLong;

import mastercase.AirUtil;

public class ScheduledCycle {

	static final AtomicLong NEXT_ID = new AtomicLong(1); 
	
	final long id = NEXT_ID.getAndIncrement();
	Destination destination; 
	int departureTime; 
	int ticketPrice; 
	int demand;
	/**
	 * @param destination
	 * @param departureTime
	 * @param ticketPrice
	 * @param demand
	 */
	public ScheduledCycle(Destination destination, int departureTime,
			int ticketPrice, int demand) {
		super();
		this.destination = destination;
		this.departureTime = departureTime;
		this.ticketPrice = ticketPrice;
		this.demand = demand;
	} 
	
	
	public int getArrivalTime() {
		return departureTime + destination.getCycleTime();
	}
	
	
	public String toString() {
		StringBuilder out = new StringBuilder(""+id);
		out.append(" Dest: ");
		out.append(destination.code+" - Departure: ");
		out.append(AirUtil.minutesToHHMM(departureTime) + " - Arrival: ");
		out.append(AirUtil.minutesToHHMM(getArrivalTime()));
		out.append("- price "+ticketPrice+" demand "+ demand);
		return out.toString();
	}
	
	
	
	
}
