import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

import java.util.Queue;

/**
 * Created by var on 13/7/17.
 */
public class KdTree {
    private int size;
    private Node root;
    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        Node(){}
        Node(Point2D p,RectHV rect,Node lb,Node rt){
            this.p=p;this.rect=rect;this.lb=lb;
            this.rt=rt;

        }
    }

    public         KdTree(){}                               // construct an empty set of points
    public           boolean isEmpty(){return size==0;}                      // is the set empty?
    public               int size() {return size;}                        // number of points in the set
    public              void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        if(p==null)
            throw new IllegalArgumentException();

        else{
                root=insert(root,p,1);
        }



    }
    private Node insert(Node x,Point2D p,int level){

        if(x==null) {
            size++;
            return new Node(p,new RectHV(0.0,0.0,1.0,1.0),null,null);
        }
        //int cmp=);
        if(level%2==1){
            //on odd level
            // if x of nodes p>p's x==>go left
            if(x.p.x()<p.x())

            {
                level++;insert(x.lb,p,level);
            }
            else {
                level++;insert(x.rt,p,level);
            }

        }

        //if xs are same have to check y

    else{
            if(x.p.y()<p.y())

            {if(x.p.y()<p.y())

            {
                level++;insert(x.lb,p,level);
            }
            else {
                level++;insert(x.rt,p,level);
            }
                level++;insert(x.lb,p,level);
            }
            else {
                level++;insert(x.rt,p,level);
            }

        }



    }



    public           boolean contains(Point2D p){return true;}            // does the set contain point p?
    public              void draw()     {}                    // draw all points to standard draw
    public Iterable<Point2D> range(RectHV rect) {return new Stack<>() ;}            // all points that are inside the rectangle
    public           Point2D nearest(Point2D p)  {return new Point2D(0.0,0.0);}           // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args)   {}               // unit testing of the methods (optional)
}
