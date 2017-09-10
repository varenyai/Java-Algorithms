import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

/**
 * Created by var on 14/8/17.
 */
public class Paths{
    private boolean [] visited ;
    private int[] edgefrom;
   private  int source;
    Paths(Graph G, int s){
    // find paths in G from source s
        source=s;
        visited=new boolean[G.V()];
        edgefrom=new int[G.V()];

        for(int i=0;i<G.V();i++){
            edgefrom[i]=i;
            visited[i]=false;
        }
        Stack<Integer> stack=new Stack<>();
        stack.push(s);
        while(!stack.isEmpty()){
            int current=stack.pop();
            visited[current]=true;
            for(int i:G.adj(current) ){
                if(!visited[i]) {
                    edgefrom[i] = current;
                    stack.push(i);
                }
            }
        }

}

      public  boolean hasPathTo(int v){
       int  current=edgefrom[v];
        while(current!=source){
            if(current==edgefrom[current])
                return false;
            current=edgefrom[current];
        }return true;

      }
       public  Iterable<Integer> pathTo(int v){
           //is there a path from s to v?path from s to v; null if no such path
           Stack<Integer> stack=new Stack<>();
           int  current=edgefrom[v];
           stack.push(v);
           while(current!=source){
               if(current==edgefrom[current])
                   return null;
               stack.push(current);
               current=edgefrom[current];

           }
           stack.push(source);
           return stack;
       }
public static void main(String[] args){
    In in = new In(args[0]);
    Graph G = new Graph(in);
   // System.out.print(G);
    Paths paths=new Paths(G,0);
    for (int v = 0; v < G.V(); v++){
        if (paths.hasPathTo(v))

    System.out.print(paths.pathTo(v)+"\n");}
}

        }