public class Location {
    private int row, col;
    private char[][] gameMatrix;
    public Location(int row,int col){
        this.row = row;
        this.col = col;
    }
    public int getRow() {return row;}
    public int getCol() {return col;}

    public void set(int row, int col){this.row = row; this.col = col;}

    public void incR(int x){
        if(row>0 && row< gameMatrix.length-1 && (gameMatrix[row+x][col]!='#'))
            row+=x;
    }
    public void incR(int x, boolean hasKey){
        if(row>0 && row< gameMatrix.length-1 && gameMatrix[row+x][col]!='#' && (gameMatrix[row+x][col]!='D'|| hasKey) )
            row+=x;
    }

    public void incC(int x){
        if(col>0 && col< gameMatrix[0].length-1 && gameMatrix[row][col+x]!='#')
            col+=x;
    }
    public void incC(int x, boolean hasKey){
        if(col>0 && col< gameMatrix[0].length-1 && gameMatrix[row][col+x]!='#' && (gameMatrix[row][col+x]!='D'||hasKey))
            col+=x;
    }

    public void setGameMatrix(char[][] matrix){
        gameMatrix = matrix;
    }

    public boolean equals(Location other){
        if(row == other.row && col == other.col)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Row: "+row+" Col: "+col;
    }
}
