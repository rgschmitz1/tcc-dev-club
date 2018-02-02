import processing.core.PApplet;
import processing.core.PConstants;

public class Game extends PApplet {
	private Food food;
	private Snake snake;

	public static void main(String[] args) {
		PApplet.main("Game");
	}
	
	public void settings() {
		size(600, 600);
	}
	
	public void setup() {
		food = new Food(this);
		snake = new Snake(this);

		snake.display();
		food.display();
	}
	
	public void draw() {
		background(0);
		snake.move();
		snake.display();
		food.display();
		delay(100);
	}
	
	public void keyPressed() {
		if (key == PConstants.CODED && (keyCode == PConstants.UP || keyCode == PConstants.LEFT ||
										keyCode == PConstants.DOWN || keyCode == PConstants.RIGHT))
			snake.changeDirection(keyCode);
		else if (key == 'w' || key == 'a' || key == 's' || key == 'd')
			snake.changeDirection(key);
	}
	
	public void generateFood() {
		food.generate();
	}
	
	public int getFoodX() {
		return food.getX();
	}
	
	public int getFoodY() {
		return food.getY();
	}
}
