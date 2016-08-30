
package algorithms.search;

/**
 * State in the game
 * @author Tomer, Gilad
 *
 * @param <T>
 */
public class State<T> implements Comparable<State<T>> {
	private T value; // represent the state
	private double cost = 0;  // cost to reach this state
	private State<T> cameFrom;  // the state we came from to this state
	
	public State(T value){ // CTOR
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {  // override equals
		State<T> s = (State<T>)obj;
		return s.value.equals(this.value);
	}
	
/**
 * @return state value
 */
	public T getValue() {
		return value;
	}

	/**
	 * 
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @return state we came from
	 */
	public State<T> getCameFrom() {
		return cameFrom;
	}

	/**
	 * set the state we came from
	 * @param cameFrom {@link State}
	 */
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}

	/**
	 *  set the value
	 * @param value
	 */
	public void setValue(T value) { 
		this.value = value;
	}

	/**
	 * set the cost
	 * @param cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() { // prints the value
		return value.toString();
	}

	@Override
	public int compareTo(State<T> s) {
		return (int) (this.getCost()-s.getCost()); // return >0 if this>s //  <0 if this<s 
	}
	
	
	
	
	

}
