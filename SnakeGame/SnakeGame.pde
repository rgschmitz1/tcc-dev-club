import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

int GRID_SIZE = 20;
int WIDTH = 600, HEIGHT = 600;

Snake snake = new Snake(GRID_SIZE, GRID_SIZE);
Food food = new Food(0, 0);

void setup() {
  size(600, 600);
  background(0);
  
  snake.draw_();
  
  food = food.generate(WIDTH, HEIGHT);
  food.draw_();
}

void draw() {
  background(0);
  food.draw_();
  if (keyPressed)
    if (key == CODED && Directions.contains(keyCode))
      snake.changeDirection(keyCode);
    else if (Directions.contains(key))
      snake.changeDirection(key);
  snake.move();
  snake.draw_();
  delay(100);
}




class Snake extends SnakeBody {
  ArrayList<SnakeBody> _tail = new ArrayList<SnakeBody>();
  Directions _direction = Directions.right;
  
  public Snake(int x, int y) {
    super(x, y);
    _color = color(255);
  }
  
  public void draw_() {
    stroke(0);
    fill(_color);
    rect(_x, _y, _size, _size);
    for (int i = 0; i < _tail.size(); i++)
      _tail.get(i).draw_();
  }
  
  public void changeDirection(Directions newDirection) {
    if (newDirection != _direction.getOpposite())
      _direction = newDirection;
  }
  
  public void changeDirection(char newDirection) {
    try {
      changeDirection(Directions.parseDirection(newDirection));
    } catch (Exception e) {} finally {}
  }
  
  public void changeDirection(int newDirection) {
    try {
      changeDirection(Directions.parseDirection(newDirection));
    } catch (Exception e) {} finally {}
  }
  
  public void move() {
    int lastX = _x;
    int lastY = _y;
    if (_direction == Directions.right && _x+_size <= WIDTH-_size)
      _x += _size;
    else if (_direction == Directions.left && _x-_size >= 0)
      _x -= _size;
    else if (_direction == Directions.down && _y+_size <= HEIGHT-_size)
      _y += _size;
    else if (_direction == Directions.up && _y-_size >= 0)
      _y -= _size;
    else if (_direction != Directions.stopped)
      _direction = Directions.stopped;
      
    int bufferX, bufferY;
    for (int i = 0; i < _tail.size(); i++) {
      bufferX = _tail.get(i).getX();
      bufferY = _tail.get(i).getY();
      if (_x == bufferX && _y == bufferY)
        _direction = Directions.stopped;
      _tail.get(i).moveTo(lastX, lastY);
      lastX = bufferX;
      lastY = bufferY;
    }
      
    if (food.isCollidingWith(_x, _y)) eat();
    
    if (_direction == Directions.stopped)
      reset();
  }
    
  public boolean isCollidingWith(int x, int y) {
    for (int i = 0; i < _tail.size(); i++)
      if (x == _tail.get(i).getX() && y == _tail.get(i).getY()) return false;
    return x != _x && y != _y;
  }
  
  public boolean isCollingWith(GridObject other) {
    return isCollidingWith(other.getX(), other.getY());
  }
  
  public void eat() {
    food = food.generate(WIDTH, HEIGHT);
    int dx = 0, dy = 0;
    int x = _tail.isEmpty()? _x : _tail.get(_tail.size()-1).getX(),
        y = _tail.isEmpty()? _y : _tail.get(_tail.size()-1).getY();
        
    int previousX, previousY;
    if (_tail.size() > 1) {
      previousX = _tail.get(_tail.size()-2).getX();
      previousY = _tail.get(_tail.size()-2).getY();
      dx = x-previousX;
      dy = y-previousY;
    }
    _tail.add(new SnakeBody(x+dx, y+dy));
  }
  
  public void reset() {
    _tail.clear();
    _x = GRID_SIZE;
    _y = GRID_SIZE;
    _direction = Directions.right;
    food = food.generate(WIDTH, HEIGHT);
  }
}



class SnakeBody extends GridObject {
  public SnakeBody(int x, int y) {
    super(x, y);
    _color = color(255);
  }
  
  public void moveTo(int x, int y) {
    _x = x;
    _y = y;
  }
  
  public void draw_() {
    stroke(0);
    fill(_color);
    rect(_x, _y, _size, _size);
  }
}



class Food extends GridObject {
  
  public Food(int x, int y) {
    super(x, y);
    _color =  color(255, 231, 112);
  }
  
  public Food generate(int width_, int height_) {
    int x = 0, y = 0;
    do {
      x = ThreadLocalRandom.current().nextInt(0, width_);
      x -= x % GRID_SIZE;
      y = ThreadLocalRandom.current().nextInt(0, height_);
      y -= y % GRID_SIZE;
    } while (snake.isCollidingWith(x, y));
    
    return new Food(x, y);
  }
  
  public void draw_() {
    stroke(0);
    fill(_color);
    rect(_x, _y, _size, _size);
  }
}



class GridObject {
  int _size = GRID_SIZE;
  int _x, _y;
  color _color;
  
  public GridObject(int x, int y) {
    _x = x;
    _y = y;
  }
  
  public int getX() {
    return _x;
  }
  
  public int getY() {
    return _y;
  }
  
  public boolean isCollidingWith(int x, int y) {
    return _x == x && _y == y;
  }
  
  public boolean isCollidingWith(GridObject other) {
    return other.getX() == _x && other.getY() == _y;
  }
}



enum Directions {
  up('w'), left('a'), down('s'), right('w'), stopped('\0');
  static Set<Character> _directions = new HashSet<Character>
    (Arrays.asList('w', 'a', 's', 'd'));
  static Set<Integer> _directionCodes = new HashSet<Integer>
    (Arrays.asList(UP, DOWN, LEFT, RIGHT));
    
  static {
    up._opposite = down;
    down._opposite = up;
    left._opposite = right;
    right._opposite = left;
  }
  
  char _dir;
  Directions _opposite;
  private Directions(char direction) {
    _dir = direction;
  }
  
  public Directions getOpposite() {
    return _opposite;
  }
  
  public static boolean contains(char direction) {
    return _directions.contains(direction);
  }
  
  public static boolean contains(int direction) {
    return _directionCodes.contains(direction);
  }
  
  public static Directions parseDirection(char direction) throws Exception {
    if (!Directions.contains(direction))
      throw new Exception(String.format("'%c' is not a valid direction", direction));
      
    Directions dir;
    if (Character.toLowerCase(direction) == 'w')
      dir = Directions.up;
    else if (Character.toLowerCase(direction) == 'a')
      dir = Directions.left;
    else if (Character.toLowerCase(direction) == 's')
      dir = Directions.down;
    else
      dir = Directions.right;
    return dir;
  }
  
  public static Directions parseDirection(int direction) throws Exception {
    if (!_directionCodes.contains(direction))
      throw new Exception(String.format("%d is not a valid direction"));
      
    Directions dir;
    if (direction == UP) dir = Directions.up;
    else if (direction == LEFT) dir = Directions.left;
    else if (direction == DOWN) dir = Directions.down;
    else dir = Directions.right;
    return dir;
  }
}