import java.util.Objects;
import java.util.Random;

import processing.core.PApplet;

// represents a generic list-like structure with basic operations
public interface LoX<T> {

	// draws all elements of the list
	public PApplet draws(PApplet c);

	// retrieves the first element in the list
	public T getFirst();

	// retrieves the rest of the list
	public LoX<T> getRest();

	// checks if the list is empty
	public boolean isEmpty();

}

// represents an empty list in the LoX structure
class MT<T> implements LoX<T> {
	MT() {}

	// does nothing and returns the given canvas
	public PApplet draws(PApplet c) {
		return c;
	}

	// returns true, indicating that this is an empty list
	public boolean isEmpty() {
		return true;
	}

	// returns null, as there is no first element in an empty list
	public T getFirst() {
		return null;
	}

	// returns the empty list itself, as there is no rest in an empty list
	public LoX<T> getRest() {
		return this;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof MT;
	}

	@Override
	public int hashCode() {
		return MT.class.hashCode();
	}

	@Override
	public String toString() {
		return "MT<T> []";
	}
}

// represents a non-empty list in the LoX structure, holding an element and the rest of the list
class Cons<T> implements LoX<T> {
	T first;
	LoX<T> rest;

	Cons(T first, LoX<T> rest) {
		super();
		this.first = first;
		this.rest = rest;
	}

	// draws all elements of the list
	public PApplet draws(PApplet c) {
		if (first.getClass() == Platform.class) {
			((Platform) first).draw(c);
		} else if (first.getClass() == Obstacle.class) {
			((Obstacle) first).draw(c);
		} 
		rest.draws(c);
		return c;
	} 

	// returns false, indicating that this is not an empty list
	public boolean isEmpty() {
		return false;
	}

	// returns the first element of the list
	public T getFirst() {
		return first;
	}

	// return the rest of the list
	public LoX<T> getRest() {
		return rest;
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, rest);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cons other = (Cons) obj;
		return Objects.equals(first, other.first) && Objects.equals(rest, other.rest);
	}

}

