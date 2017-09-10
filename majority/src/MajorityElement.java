import java.util.*;
import java.io.*;

public class MajorityElement {
    private static long getMajorityElement(long[] a, int left, int right) {
     Arrays.sort(a);
        for(int i=0;i<right;i++) {
            //System.out.println(a[i]+"\t");
        }
     int count=1;
     for(int i=1;i<right;i++){
         while(i<right &&a[i-1]==a[i]){

             count++;i++;
             //System.out.println(a[i-1]+" "+count+" "+i);
         }if(count>(right-left)/2)
         return 1;
         else
         {
             count=1;
         }

     }return -1;
}





    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextLong();
        }
        if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

}
