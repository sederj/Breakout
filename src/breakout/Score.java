package breakout;

import java.io.Serializable;

public class Score implements Serializable{
	private int value;
	private String name;
	
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
}
