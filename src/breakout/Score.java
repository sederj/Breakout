package breakout;

import java.io.Serializable;

/**
 * This class represents score data to be stored
 * in the high score system. Tracks what the player's
 * final score and time were, along with a name.
 *
 * @author Joey Seder, Jacob McCloughan, Jonah Bukowsky
 * @version 4/19/17
 */
public class Score implements Serializable, Comparable {
	/**
	 * The value of the score.
	 */
	private int value;
	private int time;
	private String name;
	
	public Score(int val, int t, final String n) {
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
		if (!(o instanceof Score)) {
		      throw new ClassCastException();
		}
		    int anotherValue = ((Score) o).getValue();
		    int anotherTime = ((Score) o).getTime();  
		    if (this.value - anotherValue == 0) {
		    	return anotherTime - this.time;
		    }
		    return this.value - anotherValue;
	}
}
