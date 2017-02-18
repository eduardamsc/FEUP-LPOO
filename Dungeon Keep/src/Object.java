
public abstract class Object {

	//x->colunas y->linhas
		protected int x;
		protected int y;
		protected char symbol;

		public Object() {
			x=0;
			y=0;
		}
		
		public Object(int x, int y)
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
}
