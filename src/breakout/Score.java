package breakout;

import java.io.Serializable;

public class Score implements Serializable, Comparable{
	private int value;
	private String name;
	
	public Score(int val, String n) {
		this.value = val;
		this.name = n;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setValue(int i) {
		this.value = i;
	}
	
	public void setName(String s) {
		this.name = s;
	}

	@Override
	public int compareTo(Object o) {
		if (!(o instanceof Score))
		      throw new ClassCastException();
		    int anotherValue = ((Score) o).getValue();  
		    return this.value - anotherValue;
	}
}
