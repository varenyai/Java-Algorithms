import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by var on 21/6/17.
 */
public class Deque<Item> implements Iterable<Item> {
    private class Node<Item>{

        private Item Element=null;
        private Node<Item> next=null;
        private Node<Item> prev=null;


    }

    private  Node<Item> head;
    private Node<Item> tail;
    private int size=0;


    public Deque()  {
        head=null;
        tail=head;
    }                         // construct an empty deque



    public boolean isEmpty() {
        return size==0;
    }                // is the deque empty?


    public int size()   {return size;}                     // return the number of items on the deque


    public void addFirst(Item item) {
        if(item==null) {
            throw new IllegalArgumentException();
        }

        Node<Item> newNode= new Node();
        newNode.Element=item;
        if(size!=0){


            newNode.next = head;
            head.prev = newNode;
        }
        head=newNode;
        if (size==0)
        {
            tail=head;
        }
            size++;


    }         // add the item to the front


    public void addLast(Item item) {
        if(item==null) {
            throw new IllegalArgumentException();
        }
        Node<Item> newNode= new Node();
        newNode.Element=item;
        if(size==0){
            tail=newNode;
            head=tail;
        }else{
            tail.next=newNode;
            newNode.prev=tail;
            tail=newNode;
        }
        size++;



    }          // add the item to the end

    public Item removeFirst()   {

        if (size==0) {
            throw new NoSuchElementException();
        }
        Item removed=head.Element;
        head.Element=null;
       head= head.next;
       if(head!=null&&head.prev!=null)
       head.prev=null;
       if(size==1)
           tail=null;
       size--;
       return removed;
    }             // remove and return the item from the front


    public Item removeLast()                 // remove and return the item from the end

    {
        if (size==0) {
            throw new NoSuchElementException();
        }
        Item removed=tail.Element;
        if(size!=1){


        tail=tail.prev;
       tail.next=null;}else{head=null;tail=null;}
        size--;
    return removed;

    }



    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = (Item) current.Element;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {

        Deque<Integer> deque =new Deque();
        deque.addLast(0);
        deque.addLast(1);
        System.out.println( deque.removeLast());
        deque.addLast(3);
        for(int i:deque){System.out.println(i);}
        System.out.println(deque.removeLast());
        System.out.println( deque.removeLast());

    }  // unit testing (optional)

}