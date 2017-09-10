import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

/**
 * Created by var on 29/6/17.
 *
 * Notes the place of zero and implementation depends on it.
 * A board is supposed to be immutable
 *
 */
public class Board {
    private int [][] board;
    private int size;

    private int[] zeropos;//save where zero is in the board..note if changed asin create new board


    public Board(int[][] blocks) {
        // construct a board from an n-by-n array of blocks
        // (where blocks[i][j] = block in row i, column j)
        zeropos=new int[2];
        size=blocks.length;
        board=new int[size][size];
        int count=0;
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){

                board[i][j]=(blocks[i][j]);
                if(blocks[i][j]==0)
                {
                    zeropos[0]=i;
                    zeropos[1]=j;
                }

            }
        }



    //make hamming and manhattan while construction?


    }



    public int dimension(){return  size;}                 // board dimension n



    public int hamming() {
//last is blank;

        int count=0;

        for(int i=0;i<size;i++) {
            for (int j = 0; j < size; j++) {

                if (board[i][j] != (i*size+j + 1)&& board[i][j]!=0)
                    count++;
            }
        }
        return  count;


    }                  // number of blocks out of place


    public int manhattan() {
        int count=0;
        for(int i=0;i<size;i++) {
            for (int j = 0; j < size; j++) {

                 if (board[i][j] == 0) {

                     continue;
                 }

                 count += manhattandistance(board[i][j], i,j);
             }

        }
        return count;
    }                // sum of Manhattan distances between blocks and goal

    private int manhattandistance(int val, int i,int j) {
       // System.out.println(val);
        int correctrow=(val-1)/size;
       // System.out.println(correctrow+" "+i);
        int currentcol=(val-1)%size ;
      //  System.out.println(currentcol+" "+j);
        return(Math.abs(correctrow-i)+Math.abs(j-currentcol));
    }


    public boolean isGoal() {

    return manhattan()==0;

    }               // is this board the goal board?


    public Board twin()       {
//doesnt change blank while creating twin
        Board twin=new Board(board);
        int i=0, j=0;
        if(twin.board[i][j]==0||twin.board[i][j+1]==0){
            i++;//j++ doesnt producetwin on all cases
        }
        //swap first  two "blocks"
        int temp=twin.board[i][j];
        twin.board[i][j]=twin.board[i][j+1];
        twin.board[i][j+1]=temp;
        return  twin;

    }             // a board that is obtained by exchanging any pair of blocks


    public boolean equals(Object y){

        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that=(Board )y;
        if(that.size!=this.size) return false;
        for(int i=0;i<size;i++) {
            for (int j = 0; j < size; j++) {

                if (board[i][j] != ((Board) y).board[i][j])
                    return false;
            }

        }
        return  true;
    }        // does this board equal y?


    public Iterable<Board> neighbors(){
        Stack<Board> it=new Stack<Board>();
        for(int n=0;n<4;n++){
            if(neighborExists(zeropos[0],zeropos[1],n,size)){
             Board newboard=createnewboard(zeropos[0],zeropos[1],n) ;
             it.push(newboard);

            }

        }
return  it;


    }     // all neighboring boards

    private Board createnewboard(int i, int j, int n) {

        Board neighbor=new Board(board);
        if (n==0){
            //swap zero by leftblock-> neighbor
            /*swap without temp as we know zero position->>wont work for neighbors neighbors
            neighbor.board[i][j]=neighbor.board[i][j-1];
            neighbor.board[i][j-1]=0;*/
            int temp=neighbor.board[i][j];
            neighbor.board[i][j]=neighbor.board[i][j-1];
            neighbor.board[i][j-1]=temp;
            neighbor.zeropos[1]-=1;
            return neighbor;
        }
        if (n==1){
            //swap zero by rightblock-> neighbor
            int temp=neighbor.board[i][j];
            neighbor.board[i][j]=neighbor.board[i][j+1];
            neighbor.board[i][j+1]=temp;
            neighbor.zeropos[1]+=1;
            return neighbor;
        }
        if (n==2){
            //swap zero by upperblock-> neighbor
            int temp=neighbor.board[i][j];
            neighbor.board[i][j]=neighbor.board[i-1][j];
            neighbor.board[i-1][j]=temp;
            neighbor.zeropos[0]-=1;
            return neighbor;
        }
        if (n==3){
            //swap zero by lowerblock-> neighbor
            int temp=neighbor.board[i][j];
            neighbor.board[i][j]=neighbor.board[i+1][j];
            neighbor.board[i+1][j]=temp;
            neighbor.zeropos[0]+=1;

        }

    return neighbor;}

    private boolean neighborExists(int i, int j, int n, int size) {
        if(n==0&&(j==0))
            //left
    return false;
        else if(n==1&&j==size-1){
            //right
            return false;

        }
        else if(n==2&&(i==0))
            //upper
            return false;
        else if(n==3&&i==size-1){
            //lower
            return false;

        }

            return true;
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(size + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();


    }              // string representation of this board (in the output format specified below)

    public static void main(String[] args) {
        // read in the board specified in the filename
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }

        // solve the slider puzzle
        Board initial = new Board(tiles);
        Board second=new Board(tiles);
        System.out.println(initial);
        System.out.println(initial.equals(second));
        System.out.println( initial.hamming());
        System.out.println( initial.manhattan());
       //1 System.out.println(initial.twin());

        //System.out.println(initial.neighbors());
        for ( Board i:initial.neighbors()) {
            System.out.println(i);
            System.out.println("manhattan is" + i.manhattan());
        }
       /* System.out.println("neighbors of neighbors");
        for ( Board i:initial.neighbors()){
        System.out.println(i.neighbors());}*/
    }// unit tests (not graded)
}