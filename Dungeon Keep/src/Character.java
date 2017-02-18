
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
		y--;
	}

	public void moveDown() {
		y++;
	}

	public void moveLeft() {
		x--;
	}

	public void moveRight() {
		x++;
	}
	
	public boolean wall(Map map, char direction)
	{
		if (direction == 'w')
		{
			if (map.getMap()[y-1][x] == 'X' || map.getMap()[y-1][x] == 'k' || map.getMap()[y-1][x] == 'I'
					|| map.getMap()[y-1][x] == 'G'|| map.getMap()[y-1][x] == 'H' || map.getMap()[y-1][x] == 'O') return false;
		}
		
		if (direction == 's')
		{
			if (map.getMap()[y+1][x] == 'X' || map.getMap()[y+1][x] == 'k' || map.getMap()[y+1][x] == 'I'
					|| map.getMap()[y+1][x] == 'G'|| map.getMap()[y+1][x] == 'H' || map.getMap()[y+1][x] == 'O') return false;
		}
		
		if (direction == 'a')
		{
			if (map.getMap()[y][x-1] == 'X' || map.getMap()[y][x-1] == 'k' || map.getMap()[y][x-1] == 'I'
					|| map.getMap()[y][x-1] == 'G'|| map.getMap()[y][x-1] == 'H' || map.getMap()[y][x-1] == 'O') return false;
		}
		
		if (direction == 'd')
		{
			if (map.getMap()[y][x+1] == 'X' || map.getMap()[y][x+1] == 'k' || map.getMap()[y][x+1] == 'I'
					|| map.getMap()[y][x+1] == 'G'|| map.getMap()[y][x+1] == 'H' || map.getMap()[y][x+1] == 'O') return false;
		}
		
		return true;
	}

	
}
