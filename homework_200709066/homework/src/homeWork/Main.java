package homeWork;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList1 = new LinkedList<>();
        linkedList1.createListFromSource("src/homeWork/Source.txt");
        //linkedList1.display();
        //linkedList1.measureAccesses("src/homeWork/Search.txt");
        linkedList1.moveToFront(13);
        linkedList1.display();


        long startTime1 = System.currentTimeMillis();
        linkedList1.moveToFront(5);
        long endTime1 = System.currentTimeMillis();
        long elapsedTime1 = endTime1 - startTime1;

        // we reset the linked list for comparing
        linkedList1 = new LinkedList<>();
        linkedList1.createListFromSource("src/homeWork/Source.txt");


        long startTime2 = System.currentTimeMillis();
        linkedList1.measureAccesses("src/homeWork/Search.txt");
        long endTime2 = System.currentTimeMillis();
        long elapsedTime2 = endTime2 - startTime2;

        System.out.println("Time for give value moves to front: " + elapsedTime1 + " milliseconds");
        System.out.println("Time for calculating MemoryAccesses: " + elapsedTime2 + " milliseconds");


    }
}