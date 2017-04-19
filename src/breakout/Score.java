package breakout;

import java.io.Serializable;

public class Score implements Serializable, Comparable{
	private int value;
	private int time;
	private String name;
	
	public Score(int val, int t, String n) {
		this.value = val;
		this.time = t;
		this.name = n;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setValue(int i) {
		this.value = i;
	}
	
	public void setTime(int i) {
		this.time = i;
	}
	
	public void setName(String s) {
		this.name = s;
	}

	@Override
	public int compareTo(Object o) {
		if (!(o instanceof Score))
		      throw new ClassCastException();
		    int anotherValue = ((Score) o).getValue();
		    int anotherTime = ((Score) o).getTime();  
		    if (this.value - anotherValue == 0) {
		    	return anotherTime - this.time;
		    }
		    return this.value - anotherValue;
	}
}
