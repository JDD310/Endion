package jddennis_hw2;

import bridges.base.NamedColor;
import bridges.base.NamedSymbol;
import bridges.games.NonBlockingGame;

class Snake extends NonBlockingGame {
  // Game settings
  java.util.Random random = new java.util.Random();
  
  // Set dimensions of the game board
  static int gridColumns = 30;
  static int gridRows = 30;

  // Refresh rate
  final long FRAMERATE = 1000000000 / 15;
  long frameTime;
  long nextFrameTime;

  // Initial snake settings
  int startX = gridColumns / 3;
  int startY = gridRows / 2;
  int startLength = 3;
  
  // Similar to a chess board, the background will alternate in color
  NamedColor bc1 = NamedColor.forestgreen;	//board color
  NamedColor bc2 = NamedColor.green;		//board color
  
  // Setting up other game colors
  NamedColor sc = NamedColor.silver;		//snake color
  NamedColor hc = NamedColor.white;			//head color
  NamedColor ac = NamedColor.red;			//apple color

  // TODO: Create an element to represent the snake's head (call it head)
  // and tail (call it tail) - is there a class that could help us where 
  // the location of the head and tail could be encapsulated?
  //DONE
  
  Block head;
  
  Block tail;
  
  // TODO: Create an element to represent the apple
  //DONE

  Block apple;
  
  // TODO: Keep track of the snake's current and last direction
  //DONE
  
  Direction dir;

  Direction lastDir;
  
  // Constructor - set bridges credentials, grid size
  public Snake(int assid, String login, String apiKey, int c, int r) {
    super(assid, login, apiKey, c, r);
  }

  // Handle user input 
  public void handleInput() {
    // Based on which of the 4 arrow keys is pressed
	// move the snake in the requested particular direction
	// Note: if the snake is moving North, it cannot move South
	// meaning that it cannot turn on itself
	  
	// TODO: Construct an if statement that tests which key is pressed and
	// if the current and last directions do not equal the opposite direction
	// then set the current direction to the direction requested
	//DONE
	  
	  if (keyLeftStillPressed() && dir != Direction.EAST && lastDir != Direction.EAST)
		  dir = Direction.WEST;
	  
	  if (keyUpStillPressed() && dir != Direction.SOUTH && lastDir != Direction.SOUTH)
		  dir = Direction.NORTH;
	  
	  if (keyDownStillPressed() && dir != Direction.NORTH && lastDir != Direction.NORTH)
		  dir = Direction.SOUTH;
	  
	  if (keyRightStillPressed() && dir != Direction.WEST && lastDir != Direction.WEST)
		  dir = Direction.EAST;
  }

  // Update snake position
  public void updatePosition() {
    // Move the snake one position, based on its direction and update
    // the linked list
	Block current = head.next; //assumes you created a Block called head at the top of your class
    int nextX = head.x;
    int nextY = head.y;

    // Loops through the whole snake to move all of the blocks one space
    while (current != null) {
      int tempX = current.x;
      int tempY = current.y;
      current.x = nextX;
      current.y = nextY;
      nextX = tempX;
      nextY = tempY;
      current = current.next;
    }

    // TODO: handle edge cases - check to make sure the snake
    // doesn't go off the edge of the board; can do a wrap around
    // in X or Y to handle it. Must handle all 4 directions the snake
    // might be traveling..
    // DONE
    
    switch(dir) {
	case WEST:
		head.y--;
		if (head.y < 0)
			head.y = gridRows - 1;
		break;

	case EAST:
		head.y++;
		if (head.y == gridRows)
			head.y = 0;
		break;

	case SOUTH:
		head.x++;
		if (head.x == gridColumns)
			head.x = 0;
		break;

	case NORTH:
		head.x--;
		if (head.x < 0)
			head.x = gridColumns - 1;
		break;
    	}
  }

  // Create a new apple (position)
  public void plantApple() {
    // Randomly position the apple, taking care to ensure that it doesn't
    // intersect with the snake position.
	
	// TODO: Create an x and y (ints). You will want to get a random 
	// number between 0 and 29 for x and for y. You will want to keep
	// getting random numbers for x and y WHILE there is a collision
	// between the x or y and a block on the snake. You'll have to loop
	// through the snake much like in updatePosition() verifying whether
	// or not the x/y of a block is the same as the random x/y.
	// Once you find a valid x/y, you can set the apple's x/y to x/y.
	// DONE
	  	int x;
		int y;
		while (true) {
			x = Math.abs(random.nextInt() % 29);
			y = Math.abs(random.nextInt() % 29);

			boolean noCollision = true;

			Block current = head;
			while (current != null) {
				if (current.x == x && current.y == y) {
					noCollision = false;
					break;
				}
				current = current.next;
			}
			if (noCollision)
				break;
		}
		apple.x = x;
		apple.y = y;
  }

  // Check if snake has found the apple
  public void detectApple() {
    // If apple is found, snake consumes it and update the board and plant
    // a new apple on the board.
	  
	// TODO: If the head's x/y equals the apple's x/y, add a new tail
	// and plant an apple
	if (head.x == apple.x && head.y == apple.y) {
		Block newTail = new Block(tail.x, tail.y);
		drawSymbol(apple.x, apple.y, NamedSymbol.none, ac);
		tail.next = newTail;
		tail = newTail;
		plantApple();
		}
  }

  // Check if snake ate itself! Yuk!
  public void detectDeath() {
	// TODO: Loop through the snake's body and determine if the head's x/y
	// equals any of the current's x/y throughout the loop. If it does, 
	// Sytem.exit(0)
	//DONE
	  
	  Block current = head.next;
	  while (current != null) {
		  	if (head.x == current.x && head.y == current.y) {
		  		System.exit(0);
		  }
		  current = current.next;
	  }
  }

  // Redraw
  public void paint() {
    // TODO: Draw the board, the apple and the snake
    // make sure to choose colors so that snake and apple are clearly visible.
	  for (int i = 0; i < gridColumns; ++i) {
			for (int j = 0; j < gridRows; ++j) {
				if (i % 2 == j % 2)
					setBGColor(i, j, bc1);
				else
					setBGColor(i, j, bc2);
			}
		}

		setBGColor(head.x, head.y, hc);

		drawSymbol(apple.x, apple.y, NamedSymbol.apple, ac);

		Block current = head.next;
		while (current != null) {
			setBGColor(current.x, current.y, sc);
			current = current.next;
		}
  
  }

  // Set up the first state of the game grid
  public void initialize() {
    // TODO: Create the snake of some number of elements,
    // perform all initializations, place the apple
	
	// TODO: Draw the background of the board exactly as was done in repaint()
	  for (int i = 0; i < gridColumns; ++i) {
			for (int j = 0; j < gridRows; ++j) {
				if (i % 2 == j % 2)
					setBGColor(i, j, bc1);
				else
					setBGColor(i, j, bc2);
			}
		}
	// TODO: Set head to a new block passing it the start x/y
	//DONE
	  
	  head = new Block(startX, startY);
	  
	  //loops through the snake based on the start length and colors
	  //the board accordingly
	  
	  Block current = head;

	  for (int i = 0; i < startLength; ++i) {
	    setBGColor(startY, startX - i, sc);
	    if (i > 0) {
	      current.next = new Block(startX - i, startY);
	      current = current.next;
	    }
	  }
	  tail = current;
	
	  frameTime = System.nanoTime();
	  nextFrameTime = frameTime + FRAMERATE + 10000;
	  dir = Direction.EAST;
	  lastDir = dir;
	  apple = new Block();
	  
	// TODO: plant an apple
	  plantApple();
  }

  // Game loop will run many times per second.
  // handle input, check if apple was detected, update position, redraw,
  // detect if snake ate itself
  public void gameLoop() {
	  // TODO: handle the input
	  //DONE
	  handleInput();
	  
	  if (System.nanoTime() > nextFrameTime) {
	    frameTime = System.nanoTime();
	    nextFrameTime = frameTime + FRAMERATE;

	    // TODO: set the last direction equal to direction
	    //DONE
	    lastDir = dir;
	    
	    // TODO: detect an apple
	    //DONE
	    detectApple();
	    
	    // TODO: update the position
	    //DONE
	    updatePosition();
	    
	    // TODO: paint the screen
	    //DONE
	    paint();
	    
	    // TODO: detect death
	    //DONE
	    detectDeath();
	  }
  }

  public static void main(String args[]) {
    Snake game = new Snake(4, "jddennis", "516868507343",
                           gridColumns, gridRows);
    game.setTitle("JDDSnake");

    game.start();
  }
}

enum Direction {
  NORTH,
  SOUTH,
  EAST,
  WEST
}

// helper class to hold snake and apple objects; snake grows as it
// eats and hence a linked list of blocks
class Block {
  public Block next;
  public int x;
  public int y;

  public Block() {
    this(-1, -1, null);
  }

  public Block(int x, int y) {
    this(x, y, null);
  }

  public Block(int x, int y, Block next) {
    this.x = x;
    this.y = y;
    this.next = next;
  }
}
