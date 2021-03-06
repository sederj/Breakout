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
public class Score implements Serializable, Comparable<Score> {
	/**
	 * Serialization ID for the class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The player's score value.
	 */
	private int value;
	
	/**
	 * The player's time.
	 */
	private int time;
	
	/**
	 * The player's name.
	 */
	private String name;
	
	/**
	 * Basic constructor for a score. Takes in the
	 * score's value, the time the player finished
	 * and their name.
	 * @param val The player's score value.
	 * @param t The player's time.
	 * @param n The player's name.
	 */
	public Score(final int val, final int t, final String n) {
		this.value = val;
		this.time = t;
		this.name = n;
	}
	
	/**
	 * Returns the player's score value.
	 * @return this.value The player's score value.
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Returns the player's time.
	 * @return this.time The player's time.
	 */
	public int getTime() {
		return this.time;
	}
	
	/**
	 * Returns the player's name.
	 * @return this.name The player's name.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the player's score value to the
	 * argument passed.
	 * @param i The new score value.
	 */
	public void setValue(final int i) {
		this.value = i;
	}
	
	/**
	 * Sets the player's time to the argument
	 * passed.
	 * @param i The new time.
	 */
	public void setTime(final int i) {
		this.time = i;
	}
	
	/**
	 * Sets the player's name to the argument
	 * passed.
	 * @param s The new name.
	 */
	public void setName(final String s) {
		this.name = s;
	}
	
	/**
	 * Compares two scores. If one score's score 
	 * value is higher than the other, that is
	 * the higher score. However, if the two
	 * scores' score values are the same,
	 * whichever has the lowest time is the
	 * higher score.
	 * 
	 * @param o The object being compared to.
	 */
	@Override
	public int compareTo(final Score o) {  
		    if (this.value - o.getValue() == 0) {
		    	return o.getTime() - this.time;
		    }
		    return this.value - o.getValue();
	}
	
	/**
	 * Generates hashCode.
	 * @return result The hashCode.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		int var = 0;
		if (name == null) {
			var = 0;
		} else {
			var = name.hashCode();
		}
		result = prime * result + var;
		result = prime * result + time;
		result = prime * result + value;
		return result;
	}
	
	/**
	 * Checks equality.
	 * @param obj Object being compared to.
	 * @return boolean Based on result.
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Score other = (Score) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (time != other.time) {
			return false;
		}
		if (value != other.value) {
			return false;
		}
		return true;
	}
}
