import java.util.Arrays;

/**
 * Created by var on 15/7/17.
 */
public class MergeSort <Key extends Comparable> {

    public static void sort(Comparable a[]){
        Comparable [] aux=new Comparable[a.length];
        int length=a.length;
        sort(a,0,length/2-1);
        sort(a,length/2,length-1);
        merge(a,aux);
    }
    private static void merge(Comparable a[],Comparable[] aux){
        //given two sorted arrays merge them to a sorted array
        int length=a.length;

       int  current=0,mid=a.length/2-1;
        for(int i=0;i<a.length;i++){
            aux[i]= a[i];

        }
       for(int i=0;i<a.length;i++) {
           //System.out.println("i,mid,current are:"+i+mid+current);
           if (mid>=a.length){a[i]=aux[current++];
               System.out.println("In if i,mid,current are:"+i+mid+current);
           }
           else if(current>=a.length/2 -1){a[i]=aux[mid++ ];
               System.out.println("In elsif if i,mid,current are:"+i+mid+current);}
           else if (aux[current].compareTo(aux[mid +1]) <= 0) {
               a[i]=aux[current++];
               System.out.println("In elseif i,mid,current are:"+i+mid+current);
           }
           else{
               a[i]=aux[mid++ +1 ];
               System.out.println("In else i,mid,current are:"+i+mid+current);
           }
       }


    }

    private static void sort(Comparable a[],int start,int end )
    {


    }
    public static  void main(String args[]){
        Integer [] ints={5,8,4,7};
       // Integer [] aux=new Integer[ints.length];
        MergeSort.sort(ints);
       // Arrays.sort(ints);
        /*String []strings={"int","mill","ape","zap"};
        MergeSort.merge(strings);
        for(String i:strings){
            System.out.println(i);
        }*/
        for (Integer i :ints){
            System.out.println(i);
        }
       // System.out.println(strings[0].compareTo(strings[2]));
    }
}
