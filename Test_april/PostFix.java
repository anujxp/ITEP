class Stack {
	
    private int[] stack;
    private int top;
    private int capacity;

    public Stack(int size) {
        this.capacity = size;
        this.stack = new int[capacity];
        this.top = -1; 
    }
    public void push(int value) {
        if (top == capacity - 1) {
            System.out.println("Stack Overflow!......");
            return;
        }
        stack[++top] = value;
    }
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack Underflow!........");
        }
        return stack[top--];
    }
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty!......");
        }
        return stack[top];
    }
    public boolean isEmpty() {
        return top == -1;
    }
}


public class PostFix{
	public static int evaluate(String expression){
		Stack stack = new Stack(expression.length());
		String[] tokens = expression.split(" ");
		for(String token : tokens){
			if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
				int val2 = stack.pop();
				int val1 = stack.pop();
				
				switch(token){
					case "+" : 
						stack.push(val1+val2);
						break;
					case "-" : 
						stack.push(val1-val2);
						break;
					case "*" : 
						stack.push(val1*val2);
						break;
					case "/" : 
						stack.push(val1/val2);
						break;
				}
			}
			else stack.push(Integer.parseInt(token));
		}
		return stack.pop();
	}
	public static void main(String[] args){
		String s = "2 3 * 5 4 * + 9 -"; 
		System.out.println(evaluate(s));
	}
}