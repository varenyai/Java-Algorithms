
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    /*grid is needed to support isopen
     * no similar array for full as it can be determined by connectionfrom top
     * opengrid is  used as open maynot necessarily  be on a aloof site  */
    private boolean [][] grid;
    private WeightedQuickUnionUF opengrid;
    private  WeightedQuickUnionUF fullgrid;
    private int gridsize;
    private int open=0;


    public Percolation(int n) {             // create n-by-n grid, with all sites blocked .makes connection on top,bottom
       if(n<=0)
           throw new IllegalArgumentException();

        gridsize=n;
        opengrid=new WeightedQuickUnionUF((n+1)*(n+1));
        fullgrid=new WeightedQuickUnionUF((n+1)*(n+1));
        grid=new boolean[n][n];
        /*
        for (int i=0;i<n;i++) {
            for (int j = 0; j < n; j++) {

                System.out.println("i ,j bool are "+i+" "+j+" " +grid[i][j]);
            }


        }*/
        //make top and bottom virtuall sites by unioin of 1 and n row elements

        for(int i=1;i<n;i++){
        opengrid.union(gridtoint(1,1)+i,gridtoint(1,1));
        opengrid.union(gridtoint(n,1)+i,gridtoint(n,1));
        fullgrid.union(gridtoint(1,1)+i,gridtoint(1,1));
        fullgrid.union(gridtoint(n,1)+i,gridtoint(n,1));
        }
    }



    private int gridtoint(int n,int m){

       return (n-1)*(gridsize)+m-1 ;


        }
    /*private int[] inttogrid(int n){
        //returned must be 0<n
        return 0;};
        */

//open the current,connect opengrid if neighbor is open and if it became full,connect that one too
    public    void open(int row, int col) {

        if (row <1||row > gridsize) throw new IndexOutOfBoundsException("row index i out of bounds");
        if (col <1|| col > gridsize) throw new IndexOutOfBoundsException("column index i out of bounds");
       if( isOpen(row, col))return;
        grid[row-1][col-1]=true;
       // System.out.println(row+"  "+col);
        //if a neighbor is alreasy open,connect with it
        if (existsInGrid(row-1,col)&&isOpen(row-1,  col)){
           // System.out.println("connecting -1,0");
            opengrid.union(gridtoint(row,col),gridtoint(row-1,col));

         //update the full grid on connection
        if(opengrid.connected(gridtoint(row,col),0))
            fullgrid.union(gridtoint(row,col),gridtoint(1,1));
        fullgrid.union(gridtoint(row,col),gridtoint(row-1,col));
        }


        if (existsInGrid(row+1,col)&&isOpen(row+1,  col)) {
            //System.out.println("connecting 1,0");
            opengrid.union(gridtoint(row, col), gridtoint(row + 1, col));
            if(opengrid.connected(gridtoint(row,col),0))
                fullgrid.union(gridtoint(row,col),gridtoint(1,1));
            fullgrid.union(gridtoint(row,col),gridtoint(row+1,col));
        }

        if (existsInGrid(row,col-1)&&isOpen(row,  col-1)){
            //System.out.println("connecting 0,-1");
            opengrid.union(gridtoint(row,col),gridtoint(row,col-1));

            if(opengrid.connected(gridtoint(row,col),0))
                fullgrid.union(gridtoint(row,col),gridtoint(1,1));
            fullgrid.union(gridtoint(row,col),gridtoint(row,col-1));
        }

        if (existsInGrid(row,col+1)&&isOpen(row,  col+1)){
            opengrid.union(gridtoint(row,col),gridtoint(row,col+1));
          //  System.out.println("connecting 0,1");
            if(opengrid.connected(gridtoint(row,col),0))
                fullgrid.union(gridtoint(row,col),gridtoint(1,1));
           fullgrid.union(gridtoint(row,col),gridtoint(row,col+1));
        }

        open++;
    }   // open site (row, col) if it is not open already

    private boolean existsInGrid(int row, int col) {
        if(row>0&&row<=gridsize&&col>0&&col<=gridsize)
            return true;
        else
            return false;
    }


    public boolean isOpen(int row, int col) {
        if (row <1|| row > gridsize) throw new IndexOutOfBoundsException("row index i out of bounds");
        if (col <1|| col > gridsize) throw new IndexOutOfBoundsException("column index i out of bounds");
        return grid[row-1][col-1];} // is site (row, col) open?//connect cant be used



    public boolean isFull(int row, int col) {
        if(isOpen(row,col)==false)return false;
       if (row <1|| row > gridsize) throw new IndexOutOfBoundsException("row index i out of bounds");
       if (col <1|| col > gridsize) throw new IndexOutOfBoundsException("column index i out of bounds");
        return fullgrid.connected(gridtoint(row,col),0);} // is site (row, col) full?


    public     int numberOfOpenSites(){return open;}       // number of open sites


    public boolean percolates()    {
        if (open>0)
            return fullgrid.connected(0,(gridsize*gridsize)-1);
    else return  false;}          // does the system percolate?

    public static void main(String[] args){

        Percolation perc = new Percolation(2);
        System.out.println(perc.gridsize*perc.gridsize);
        System.out.println(  perc.gridtoint(1,1));
        System.out.println(  perc.gridtoint(1,2));
        System.out.println(  perc.gridtoint(2,1));
        System.out.println(  perc.gridtoint(2,2));
       perc.open(1,1);
       System.out.println( perc.isFull(1,1));
        System.out.println( perc.isOpen(1,1));

        perc.open(2,2);
        perc.open(1,2);
        System.out.println( perc.isFull(2,1));
        System.out.println( perc.isOpen(2,1));
        System.out.println( perc.isFull(2,2));
        System.out.println( perc.isOpen(2,2));
        System.out.println( perc.isFull(1,2));
        System.out.println( perc.isOpen(1,2));
        for (int i =0;i<4;i++) {
            System.out.println("full"+i);
            System.out.println(perc.fullgrid.find(i));
            System.out.println("open");
            System.out.println(perc.opengrid.find(i));
        }
        System.out.println( perc.opengrid.connected(0,1));
        System.out.println( perc.fullgrid.connected(0,2));
        System.out.println( perc.opengrid.connected(0,3));
        System.out.println( perc.opengrid.connected(1,3));
        System.out.println( perc.fullgrid.connected(1,2));
    }   // test client (optional)
}