import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by var on 14/8/17.
 */
public class BreadthFirst {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s-v path
    private int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path
    private int source;

    public BreadthFirst(Graph G, int s) {

        source=s;
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
    for(int i:distTo){
        i=INFINITY;
    }
        bfs(G, s);


    }
    private void bfs(Graph G,int s){
        Queue<Integer> queue=new Queue<Integer>() ;
        queue.enqueue(s);
        marked[s] = true;
        distTo[s] = 0;

        while(!queue.isEmpty()){
          int top  =queue.dequeue();
          for(int i:G.adj(top)) {
              if (!marked[i]) {
                  marked[i] = true;
                  distTo[i] = distTo[top]+1;
                  edgeTo[i]=top;
                  queue.enqueue(i);
              }
          }
        }
    }
    public boolean hasPathTo(int v) {

        return marked[v];
    }
    public int distTo(int v) {

        return distTo[v];
    }
    public  Iterable<Integer> pathTo(int v){
        //is there a path from s to v?path from s to v; null if no such path
        Stack<Integer> stack=new Stack<>();
        int  current=edgeTo[v];
        stack.push(v);
        while(current!=source){
            if(current==edgeTo[current])
                return null;
            stack.push(current);
            current=edgeTo[current];

        }
        stack.push(source);
        return stack;
    }
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        // StdOut.println(G);

        int s = Integer.parseInt(args[1]);
        BreadthFirst bfs = new BreadthFirst(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v)) {
                StdOut.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
                for (int x : bfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("-" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d (-):  not connected\n", s, v);
            }

        }
    }

}

