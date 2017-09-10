import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by var on 29/6/17.
 */
public class Solver {
    /*use static ids for boards*/
    private class Node implements Comparable<Node> {
        Board board;
        int NoOfMoves;
        Node prev;

        // boolean type;
        Node(Board board, int moves, Node prev) {
            this.board = board;
            this.prev = prev;
            NoOfMoves = moves;
        }

        Node(Board board) {
            this(board, 0, null);
        }


        public int compareTo(Node node) {
            return (this.board.manhattan() - node.board.manhattan()) + (this.NoOfMoves - node.NoOfMoves);
        }
    }
    private  MinPQ<Node> pq;
    private  MinPQ<Node> qp;
    //private int size=pq.size();
    private int moves=0;
    private boolean solved=true;//true if solved on initial and false on twin
    private Node goal;


    public Solver(Board initial) {

        if (initial == null)
            throw new IllegalArgumentException();

        //get a goal board  to compare
        int size = initial.dimension();
        int[][] goaltiles = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                goaltiles[i][j] = i * size + j + 1;
            }
        }
        goaltiles[size - 1][size - 1] = 0;
        Board goalboard = new Board(goaltiles);
        goal = new Node(goalboard);

        pq = new MinPQ<Node>();
        qp = new MinPQ<Node>();
        pq.insert(new Node(initial));
        qp.insert(new Node(initial.twin()));
        Node current = pq.delMin();
        Board currentBoard = current.board;

        /*System.out.println("node deleted is");
        System.out.print(currentBoard);
*/

        Node currenttwin = qp.delMin();
        Board currentBoardtwin = currenttwin.board;

/*

        System.out.println("node deleted twin is");
        System.out.print(currentBoardtwin);
*/

        while (true) {


        boolean  done=insertNeighbours(current,currentBoard,pq, true);
       done=insertNeighbours(currenttwin,currentBoardtwin,qp, false);



            moves++;
            if(done)break;

        }           // find a solution to the initial board (using the A* algorithm)

    }

    private boolean insertNeighbours(Node current, Board currentBoard,MinPQ<Node> pq,boolean flag) {

        for (Board i : currentBoard.neighbors()) {
            if (current.prev != null && (i.equals(current.prev.board)))
                continue;


            Node newNode = new Node(i, moves, current);
            pq.insert(newNode);



        }
        current = pq.delMin();
        currentBoard = current.board;
        if (currentBoard.equals(goal.board)) {
            goal = current;
            if (!flag){solved=false;}
        return  true;
        }return false;
    }

    public boolean isSolvable()  {return solved;}          // is the initial board solvable?



    public int moves()  {
       if (isSolvable())
    return moves;
       else
           return -1;
    }                   // min number of moves to solve initial board; -1 if unsolvable



    public Iterable<Board> solution() {
        if(!isSolvable())
            return null;
        Stack<Board> p=new Stack<Board>();
        Node  q=goal;
        for  (int i=0;i<moves;i++){
            p.push(q.board);
            q= q.prev;

    }
    return p;
    }     // sequence of boards in a shortest solution; null if unsolvable


    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }


    }// solve a slider puzzle (given below)
}