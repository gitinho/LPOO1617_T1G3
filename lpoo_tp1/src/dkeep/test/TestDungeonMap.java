package dkeep.test;

import dkeep.logic.Coords;
import dkeep.logic.GameMap;

public class TestDungeonMap extends GameMap {
	public TestDungeonMap(char[][] map) {
		name = "test_dungeon";
		this.map = map;
		hasLevers = true;
		doorsAreOpen = false;
		doorsAmmount = 2;
		doorsPos = new Coords[doorsAmmount];
		doorsPos[0] = new Coords(0,2);
		doorsPos[1] = new Coords(0,3);
		heroStartingPos = new Coords(1,1);
		heroHasClub = false;
		guardMoves = false;
		guardAmmount = 1;
		guardStartingPos = new Coords[guardAmmount];
		guardStartingPos[0] = new Coords(3,1);
		guardTypes = new String[guardAmmount];
		guardTypes[0] = "rookie";
		guardMovements = new String[guardAmmount];
		guardMovements[0] = "";
		ogreAmmount = 0;
	}
}