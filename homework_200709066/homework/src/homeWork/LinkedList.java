package homeWork;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LinkedList<T extends Comparable<T>> {  //I put one more <T> because of measureAccesses class.
    private Node<T> head;

    public Node<T> createNode(T val) {
        return new Node<T>(val);
    }

    public LinkedList() {
        this.head = null;
    }

    public LinkedList<T> createListFromSource(String fileName) {
        LinkedList<T> list = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                for (String val : values) {
                    T tValue = (T) Integer.valueOf(val.trim());
                    insertIfNotExists(tValue);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertToEnd(T val){
        Node<T> newNode=createNode(val);
        if(head==null){
            head=newNode;
            return;
        }
        Node<T> iterator=head;
        while(iterator.next!=null) {
            iterator = iterator.next;
        }
        iterator.next=newNode;
    }

    public void insertIfNotExists(T val) {
        if (!isComprise(val)) {
            insertToEnd(val);
        }
    }

    private boolean isComprise(T value) {
        Node<T> iterator = head;
        while (iterator != null) {
            if (iterator.value.compareTo(value) == 0) {
                return true; // Value already in the list
            }
            iterator = iterator.next;
        }
        return false; // Value not in the list
    }

    private int search(T value) {
        Node<T> current = head;
        int memoryAccesses = 0;

        while (current != null) {
            memoryAccesses++;
            if (current.value.compareTo(value) == 0) {
                return memoryAccesses; // Value found
            }
            current = current.next;
        }

        return -1; // Value not found
    }

    public int moveToFront(T value) {
        Node<T> iterator = head;
        Node<T> prev = null;
        int memoryAccesses = 0;

        while (iterator != null) {
            memoryAccesses++;
            if (iterator.value.compareTo(value) == 0) {
                if (prev != null) {
                    // Move the found node to the front
                    prev.next = iterator.next;
                    iterator.next = head;
                    head = iterator;
                }
                break; // No need to continue searching after moving the value
            }
            prev = iterator;
            iterator = iterator.next;
        }
        return memoryAccesses;
    }

   public void measureAccesses(String fileName) {
       int totalAccesses = 0;
       int totalValues = 0;

       try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
           String line;
           while ((line = br.readLine()) != null) {
               String[] values = line.split(",");
               for (String stringValue : values) {
                   // We assume that T is a type that extends Comparable
                   T typedValue = (T) Integer.valueOf(stringValue.trim());
                   int memoryAccesses = search(typedValue);

                   if (isComprise(typedValue)) {
                       totalValues++;
                       totalAccesses += memoryAccesses;
                   }
                   totalAccesses += memoryAccesses;
               }
           }
       } catch (IOException | NumberFormatException e) {
           e.printStackTrace();
       }

       int averageAccesses = (int) totalAccesses / totalValues; // I coded with 'int' because I want see the result clear without point and zeros.
       System.out.println("Total Memory Accesses: " + totalAccesses);
       System.out.println("Average Memory Accesses: " + averageAccesses);
   }

    public void display() {
        Node<T> iterator = head;
        while (iterator != null) {
            System.out.print(iterator + " ");
            iterator = iterator.next;
        }
        System.out.println();
    }
}