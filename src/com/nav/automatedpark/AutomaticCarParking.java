package com.nav.automatedpark;

/**
 * The purpose of this exercise is to demonstrate TDD and come up with a
 * solution to the problem given below. Working unit tests will be sufficient as
 * a solution, but you may provide a simple user interface to demonstrate your
 * codes capability if you desire. Cars are placed on a 15 by 15 grid at
 * particular co-ordinates heading north, and the simple commands Left, right
 * and forward are transmitted to them. The commands must be executed and the
 * final position calculated. The following examples should be used to
 * demonstrate your code: For the input "5,5:RFLFRFLF" We should get the
 * position "7,7" For the input "6,6:FFLFFLFFLFF" We should get the position
 * "6,6" For the input "5,5:FLFLFFRFFF" We should get the position "4,1"
 */
public class AutomaticCarParking {
	private int x_min, y_min = 0;
	private int x_max, y_max = 15;
	private int x_startPosition = 0;
	private int y_startPosition = 0;

	private int x_endPosition = 0;
	private int y_endPosition = 0;

	private int x = 0;
	private int y = 0;
	private Direction currentDirection = Direction.NORTH;

	private enum Direction {
		NORTH, SOUTH, EAST, WEST, NONE;
	}

	public static void main(String[] args) {
		String inArr[] = { "5,5:RFLFRFLF", "6,6:FFLFFLFFLFF", "5,5:FLFLFFRFFF" };

		AutomaticCarParking park = new AutomaticCarParking();
		for (String in : inArr) {
			park.setToDefault();
			System.out.println("For input :" + in);
			park.input(in);
			// park.displayEndPosition();
			park.displayOutPut();
			System.out.println("\n---------------\n");
		}
	}

	private void displayOutPut() {
		System.out.println(y + "," + x);
	}

	private void setToDefault() {
		currentDirection = Direction.NORTH;
		x = 0;
		y = 0;
		x_startPosition = 0;
		y_startPosition = 0;

		x_endPosition = 0;
		y_endPosition = 0;
	}

	void displayCurrentDirection() {
		System.out.println("currentDirection:" + currentDirection);
	}

	void displayNavigation(char navigation) {
		System.out.println("navigation:" + navigation);
	}

	void displayCurrentPosition() {
		System.out.println("	x_Position:" + x);
		System.out.println("	y_Position:" + y);
	}

	void displayStartPosition() {
		System.out.println("x_startPosition:" + x_startPosition);
		System.out.println("y_startPosition:" + y_startPosition);
	}

	void displayEndPosition() {
		System.out.println("x_endPosition:" + x_endPosition);
		System.out.println("y_endPosition:" + y_endPosition);
	}

	private void input(String inputParams)

	{
		String startPostionStr = inputParams.split(":")[0];

		x_startPosition = Integer.parseInt(startPostionStr.split(",")[0]);
		y_startPosition = Integer.parseInt(startPostionStr.split(",")[1]);

		displayStartPosition();
		x = x_startPosition;
		y = y_startPosition;

		String navigationStr = inputParams.split(":")[1];
		char navigationChars[] = navigationStr.toCharArray();
		for (char c : navigationChars) {
			switch (c) {
			case 'R':
			case 'L':
				setDirection(c);
				break;
			case 'F':
				try {
					changePosition();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Invalid Char :" + c);
			}
		}
		x_endPosition = x;
		y_endPosition = y;
	}

	private void setDirection(char navigation) {
		displayCurrentDirection();
		displayNavigation(navigation);
		switch (currentDirection) {
		case NORTH:

			currentDirection = ('L' == navigation) ? Direction.WEST
					: ('R' == navigation) ? Direction.EAST : Direction.NONE;

			break;
		case SOUTH:
			currentDirection = ('R' == navigation) ? Direction.WEST
					: ('L' == navigation) ? Direction.EAST : Direction.NONE;
			break;
		case EAST:
			currentDirection = ('R' == navigation) ? Direction.SOUTH
					: ('L' == navigation) ? Direction.NORTH : Direction.NONE;
			break;
		case WEST:
			currentDirection = ('L' == navigation) ? Direction.SOUTH
					: ('R' == navigation) ? Direction.NORTH : Direction.NONE;
			break;
		default:
			System.out.println("Error Direcction");
			break;
		}
	}

	private void changePosition() throws Exception {
		displayCurrentDirection();
		switch (currentDirection) {
		case NORTH:
			if (y == 15) {
				throw new Exception("Out of Boundry Limit:Y" + y);
			} else {
				y++;
			}
			break;
		case SOUTH:
			if (y == 0) {
				throw new Exception("Out of Boundry Limit:Y" + y);
			} else {
				y--;
			}
			break;
		case EAST:
			if (x== 15) {
				throw new Exception("Out of Boundry Limit:X" + x);
			} else {
				x++;
			}
			break;
		case WEST:
			if (x == 0) {
				throw new Exception("Out of Boundry Limit:X" + x);
			} else {
				x--;
			}
			break;
		default:
			break;

		}
		displayCurrentPosition();
	}
}
