import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
//checks points are duplicate in collinear..bad design as exception should be thrown on non collinear dups
//datatype mutable?
public class BruteCollinearPoints {
   private LineSegment[] lineSegments;
    private int count=0;
    public BruteCollinearPoints(Point[] points) {

        // finds all line segments containing 4 points

        if(points==null)
        {
            throw  new IllegalArgumentException();
        }
        lineSegments=new LineSegment[(points.length)];
        for(int i=0;i<points.length;i++) {
            if(points[i]==null)
            {
                throw  new IllegalArgumentException();
            }
            for (int j = i+1; j < points.length; j++) {
                if(points[j]==null)
                {
                    throw  new IllegalArgumentException();
                }
                for (int k = j+1; k < points.length; k++) {
                    if(points[k]==null)
                    {
                        throw  new IllegalArgumentException();
                    }
                    if(collinear(points[i],points[j],points[k])) {
                        //collinear checks if points arent repeated and are collinear
                        for (int l = k + 1; l < points.length; l++) {
                            if(points[i]==null)
                            {
                                throw  new IllegalArgumentException();
                            }
                            if(collinear(points[j],points[k],points[l])){
                                Point [] collinearpoints={points[i],points[j],points[k],points[l]};
                                Point min=points[i];
                                Point max=points[i];
                                for(int m=1;m<4;m++){
                                    if(collinearpoints[m].compareTo(min)<0){
                                        min=collinearpoints[m];
                                    }
                                    else if(collinearpoints[m].compareTo(max)>0){
                                        max=collinearpoints[m];
                                    }
                                    else{}
                                }
                                lineSegments[count++]=new LineSegment(min,max);
                            }
                        }
                    }
            }
        }


        }

        LineSegment[] trunc=new LineSegment[count];
         int p=0;
        for(LineSegment i:lineSegments){

            if (i!=null){

                trunc[p++]=i;
            }
        }
        lineSegments=trunc;
    }
    private boolean collinear(Point a ,Point b, Point c){
            if(a.compareTo(b)==0||a.compareTo(c)==0||b.compareTo(c)==0){
                throw new IllegalArgumentException();
            }
            if(a.slopeTo(b)==a.slopeTo(c)){return true;}
            else{return false;}


    }

    public int numberOfSegments() {
        return count;
    }       // the number of line segments

    public LineSegment[] segments() {


        return lineSegments;
    }               // the line segments
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}