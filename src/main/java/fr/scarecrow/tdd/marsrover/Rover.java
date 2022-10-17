package fr.scarecrow.tdd.marsrover;

import java.util.Optional;

class Rover {

	private Grid grid;

	private Direction direction = Direction.NORTH;
	private Coordinate coordinate = new Coordinate(0, 0);

	public Rover(Grid grid) {
		this.grid = grid;
	}

	String execute(String commands) {
		String obstacleString = "";
		for (char command : commands.toCharArray()) {
			if (command == 'R') {
				direction = direction.right();
			}
			if (command == 'L') {
				direction = direction.left();
			}
			if (command == 'M') {
				Optional<Coordinate> nextCoordinate = grid.nextCoordinateFor(coordinate, direction);
				nextCoordinate.ifPresent(nc -> this.coordinate = nc);
				obstacleString = nextCoordinate.isPresent() ? "": "O:";
			}
		}
		return obstacleString + coordinate.x() + ":" + coordinate.y() + ":" + direction.value();
	}
}
