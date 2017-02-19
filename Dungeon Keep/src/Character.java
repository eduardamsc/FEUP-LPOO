
public abstract class Character {

	//x->colunas y->linhas
	protected int x;
	protected int y;
	protected char symbol;
	
	

	public Character() {
		x=0;
		y=0;
	}
	
	public Character(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	
	//gets e sets coordenadas
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x=x;
	}
	
	public void setY(int y)
	{
		this.y=y;
	}
	
	
	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	public abstract void defineSymbol();
	
	//movimentos
	public void moveUp() {
		x--;
	}

	public void moveDown() {
		x++;
	}

	public void moveLeft() {
		y--;
	}

	public void moveRight() {
		y++;
	}
	
	public boolean wall(Map map, char direction)
	{
		if (direction == 'w')
		{
			if (map.getMap()[x-1][y] == 'X' || map.getMap()[x-1][y] == 'k' || map.getMap()[x-1][y] == 'I'
					|| map.getMap()[x-1][y] == 'G'|| map.getMap()[x-1][y] == 'H' || map.getMap()[x-1][y] == 'O') return false;
		}
		
		if (direction == 's')
		{
			if (map.getMap()[x+1][y] == 'X' || map.getMap()[x+1][y] == 'k' || map.getMap()[x+1][y] == 'I'
					|| map.getMap()[x+1][y] == 'G'|| map.getMap()[x+1][y] == 'H' || map.getMap()[x+1][y] == 'O') return false;
		}
		
		if (direction == 'a')
		{
			if (map.getMap()[x][y-1] == 'X' || map.getMap()[x][y-1] == 'k' || map.getMap()[x][y-1] == 'I'
					|| map.getMap()[x][y-1] == 'G'|| map.getMap()[x][y-1] == 'H' || map.getMap()[x][y-1] == 'O') return false;
		}
		
		if (direction == 'd')
		{
			if (map.getMap()[x][y+1] == 'X' || map.getMap()[x][y+1] == 'k' || map.getMap()[x][y+1] == 'I'
					|| map.getMap()[x][y+1] == 'G'|| map.getMap()[x][y+1] == 'H' || map.getMap()[x][y+1] == 'O') return false;
		}
		
		return true;
	}

	
}
