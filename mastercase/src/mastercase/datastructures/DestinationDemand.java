package mastercase.datastructures;

public class DestinationDemand implements Comparable<DestinationDemand>{

	public final Destination destination; 
	private int demand = 0; 
	
	public int getDemand() {
		return demand;
	}

	public void setDemand(int demand) {
		this.demand = demand;
	}
	
	public int add(int add) {
		return demand= demand + add;
	}
	
	public int subtract(int sub) {
		demand = demand - sub; 
		if(demand < 0) {
			throw new Error("Demand < 0");
		}
		return demand;
	}

	public DestinationDemand(Destination destination) {
		this.destination = destination;
	}

	@Override
	public int compareTo(DestinationDemand o) {
		return this.demand - o.demand;
	}
	
	
	public String toString() {
		return destination.code+" d:"+demand;
	}

}
