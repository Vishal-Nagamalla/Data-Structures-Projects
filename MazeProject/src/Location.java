public class Location {
	private int r, c;
	
	public Location(int row, int col)
	{
		this.r = row;
		this.c = col;
	}
	
	public int getR(){return this.r;}
	public int getC(){return this.c;}
	
	public void incR(int x){this.r+=x;}
	public void incC(int x){this.c+=x;}
	
	public void set(int newR, int newC)
	{
		this.r = newR;
		this.c = newC;
	}
	
	public boolean equals(Location other)
	{
		if((other.getR() == this.r)&&(other.getC() == this.c))
			return true;
		return false;
				
	}
}