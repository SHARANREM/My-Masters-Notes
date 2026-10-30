class S<T>{
	T[] stack;
	int top = -1;

	S(int size){
		stack = (T[]) new Object[size];
	}

	void push(T data){
		if(top == stack.length - 1){
			System.out.println("Over Flow");
		}
		else{
			stack[++top] = data;
		}
	}

	T pop(){
		if(top == -1){
			System.out.println("Stack Underflow");
			return null;
		}
		return stack[top--];
	}

	T peek(){
		if(top == -1){
				return null;
		}
		return stack[top];
	}

	void display(){
		if(top == -1){
					System.out.println("Stack Underflow");
					return;
		}
		for(int i = top;i>=0;i--){
			System.out.print(stack[i]+" ");
		}
		System.out.println();
	}

}

class Stack{
	public static void main(String[] args){
		S<Integer> s = new S<>(5);
		s.push(10);
		        s.push(20);
		        s.push(30);

		        System.out.println("Stack:");
		        s.display();

		        System.out.println("Top element: " + s.peek());

		        System.out.println("Popped element: " + s.pop());

		        System.out.println("Stack after pop:");
        s.display();
	}
}