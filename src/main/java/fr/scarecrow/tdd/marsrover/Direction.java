package fr.scarecrow.tdd.marsrover;

enum Direction {

	NORTH("N", "W", "E"), SOUTH("S", "E", "W"), EAST("E", "N", "S"), WEST("W", "S", "N");

	private final String value;
	private final String left;
	private final String right;

	Direction(String value, String left, String right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	Direction right() {
		return directionMatching(right);
	}

	Direction left() {
		return directionMatching(left);
	}

	public String value() {
		return value;
	}

	Direction directionMatching(String value) {
		for (Direction direction : values()) {
			if (direction.value == value) {
				return direction;
			}
		}
		return null;
	}
}