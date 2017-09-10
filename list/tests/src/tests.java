public class Test {

    // Created to basically test SinglyLinkedList
    public static void main(String[] args) {
        SinglyLinkedList<int> list = new SinglyLinkedList();
        slist<int> list1 = new slist();
        list.addFirst(0);
        list.addFirst(1);
        list1.addFirst(2);
        list1.addFirst(0);
        list1.addFirst(1);
        list1.addFirst(2);
        System.out.println(list);

        System.out.println(list1);

        list.addFirst("First");
        list.addLast("Last");
        System.out.println(list);



        list.removeFirst();

        System.out.println(list);


        // Removes the item in the specified index



        list1.remove();
        System.out.println(list);


    }
}