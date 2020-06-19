package JSI_SA;

import java.util.Scanner;

/**
 *
 * Author : @binc876
 * Repo   : byeolbin_study
 */
public class Stack {
    StackNode root;
    static class StackNode {

        int data;
        StackNode next;

        StackNode(int data) {
            this.data = data;
        }
    }

    public boolean isEmpty() {
        if (root == null) {
            return true;
        } else {
            return false;
        }
    }

    public void push(int data) {
        StackNode newNode = new StackNode(data);

        if (root == null) {
            root = newNode;
        } else {
            StackNode temp = root;
            root = newNode;
            newNode.next = temp;
        }
//        System.out.print(data + ", ");
    }

    public int pop() {
        int popped = Integer.MIN_VALUE;
        if (root == null) {
            System.out.println("Stack is Empty");
        } else {
            popped = root.data;
            root = root.next;
        }
        return popped;
    }

    public int peek() {
        if (root == null) {
            System.out.println("Stack is empty");
            return Integer.MIN_VALUE;
        } else {
            return root.data;
        }
    }
    
    static int hitungPostfix(String value) {
        Stack stack = new Stack();
        for (int i = 0; i < value.length(); i++) {
            char trimValue = value.charAt(i);

            if (trimValue == ',') {
                continue;

            } else if (Character.isDigit(trimValue)) {
                int n = 0;
                while (Character.isDigit(trimValue)) {
                    n = n * 10 + (int) (trimValue - '0');
                    i++;
                    trimValue = value.charAt(i);
                }
                i--;

                stack.push(n);
                
            } else {
                int val1 = stack.pop();
                int val2 = stack.pop();

                if(trimValue == '*'){
                    stack.push(val2 * val1);
                }
                if(trimValue == '/'){
                    stack.push(val2 / val1);
                }
                if(trimValue == '^'){
                    int pangkat = (int) Math.pow(val2, val1);
                    stack.push(pangkat);
                }
                if(trimValue == '+'){
                    stack.push(val2 + val1);
                }
                if(trimValue == '-'){
                    stack.push(val2 - val1);
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String value = in.nextLine();
        System.out.println(hitungPostfix(value));
    }
}
