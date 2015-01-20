package mastercase.datastructures;

import java.util.concurrent.atomic.AtomicLong;

import mastercase.AirUtil;

public class AirPlane implements Comparable<AirPlane>{

	final static AtomicLong NEXT_ID = new AtomicLong(1);
	public final int id = (int) NEXT_ID.getAndIncrement();
	final public int capacity; 
	private boolean inAir = false;
	int inAirUntil =0;

	 int cumAirTime  =0;
	private int time = 0; 
	/**
	 * @param capacity
	 */
	public AirPlane(int capacity) {
		super();
		this.capacity = capacity;
		
		
	}
	
	public int getCumAirTime() {
		return cumAirTime;
	}
	
	public void setInAir(int until) {
		inAir = true;
		inAirUntil = until;
		cumAirTime += (until - time);
	}
	
	public boolean isInAir() {
		return inAir;
	}
	
	public void updateTime(int time) {
		this.time = time;
		if(inAir) {
			if(inAirUntil < time) {
				inAir = false;
			}
		}
	}
	
	public int compareTo(AirPlane other) {
		return this.capacity - other.capacity;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof AirPlane) {
			return equals((AirPlane)o);
		}else {
			return false;
		}
	}
	public boolean equals(AirPlane other) {
		return this.id == other.id;
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}

	public String toString() {
		return id+" "+(inAir? AirUtil.minutesToHHMM(inAirUntil): "Grnd");
	}


}
