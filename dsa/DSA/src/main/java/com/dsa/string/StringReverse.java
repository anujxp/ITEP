package com.dsa.string;

import java.util.Scanner;

class StackChar {
    private char[] stack;
    private int top;
    private int size;

    public StackChar(int size) {
        stack = new char[size];
        this.size = size;
        top = -1;
    }

    public void push(char data) {
        if (top == size - 1) {
            System.out.println("Stack overflow");
            return;
        }
        stack[++top] = data;
    }

    public char pop() {
        if (top == -1) {
            System.out.println("Stack UnderFlow");
            return '0';
        }
        return stack[top--];
    }

}
public class StringReverse {
    public static String getReverse(String str){
        String str1 = "";
        StackChar stack = new StackChar(str.length());
        for (int i = 0; i < str.length(); i++) {
           stack.push(str.charAt(i));
        }
        for (int i = str.length()-1; i >=0 ; i--) {
            str1+=stack.pop();
        }
        return str1;
    }
    public static boolean getPalindrome(String str){
        StackChar stack = new StackChar(str.length());
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }
        for (int i = str.length()-1; i >=0 ; i--) {
            if(stack.pop() != str.charAt(str.length()-i-1))
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(getReverse(str));
        System.out.println(getPalindrome(str));

    }
}
