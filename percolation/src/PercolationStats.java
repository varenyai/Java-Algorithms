import edu.princeton.cs.algs4.*;

public class PercolationStats {

   private int trials,gridsize;
   private double [] countsfraction;
   private Percolation perc[];
   public PercolationStats(int n, int trials) {

       this.trials=trials;
       gridsize=n;
       if (gridsize<=0||trials<=0)
       {throw new  IllegalArgumentException();}
       countsfraction=new double[trials];
       perc=new Percolation[trials];
       for(int i=0;i<trials;i++) {
           perc[i] = new Percolation(n);
       }

   }   // perform trials independent experiments on an n-by-n grid
   public double mean()   {
       if (gridsize<=0||trials<=0)
           throw new  IllegalArgumentException();
       return StdStats.mean(countsfraction);
   }                        // sample mean of percolation threshold
   public double stddev()   {
      if (gridsize<=0||trials<=0)throw new  IllegalArgumentException();
      if (trials==1)return Double.NaN;

      return StdStats.stddev(countsfraction);
   }                      // sample standard deviation of percolation threshold
   public double confidenceLo()  {
       if (gridsize<=0||trials<=0)throw new  IllegalArgumentException();

       return mean()-(1.96*Math.sqrt(stddev())/Math.sqrt(trials));
   }                 // low  endpoint of 95% confidence interval
   public double confidenceHi() {
       if (gridsize<=0||trials<=0)throw new  IllegalArgumentException();

       return mean()+(1.96*Math.sqrt(stddev())/Math.sqrt(trials));
   }                  // high endpoint of 95% confidence interval

   public static void main(String[] args)  {
      // StdIn in = new StdIn();
       int n=Integer.parseInt(args[0]);
       int T=Integer.parseInt(args[1]);

      PercolationStats Perc=new PercolationStats(n,T);
       for(int i=0;i<T;i++){


        while(!(Perc.perc[i].percolates())){
            int n1=StdRandom.uniform(1,n+1);
            int n2=StdRandom.uniform(1,n+1);
           Perc.perc[i].open(n1,n2);

        }
        Perc.countsfraction[i]=Perc.perc[i].numberOfOpenSites()/n;


       }
      StdOut.println("mean="+Perc.mean());
       StdOut.println("stddev="+Perc.stddev());
       StdOut.println("95% confidence interval = ["+Perc.confidenceLo()+","+Perc.confidenceHi()+"]");

   }      // test client (described below)
}
