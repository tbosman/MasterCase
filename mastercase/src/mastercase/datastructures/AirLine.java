package mastercase.datastructures;

import java.util.*;


public class AirLine {

	List<AirPlane> airPlanes = new ArrayList<AirPlane>();
	List<Destination> destinations = new ArrayList<Destination>();
	
	
	public AirLine() {
		// TODO Auto-generated constructor stub
		init();
	}

	public void readDestinations() {
		destinations.add(new Destination(1, "LHR", 229, 57, 2000));
		destinations.add(new Destination(2, "HAM", 235, 58, 600));
		destinations.add(new Destination(3, "CDG", 247, 59, 1500));
		destinations.add(new Destination(4, "BHX", 274, 62, 300));
		
		destinations.add(new Destination(5, "MUC", 412, 79, 800));
		destinations.add(new Destination(6, "NCE", 607, 102, 500));
		destinations.add(new Destination(7, "BUD", 725, 117, 400));
		destinations.add(new Destination(8, "LIS", 1146, 167, 300));
		
	}
	
	public void addAirPlanes(int number, int capacity) {
		for(int i=0; i<number;i++) {
			airPlanes.add(new AirPlane(capacity));
		}
	}
	
	public void init() {
		readDestinations();
		addAirPlanes(4, 108);
		addAirPlanes(4, 128);
		addAirPlanes(4, 148);
	}
	
	
	public List<AirPlane> getAirplanes(){
		return airPlanes;			
	}
	
	public List<Destination> getDestinations(){
		return destinations;
	}
}
