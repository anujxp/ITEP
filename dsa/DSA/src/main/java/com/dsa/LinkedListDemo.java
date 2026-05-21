package com.dsa;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head = null;


    public void add(int data) {
        Node newNode = new Node(data);
        Node current = head;
        if (head == null)
            head = newNode;
        else {
            while (current.next != null)
                current = current.next;
            current.next = newNode;
        }
    }

    public void insertAtBeg(int data){
        Node temp = new Node(data);
        temp.next = head;
        head = temp;
    }
    public void insertEnd(int data){
        Node temp = new Node(data);
        if(head == null) head = temp;
        else{
            Node last = head;
            while(last.next != null){
                last = last.next;
            }
            last.next = temp;
        }
        System.out.println("Nde inserted at the End....");
    }

    public void insertPos(int data , int pos){
        Node temp = new Node(data), last;
        last = head;
        int count = 0;
        while (last != null){
            count++;
            last = last.next;
        }
        if(pos <= 0 || pos>count+1){
            System.out.println("Position is invalid");
            return;
        }
        if(pos == 1) {
            temp.next = head;
            head = temp;
        }
        else{
            last = head;
            for(int i = 2;i< pos; i++){
                last = last.next;
            }
            temp.next = last.next;
            last.next = temp;

        }
      }

    public void deleteFirst(){
        if(head == null)
        {

            System.out.println("Lisked list is Empty");
            return;
        }
        head = head.next;
    }

    public void deleteLast(){
        if(head == null)
        {
            System.out.println("List is Empty");
            return;
        }
        if(head.next == null){
            head = null;
            return;
        }
        Node p = head;
        while(p.next.next != null){
            p =p.next;
        }
        p.next = null;

    }
    

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void deleteByValue(int data){
        if(head == null) return;
        if(head.data == data){
            head= head.next;
            return;
        }
        Node current = head;
        while(current.next!= null && current.next.data != data){
            current = current.next;
        }
        if(current.next != null)
            current.next  = current.next.next;
    }
}

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList nums = new LinkedList();
        nums.add(4);
        nums.add(5);
        nums.add(5);
        nums.add(6);
        nums.add(3);
        
        nums.display();
        nums.deleteByValue(6);
        nums.display();

    }
}
