package sst;

public class Interval {
	private long start;
	private long end;	 
	
	public Interval() { 
		start = System.currentTimeMillis(); 
	}

	public void stop() { 
		end = System.currentTimeMillis(); 
	}
	
	public long current() { return end - start; }
}