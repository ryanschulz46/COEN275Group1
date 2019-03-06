package Game;

import Asteroids.Asteroid;
import Ship.Ship;
import Stars.Star;
import java.awt.Point;

import java.util.ArrayList;

public class GameState {

	public enum State {
		MENU, GAME, PAUSED, DEATH, WIN, EXIT
	}

	private State gameState;
	private Game game;

	private int level;

	private ArrayList<Asteroid> asteroids;
	private ArrayList<Star> stars;
	private Ship ship;

	public int lastAsteroidIter, dodgeCount;

	private long startTime;
	private long timeAlive = 0L;
	
	public GameState(Game game) {
		this.game = game;
		gameState = State.MENU;
		resetState();
	}

	private void resetState() {
		level = 0;
		lastAsteroidIter = 0;
		dodgeCount = 0;
		asteroids = new ArrayList<Asteroid>();
		stars = new ArrayList<Star>();

		ship = new Ship(new Point.Double(game.getSize().width / 2, (int) (game.getSize().height * 0.8)), 2);
	}

	public State getState() {
		return gameState;
	}

	public Ship getShip() {
		return this.ship;
	}

	public ArrayList<Asteroid> getAsteroids() {
		return asteroids;
	}

	public ArrayList<Star> getStars() {
		return stars;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getTimeAlive() {
		return timeAlive;
	}

	public void setTimeAlive(long time) {
		timeAlive = time;
	}

	public long getStartTime() {
		return startTime;
	}

	// state manager, mirrors the one in game

	// starts a new game
	public void startGame() {
		resetState();
		startTime = System.currentTimeMillis();
		gameState = State.GAME;
	}

	// if game is paused, resume game
	public void resumeGame() {
		gameState = State.GAME;
	}

	// game has been won, switch to win state
	public void finishGame() {
		gameState = State.WIN;
	}

	// game has been lost, switch to lose state
	public void endGame() {
		gameState = State.DEATH;
	}

	// return to menu
	public void exitGame() {
		gameState = State.EXIT;
	}
}
