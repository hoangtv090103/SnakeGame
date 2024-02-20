package com.snakegame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

/**
 * @see https://www.youtube.com/watch?v=bI6e6qjJ8JQ
 * Constants
 * SCREEN_WIDTH: Width of the game screen
 * SCREEN_HEIGHT: Height of the game screen
 * UNIT_SIZE: Size of the game unit
 * GAME_UNITS: Number of game units
 * DELAY: Delay of the game
 * x: x-coordinate of the snake
 * y: y-coordinate of the snake
 * bodyParts: Number of body parts
 * applesEaten: Number of apples eaten
 * appleX: x-coordinate of the apple
 * appleY: y-coordinate of the apple
 * direction: Direction of the snake
 * running: Running status of the game
 * timer: Timer of the game
 * random: Random number generator
 */
public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 75;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];

    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;


    /**
     * Constructor
     * Set the preferred size of the game panel
     * Set the background color of the game panel
     * Set the focusable of the game panel
     * Add the key listener to the game panel
     * Start the game
     */
    GamePanel() {

        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }


    /**
     * Start the game
     * Create a new apple
     * Set the running status to true
     * Create a new timer
     * Start the timer
     */
    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    /**
     * @param g the <code>Graphics</code> object to protect the drawing
     *          Draw the game panel
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * @param g the <code>Graphics</code> object to protect the drawing
     *          Draw the game panel
     *          Draw the grid
     *          Draw the apple
     *          Draw the snake
     *          Draw the score
     *          Draw the game over text
     *          Draw the restart button
     *          If the game is not running, draw the game over text
     *          If the game is not running, draw the restart button
     *          If the game is not running, stop the timer
     */
    public void draw(Graphics g) {
        if (!running) {
            gameOver(g);
        }

        // Draw grid
        for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }
        g.setColor(Color.red);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) {
                g.setColor(Color.green);
            } else {
                g.setColor(new Color(45, 189, 0));
                g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            }
            g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        }
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

    }

    /**
     * Create a new apple
     * Set the x-coordinate of the apple to a random number
     * Set the y-coordinate of the apple to a random number
     */
    public void newApple() {
        appleX = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        appleY = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }

    /**
     * Move the snake
     * For each body part, set the x-coordinate of the body part to the x-coordinate of the previous body part
     * For each body part, set the y-coordinate of the body part to the y-coordinate of the previous body part
     * If the direction is up, set the y-coordinate of the head to the y-coordinate of the head minus the unit size
     * If the direction is down, set the y-coordinate of the head to the y-coordinate of the head plus the unit size
     * If the direction is left, set the x-coordinate of the head to the x-coordinate of the head minus the unit size
     * If the direction is right, set the x-coordinate of the head to the x-coordinate of the head plus the unit size
     */
    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }


    /**
     * Check if the head touches the apple
     * If the head touches the apple, increase the number of body parts
     * If the head touches the apple, increase the number of apples eaten
     * If the head touches the apple, create a new apple
     */
    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    /**
     * Check if the head touches the body
     * If the head touches the body, set the running status to false
     * If the head touches the left border, set the x-coordinate of the head to the screen width
     * If the head touches the right border, set the x-coordinate of the head to 0
     * If the head touches the top border, set the y-coordinate of the head to the screen height
     * If the head touches the bottom border, set the y-coordinate of the head to 0
     * If the running status is false, stop the timer
     * If the head touches the body, stop the timer
     */
    public void checkCollisions() {
        // Check if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
                break;
            }
        }

        // Check if head touches left border
        if (x[0] < 0) {
            x[0] = SCREEN_WIDTH;
        }

        // Check if head touches right border
        if (x[0] > SCREEN_WIDTH) {
            x[0] = 0;
        }

        // Check if head touches top border
        if (y[0] < 0) {
            y[0] = SCREEN_HEIGHT;
        }

        // Check if head touches bottom border
        if (y[0] > SCREEN_HEIGHT) {
            y[0] = 0;
        }

        if (!running) {
            timer.stop();
        }
    }

    /**
     * @param g the <code>Graphics</code> object to protect the drawing
     *          Draw the game over text
     *          Draw the score
     *          Create a new restart button
     *          Add an action listener to the restart button
     *          If the restart button is clicked, start the game
     */
    public void gameOver(Graphics g) {
        // Score
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        // Gameover text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);

        JButton restartButton = new JButton("Restart");
        restartButton.setBounds(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 100, 50);
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
    }

    /**
     * @param e the event to be processed by the action listener
     *          Move the snake
     *          Check if the head touches the apple
     *          Check if the head touches the body
     *          Repaint the game panel
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    /**
     * Inner class MyKeyAdapter
     * KeyAdapter for the game panel
     * If the left key is pressed, set the direction to left
     * If the right key is pressed, set the direction to right
     * If the down key is pressed, set the direction to down
     * If the up key is pressed, set the direction to up
     */
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
            }
        }
    }
}
