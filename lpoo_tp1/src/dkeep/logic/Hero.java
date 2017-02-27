package dkeep.logic;

public class Hero {
	protected Coords coords;
	protected char c;
	protected boolean hasKey;

	public boolean hasKey() {
		return hasKey;
	}

	public void aquiresKey() {
		this.hasKey = true;
		c = 'K';
	}
	
	public void dropsKey() {
		this.hasKey = false;
		c = 'H';
	}

	public Hero(int x, int y) {
		coords = new Coords (x, y);
		c = 'H';
		hasKey = false;
	}

	public Hero() {
		coords = new Coords();
		c = 'H';
		hasKey = false;
	}

	public void newPos(Coords newPos) {
		coords = newPos;
	}

	public Coords getCoords() {
		return coords;
	}

	public int getX() {
		return coords.getX();
	}

	public int getY() {
		return coords.getY();
	}

	public char getC() {
		return c;
	}

}
