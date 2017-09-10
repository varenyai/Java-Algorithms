/**
 * Created by var on 15/6/17.
 * getSize( ): Returns the number of elements in the list.
 * isEmpty( ): Returns true if the list is empty, and false otherwise.
 * getfirst( ): Returns (but does not remove) the first element in the list.
 * getlast( ): Returns (but does not remove) the last element in the list.
 * addFirst(e): Adds a new element to the front of the list.
 * addLast(e): Adds a new element to the end of the list.
 * removeFirst( ): Removes and returns the first element of the list.
 */
public class slist<T> {
        private class Node<T> {
        private T current;
        private Node<T> next;

        public T getCurrent() {
            return current;

        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getNext() {
            return next;
        }

        public Node(T item, Node<T> next) {
            current=item;
           this.next=next;
        }
    }
    private Node<T> head= null;;
    private int size=0;


    public int getSize() {
        return size;
    }
    public T getFirst(){
        if(IsEmpty())
        {
            return  null;
        }
        else{
            return head.current;
        }
    }
    public boolean IsEmpty(){
        return size==0;
    }
    public T getLast(){
        if(IsEmpty())
        {
            return  null;
        }
        Node<T> cnt=head;
        while (cnt.next!=null){cnt=cnt.next;}
        return cnt.current;
    }
    public void addFirst(T newnode){
        Node<T> newfirst=new Node<>(newnode,null);
        if(head==null)
        {
            head=newfirst;
        }
        else{
            newfirst.next=head;
            head=newfirst;
        }
        size++;
    }
    public void addLast(T newnode){
        Node<T> newlast=new Node<>(newnode,null);
        Node<T> cnt=head;
        while (cnt.next!=null){cnt=cnt.next;}
       cnt.next=newlast;
        size++;
    }
    public T removeFirst(){
        Node<T> cnt=head;

        if(head.next!=null)
        {

            head=head.next;

        }else{head=null;}
        size--;
        return cnt.current;
    }
    public T removeLast(){
        if (head==null)return null;
        Node<T> cnt= new Node<T>(head.current,null);
        if(head.next==null)
        {size--;head=null;
        return cnt.getCurrent();


        }

        Node<T> cnt2= new Node<T>(head.current,null);
        while (cnt.next!=null){
            if(cnt.next.next==null)
            {
                cnt2=cnt.next;break;
            }
            cnt=cnt.next;
        }
        cnt.next=null;
        size--;

        if(size==0)head=null;
        return cnt2.current;
    }


    public String toString() {
        if (head == null) {
            return "null";
        } else {
            StringBuilder stringView = new StringBuilder();

            // using StringBuilder inside loops is very efficient
            for (Node<T> x = head; x != null; x = x.next)
                stringView.append(x.current.toString());

            return stringView.toString();
        }
    }
}
