package mastercase;

import java.util.*;

import mastercase.datastructures.*;

public class Scheduler {

	AirLine airLine; 
	
	List<AirPlane> allAirplanes; 
	List<AirPlane> availableAirPlanes;
	
	int currentTime; 
	
	Map<Destination, DestinationDemand> destinations;
	List<DestinationDemand> demands;
	
	List<ScheduledCycle> schedule; 
	
	public Scheduler() {
		airLine = new AirLine(); 
		allAirplanes = airLine.getAirplanes();
		availableAirPlanes = new ArrayList<AirPlane>(airLine.getAirplanes());
		
		destinations = new HashMap<Destination, DestinationDemand>();
		demands = new ArrayList<DestinationDemand>();
		schedule = new ArrayList<ScheduledCycle>();
		
		for(Destination destination : airLine.getDestinations()) {
			destinations.put(destination, new DestinationDemand(destination));
			demands.add(destinations.get(destination));
		}
		
		
		
		
	}
	
	public void initObjects() {
		for(DestinationDemand dd: demands) {
			dd.setDemand(dd.destination.getDemandFor(Assumptions.scheduleStart));
		}
		for(AirPlane plane : allAirplanes) {
			plane.updateTime(Assumptions.scheduleStart);
		}
	}
	
	public void updateAvailableAirplanes() {
		availableAirPlanes.clear(); 
		for(AirPlane plane : allAirplanes) {
			plane.updateTime(currentTime);
			if(!plane.isInAir()) {
				availableAirPlanes.add(plane);
			}
		}
	}
	
	public void updateDemands(int timeElapsed) {
		for(DestinationDemand dd: demands) {
			dd.add(dd.destination.getDemandFor(timeElapsed));
		}
	}
	
	
	private void schedule(DestinationDemand dd, AirPlane nextPlane) {
		int load = (int)(Assumptions.minLoad*nextPlane.capacity); 
		double markup = 1 - (load/dd.getDemand());
		int ticketPrice = (int) (dd.destination.getStandardPrice()*(1+markup));
		
		ScheduledCycle cycle = new ScheduledCycle(dd.destination, currentTime, ticketPrice, load);
		schedule.add(cycle);
		
		
//		System.out.println(cycle);//DBG
		
		//adjust objects 
		nextPlane.setInAir(currentTime+dd.destination.getCycleTime());
		dd.subtract(load);
		
		
	}
	
	public void startScheduler() {
		currentTime = Assumptions.scheduleStart;
		
		while(currentTime < Assumptions.scheduleEnd) {
			
			Collections.sort(availableAirPlanes, Collections.reverseOrder());
			Collections.sort(demands, Collections.reverseOrder());
//			System.out.println(demands); 
//			System.out.println(allAirplanes);
			Iterator<AirPlane> airplaneIt = availableAirPlanes.iterator(); 
			for(DestinationDemand demand : demands) { 
				if(demand.destination.getCycleTime() > (Assumptions.scheduleEnd - currentTime)) {
					//skip
					continue;
				}
				while(airplaneIt.hasNext()) {
					AirPlane nextPlane = airplaneIt.next();
					if(demand.getDemand() >= nextPlane.capacity*Assumptions.minLoad) {
						schedule(demand, nextPlane);
						break;						
					}
				}
			}
			
			currentTime += Assumptions.interval;
			updateAvailableAirplanes(); 
			updateDemands(Assumptions.interval);
			
		}
	}
	

	public void start() {
		initObjects();
		startScheduler();
		
		System.out.println("Destinations: ");
		for(Destination d: destinations.keySet()) {
			System.out.println(d.code+ " daily demand: "+d.dailyDemand);
		}
		
		System.out.println("##Residual Demand:");
		System.out.println(demands);
		System.out.println("##Scheduled Flights:");
		for(ScheduledCycle cycle : schedule) {
			System.out.println(cycle);
		}
		
		System.out.println("##Air plane percentage air time");
		for(AirPlane plane : allAirplanes) {
			System.out.println("Plane "+plane.id+" | cap:"+plane.capacity+ " | % time operation:"+(plane.getCumAirTime()/(18.0*60)));
		}
	}
	
	public static void main(String... args) {
		new Scheduler().start();
	}
	

}
