import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Queue;
import java.util.TreeSet;
/**
 * Created by var on 13/7/17.
 */
public class PointSET {

  private TreeSet<Point2D> pointset;
    public         PointSET(){

        pointset=new TreeSet<>();

    }                               // construct an empty set of points
    public           boolean isEmpty(){

        return pointset.isEmpty();
    }                      // is the set empty?
    public               int size() {

        return pointset.size();
    }                        // number of points in the set



    public              void insert(Point2D p) {pointset.add(p);}             // add the point to the set (if it is not already in the set)
    public           boolean contains(Point2D p){return pointset.contains(p);}            // does the set contain point p?
    public              void draw()     {


        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        for(Point2D i:pointset){
            i.draw();
        }

    }                    // draw all points to standard draw


    public Iterable<Point2D> range(RectHV rect) {
        if (rect==null)
            throw new IllegalArgumentException();
        Stack<Point2D>it=new Stack<>() ;
        for(Point2D i:pointset){
            if(rect.contains(i)){
                it.push(i);
            }
        }
return it;

    }            // all points that are inside the rectangle



    public           Point2D nearest(Point2D p)  {
        if(p==null)
            throw new IllegalArgumentException();


        /*
        Points aren't ordered by distance
        Point2D floor=pointset.floor(p);
        Point2D ceiling=pointset.ceiling(p);
        if(p.distanceSquaredTo(floor)<p.distanceSquaredTo(ceiling))
            return floor;
        else
            return ceiling;*/
        double min=Double.MAX_VALUE;
        Point2D minpoint=new Point2D(Double.MAX_VALUE,Double.MAX_VALUE);
        for (Point2D i:pointset){
            double current=i.distanceSquaredTo(p);
        if (current<min){
            min=current;
            minpoint=i;
        }
        }
        if( min==Double.MAX_VALUE)return  null;
        return minpoint;

    }           // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args)   {}               // unit testing of the methods (optional)
}
