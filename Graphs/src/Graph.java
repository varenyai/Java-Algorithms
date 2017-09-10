import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;



/**
 * Created by var on 14/8/17.
 */

public class Graph {
    private int v;
   private  int e;
   private  Bag<Integer> [] graph;//Array of list representation with bag as list and array to store bag of each of the  vertices
    public Graph(int V){
        v=V;
        e=0;
        graph= (Bag<Integer>[])new Bag[V];
        for (int v = 0; v < V; v++)
            graph[v] = new Bag<Integer>();
    } //create an empty graph with V vertices

   public Graph(In in) {//create a graph from input stream
       v=in.readInt();
       e=0;
       int edge=in.readInt();
       graph= (Bag<Integer>[])new Bag[v];
       for (int v = 0; v < this.v; v++)
           graph[v] = new Bag<Integer>();
       for(int i=0;i<edge;i++){
           int a=in.readInt();
           int b=in.readInt();
           addEdge(a,b);
       }

    }


    public void addEdge(int v, int w){
        /*
    add an edge v-w to vertices adjacent to v*/
        if (v>=this.v||w>=this.v||v<0||w<0)
            throw new UnsupportedOperationException();
        graph[v].add(w);
        graph[w].add(v);
        e++;
    }

    public Iterable<Integer> adj(int v){
        return graph[v];
    }


    public int V(){return v;}// number of vertices

   public  int E(){return e;} //number of edges

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(v + " vertices, " + e + " edges " + "\n");
        for (int v = 0; v < this.v; v++) {
            s.append(v + ": ");
            for (int w : graph[v]) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
    public int degree(int v) {

        return graph[v].size();
    }
    public static void main(String [] args){
        In in = new In(args[0]);
        Graph G = new Graph(in);
        System.out.print(G);

    }

   // string representation
}