import java.util.Objects;

/** represent the state of our doodle jump animation */
public class DoodleWorld implements IWorld {
	
		int x;
		int y;
		
		public DoodleWorld(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			DoodleWorld other = (DoodleWorld) obj;
			return x == other.x && y == other.y;
		}

}

