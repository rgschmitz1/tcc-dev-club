public class GridObject {
	protected Game game;
	protected int x, y, color;
	public static final int size = 20;
	
	GridObject(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isCollidingWith(int x, int y) {
		return this.x == x && this.y == y;
	}
	
	public boolean isCollidingWith(GridObject other) {
		return x == other.getX() && y == other.getY();
	}
	
	public void display() {
		game.fill(color);
		game.rect(x, y, size, size);
	}
}