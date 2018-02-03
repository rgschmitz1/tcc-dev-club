
public class SnakeBody extends GridObject {
	SnakeBody(Game game, int x, int y) {
		super(game, x, y);
		color = game.color(255);
	}
	
	protected void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
