import java.util.Objects;
import java.util.Random;

import processing.core.PApplet;

public interface LoX<T> {

	public PApplet draws(PApplet c);

	public T getFirst();

	public LoX<T> getRest();

	public boolean isEmpty();

}

class MT<T> implements LoX<T> {
	MT() {}

	public PApplet draws(PApplet c) {
		return c;
	}

	public boolean isEmpty() {
		return true;
	}

	public T getFirst() {
		return null;
	}

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

class Cons<T> implements LoX<T> {
	T first;
	LoX<T> rest;

	Cons(T first, LoX<T> rest) {
		super();
		this.first = first;
		this.rest = rest;
	}


	public PApplet draws(PApplet c) {
		if (first.getClass() == Platform.class) {
			((Platform) first).draw(c);
		} else if (first.getClass() == Obstacle.class) {
			((Obstacle) first).draw(c);
		} 
		rest.draws(c);
		return c;
	} 

	public boolean isEmpty() {
		return false;
	}

	public T getFirst() {
		return first;
	}

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

