package fr.scarecrow.tdd.marsrover;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class RoverShould {

	private Rover rover;

	@Before
	public void initialise() {
		Grid grid = new Grid();
		rover = new Rover(grid);
	}

	@Test
	@Parameters({ "R, 0:0:E", "RR, 0:0:S", "RRR, 0:0:W", "RRRR, 0:0:N" })
	public void rotate_right(String commands, String position) {
		assertThat(rover.execute(commands), is(position));
	}

	@Test
	@Parameters({ "L, 0:0:W", "LL, 0:0:S", "LLL, 0:0:E", "LLLL, 0:0:N" })
	public void rotate_left(String commands, String position) {
		assertThat(rover.execute(commands), is(position));
	}

	@Test
	@Parameters({ "M, 0:1:N", "MMM, 0:3:N" })
	public void move_up(String commands, String position) {
		assertThat(rover.execute(commands), is(position));
	}

	@Test
	@Parameters({ "MMMMMMMMMM, 0:0:N", "MMMMMMMMMMMMM, 0:3:N" })
	public void wrap_from_top_to_bottom_when_moving_north(String commands, String position) {
		assertThat(rover.execute(commands), is(position));
	}

	@Test
	@Parameters({ "RM, 1:0:E", "RMMMMM, 5:0:E" })
	public void move_right(String commands, String position) {
		assertThat(rover.execute(commands), is(position));
	}

	@Test
	@Parameters({ "RMMMMMMMMMM, 0:0:E" })
	public void wrap_from_right_to_left_when_moving_east(String commands, String position) {
		assertThat(rover.execute(commands), is(position));
	}

	@Test
	@Parameters({ "LM, 9:0:W", "LMM, 8:0:W" })
	public void move_left(String commands, String position) {
		assertThat(rover.execute(commands), is(position));
	}

	@Test
	@Parameters({ "LLM, 0:9:S", "LLMM, 0:8:S" })
	public void move_south(String commands, String position) {
		assertThat(rover.execute(commands), is(position));
	}
	
	@Test
	@Parameters({ "MMMM, O:0:3:N", "RMMMMMM, O:1:0:E" })
	public void stop_at_obstacle(String commands, String position) {
		Coordinate obstacle_0x4 = new Coordinate(0,4);
		Coordinate obstacle_2x0 = new Coordinate(2,0);
		Grid grid = new Grid(Arrays.asList(obstacle_0x4, obstacle_2x0));
		Rover rover = new Rover(grid);
		assertThat(rover.execute(commands), is(position));
	}

}
