import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private LineSegment[] lineSegments;
    private int count=0;
    private Point[] min;
    private Point[] max;

    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        lineSegments = new LineSegment[(points.length)];
       min=new Point[points.length];
       max=new Point[points.length];
        Point []copy=new Point[points.length];
        //sort so as to have  lowest y in points[0] and order slopes on that base
        if (points == null) {
            throw new IllegalArgumentException();
        }
    int l=0;
        for (Point i : points) {
            if (i == null) {
                throw new IllegalArgumentException();
            }
            else copy[l++]=i;
        }
        for (int a = 0; a < points.length; a++) {

            Arrays.sort(points, copy[a].slopeOrder());
/*
           System.out.println(points[0].toString());
            System.out.println();
            for (Point i : points) {
                System.out.println(i.toString() + "/t" + points[0].slopeTo(i));

            }
            System.out.println();
*/

            Point base = points[0];
            int i = 1, j = 2;
            while (j < points.length) {
                boolean flag = false;

                int collinearcount = 2;
                while (points[j].slopeTo(base) == points[i].slopeTo(base)) {

                    if (points[i].compareTo(points[j]) == 0) {
                        throw new IllegalArgumentException();
                    }


                    collinearcount++;

                    flag = true;
                    i++;//just to compare for dups
                    j++;//needed
                    if(points.length<=j){break;}



                }

                if (collinearcount > 3) {

                    int p=1;

                    Point min=points[0];
                    Point max=points[0];

                    for(int m=i-collinearcount+2;m<=i;m++)
                    {

                        if(points[m].compareTo(min)<0){
                            min=points[m];
                        }
                        else if(points[m].compareTo(max)>0){
                            max=points[m];
                        }
                        else{}


                    }


                    if(count==0||notexists(min,max)) {
                        lineSegments[count] = new LineSegment(min, max);
                        this.min[count] = min;
                        this.max[count] = max;
                        count++;
                    }
                }
                if (j < points.length) {


                    if (!flag) {
                        i++;
                        j++;
                    }
                }

            }


        }
        LineSegment[] trunc = new LineSegment[count];
        int p = 0;
        for (LineSegment k : lineSegments) {

            if (k != null) {

                trunc[p++] = k;
            }
        }
        lineSegments = trunc;

    }
    private boolean notexists(Point min,Point max){
        boolean flag1=false,flag2=false;
        for(Point i:this.min){
            if(i==null){break;}
            if (i.compareTo(min)==0){
                flag1=true;break;
            }
        }
        for(Point i:this.max){
            if(i==null){break;}
            if (i.compareTo(max)==0){
                flag2=true;break;
            }
        }
        if(flag1&&flag2)return false;
        else return true;
    }



    public           int numberOfSegments()  {return  count;}      // the number of line segments
    public LineSegment[] segments()  {return lineSegments;}              // the line segments


    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        StdOut.println(collinear.numberOfSegments());
    }

}
