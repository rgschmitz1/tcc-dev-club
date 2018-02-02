public class Food extends GridObject {
	Food(Game game) {
		super(game, 0, 0);
		color = game.color(255, 231, 112);
		generate();
	}
	
	public void generate() {
		x = (int) game.random(game.width);
		x -= x % GridObject.size;
		y = (int) game.random(game.height);
		y -= y % GridObject.size;
	}
}
