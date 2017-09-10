public class Test {

    // Created to basically test SinglyLinkedList
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList();
        slist<Integer> list1 = new slist();
        slist<String> list2 = new slist();
        list.addFirst(0);
        list.addFirst(1);
        list.addFirst(1);
        list1.addFirst(2);
        list1.addFirst(0);
       // list1.addFirst(1);
        //list1.addFirst(2);
        System.out.println(list);

        System.out.println(list1);

        list2.addFirst("First");
        list2.addFirst("Last");
        System.out.println(list2);



        Integer a=list.removeFirst();

        System.out.println(list+"int is "+a);


        // Removes the item in the specified index



        Integer b=list1.removeFirst();

        System.out.println(list1+"intb is "+b);
        Integer d=list1.removeFirst();

        System.out.println(list1+"int dis "+d);
       // System.out.println(list1.getSize());
        Integer c=list1.removeLast();
        System.out.println(list1.getSize());
        System.out.println(list1+"int c is "+c);

    }
}