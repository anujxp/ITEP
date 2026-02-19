package com.dsa;

class Stack{
    private int[]arr = new int[5];
    private int top;
    private int size;
public Stack(){
    size = arr.length;
    top = -1;
}

    public void push(int data) {
    if(top<size)
    arr[++top] = data;
    else
        System.out.println("Stack overflow");
    }
    public int pop(){
    return arr[top--];
    }
    public int peek() {
    return arr[top];
    }
}


public class StackDemo {
    public static void main(String[] args){
        Stack nums = new Stack();
        nums.push(1);
        nums.push(2);
        nums.push(3);
        System.out.println(nums.peek());
        nums.pop();
        System.out.println(nums.peek());
//        System.out.println(nums.sear
    }
}
