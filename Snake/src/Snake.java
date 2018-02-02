import java.util.List;
import java.util.ArrayList;

import processing.core.PConstants;

public class Snake extends SnakeBody{
	private List<SnakeBody> tail = new ArrayList<> ();
	private String direction = "right";

	Snake(Game game) {
		super(game, GridObject.size, GridObject.size);
	}
	
	public void display() {
		super.display();
		for (int i = 0; i < tail.size(); i++)
			tail.get(i).display();
	}
	
	public void move() {
		int lastX = x,
			lastY = y;

		if (direction.equals("up") && y-GridObject.size >= 0)
			y -= GridObject.size;
		else if (direction.equals("left") && x-GridObject.size >= 0)
			x -= GridObject.size;
		else if (direction.equals("down") && y+GridObject.size <= game.height-GridObject.size)
			y += GridObject.size;
		else if (direction.equals("right") && x+GridObject.size <= game.width-GridObject.size)
			x += GridObject.size;
		else if (!direction.equals("stopped"))
			direction = "stopped";
		
		int bufferX, bufferY;
		SnakeBody current;
		for (int i = 0; i < tail.size(); i++) {
			current = tail.get(i);
			bufferX = current.getX();
			bufferY = current.getY();
			if (current.isCollidingWith(this))
				direction = "stopped";
			current.moveTo(lastX, lastY);
			lastX = bufferX;
			lastY = bufferY;
		}

		if (direction.equals("stopped")) reset();
		if (super.isCollidingWith(game.getFoodX(), game.getFoodY())) eat();
	}
	
	public void changeDirection(char direction) {
		if (direction == 'w' && !this.direction.equals("down")) this.direction = "up";
		else if (direction == 'a' && !this.direction.equals("right")) this.direction = "left";
		else if (direction == 's' && !this.direction.equals("up")) this.direction = "down";
		else if (direction == 'd' && !this.direction.equals("left")) this.direction = "right";
	}
	
	public void changeDirection(int direction) {
		char dir = '\0';
		if (direction == PConstants.UP) dir = 'w';
		else if (direction == PConstants.LEFT) dir = 'a';
		else if (direction == PConstants.DOWN) dir = 's';
		else if (direction == PConstants.RIGHT) dir = 'd';
		if (dir != '\0') changeDirection(dir);
	}
	
	public void reset() {
		x = y = GridObject.size;
		direction = "right";
		tail.clear();
		game.generateFood();
	}
	
	public void eat() {
		int x = tail.isEmpty()? this.x : tail.get(tail.size()-1).getX();
		int y = tail.isEmpty()? this.y : tail.get(tail.size()-1).getY();
		tail.add(new SnakeBody(game, x, y));

		while (isCollidingWith(game.getFoodX(), game.getFoodY()))
			game.generateFood();
	}
	
	public boolean isCollidingWith(GridObject other) {
		for (int i = 0; i < tail.size(); i++)
			if (tail.get(i).isCollidingWith(other)) return true;
		return super.isCollidingWith(other);
	}
}