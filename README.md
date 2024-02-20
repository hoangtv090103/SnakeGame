# Snake Game in Java

This project is a simple implementation of the classic Snake game, written in Java and using the Swing library for the
graphical user interface. The game logic is encapsulated in a class named `GamePanel`, which extends `JPanel` and
implements `ActionListener`.
The `GameFrame` class is a subclass of `JFrame` and serves as the main window of the Snake game. It is responsible for
setting up the game window and adding the `GamePanel` to it. Here's a brief description of its functionality:

## GameFrame Class

The `GameFrame` class is responsible for creating the main window of the game. It extends `JFrame`, which is a class
from the Swing library used to create windows. It has a single constructor that sets up the game window.

### Constructor

- `GameFrame()`: This constructor creates a new `GamePanel` and adds it to the window. It sets the title of the window
  to "Snake", specifies that the application should exit when the window is closed, makes the window non-resizable,
  packs the window to the preferred size of its components, makes the window visible, and centers the window on the
  screen.

You can add this description to your `README.md` file to provide more information about the `GameFrame` class and its
role in the project.

## GamePanel Class

The `GamePanel` class is the heart of the game, containing all the game logic and rendering. It has several attributes
and methods:

### Attributes

- `SCREEN_WIDTH`, `SCREEN_HEIGHT`, `UNIT_SIZE`, `GAME_UNITS`, and `DELAY`: These constants define the game's dimensions
  and speed.
- `x[]` and `y[]`: These arrays store the x and y coordinates of all parts of the snake.
- `bodyParts`: This variable represents the current length of the snake.
- `applesEaten`: This variable keeps track of the number of apples the snake has eaten.
- `appleX` and `appleY`: These variables hold the coordinates of the current apple.
- `direction`: This variable stores the current direction of the snake.
- `running`: This boolean indicates whether the game is running.
- `timer`: This `javax.swing.Timer` triggers the game's actions.
- `random`: This `java.util.Random` instance is used to generate random positions for the apples.

### Methods

- `GamePanel()`: This constructor initializes the game panel and starts the game. It sets the preferred size of the
  panel, its background color, makes it focusable, adds a key listener, and calls the `startGame()` method.
- `startGame()`: This method places the first apple, sets the game as running, and starts the timer.
- `paintComponent(Graphics g)`: This method is overridden from `JPanel` and is called every time the panel needs to be
  redrawn. It calls the `draw(Graphics g)` method.
- `draw(Graphics g)`: This method draws the game grid, the apple, the snake, and the score. If the game is not running,
  it calls the `gameOver(Graphics g)` method.
- `newApple()`: This method generates a new apple at a random position.
- `move()`: This method moves the snake in the current direction.
- `checkApple()`: This method checks if the snake has eaten an apple. If so, it increases the length of the snake,
  increments the score, and generates a new apple.
- `checkCollisions()`: This method checks if the snake has collided with itself or the borders of the game area. If a
  collision is detected, it stops the game.
- `gameOver(Graphics g)`: This method displays the game over message and the final score.
- `actionPerformed(ActionEvent e)`: This method is the action performed at each tick of the timer. If the game is
  running, it moves the snake, checks for apple eating, and checks for collisions. Then it repaints the game panel.

### Inner Class

- `MyKeyAdapter`: This inner class extends `KeyAdapter` and overrides the `keyPressed(KeyEvent e)` method to change the
  direction of the snake according to the arrow key pressed by the user.
  This project is a great example of how to create a simple game using Java and Swing. It demonstrates the use of key
  Java concepts such as inheritance, interfaces, inner classes, and arrays. It also shows how to use the Swing library
  to create a graphical user interface and handle user input.

[Snake Game in Java - BroCode](https://www.youtube.com/watch?v=bI6e6qjJ8JQ)
[![Snake Game in Java](https://img.youtube.com/vi/bI6e6qjJ8JQ/0.jpg)](https://www.youtube.com/watch?v=bI6e6qjJ8JQ)