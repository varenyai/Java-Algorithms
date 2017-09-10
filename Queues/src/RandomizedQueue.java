import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int size;
    private  int count;


    public RandomizedQueue()  {
        size=1;
        count=0;
        queue=(Item[])new  Object[1];

    }               // construct an empty randomized queue
    public boolean isEmpty() {return count==0;}                // is the queue empty?
    public int size()         {return count;}               // return the number of items on the queue
    public void enqueue(Item item)  {

        if (item==null){
            throw new IllegalArgumentException();
        }
        queue[count]=item;
        count++;
        incrementsize();

    }         // add the item

    private void incrementsize() {
        if(count<size-1)
            return;
        else{

            Item[] newqueue = (Item[]) new Object[size*2];

            for (int i=0;i<count;i++){

                    newqueue[i]=queue[i];
                }

            size=size*2;
            queue=newqueue;
        }

    }

    public Item dequeue(){

        int random= StdRandom.uniform(count);

        if (count==0){
            throw new NoSuchElementException();
        }

        Item p=queue[random];
        queue[random]=queue[count-1];
        queue[count--]=null;
        reducesize();
    return p;

    }                    // remove and return a random item

    private void reducesize() {
        if (count > size / 4)
            return;
        else {

            Item[] newqueue = (Item[]) new Object[size / 2];

            for (int i = 0; i < count; i++) {

                newqueue[i] = queue[i];
            }

            size = size / 2;


        }
    }


    public Item sample() {
        if (count==0){
            throw new NoSuchElementException();
        }
        int random= StdRandom.uniform(count);


    return queue[random];
    }                    // return (but do not remove) a random item

    public Iterator<Item> iterator() {return new ArrayIterator();}
    // return an independent iterator over items in random order

   private  class ArrayIterator implements Iterator<Item> {
       int current=0;
       private void shuffle(){
           if(current==0)
           StdRandom.shuffle(queue);

           }
       public boolean hasNext() {
           return current<count;
       }


       public Item next() {
           shuffle();
           if (!hasNext()) throw new NoSuchElementException();


           return queue[current++];
       }


       public void remove() {
           throw new UnsupportedOperationException();
       }
   }


    public static void main(String[] args){}   // unit testing (optional)
}