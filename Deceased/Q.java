class Queue<T>{

	T[] q;
	int front = 0;
	int rear = -1;

	Queue(int size){
		q = (T[]) new Object[size];
	}

	void enqueue(T data){
		if(rear == q.length-1){
			System.out.println("Q Full");
		}
		else{
			q[++rear] = data;
		}
	}

	T dequeue(){
		if(front > rear){
			System.out.println("Q Empty");
			return null;
		}
		else{
			T item = q[front];
			q[front++] = null;
			return item;
		}
	}

	void display(){
		if(front > rear){
			System.out.println("Q Empty");
			return;
		}

		for(int i=front;i<=rear;i++){
			System.out.println(q[i]);
		}

	}

}

class Q{
	public static void main(String[] args){
		Queue<Integer> q = new Queue<>(5);
		q.enqueue(10);
		        q.enqueue(20);
		        q.enqueue(30);

		        System.out.println("Queue:");
		        q.display();

		        System.out.println("Deleted: " + q.dequeue());

		        System.out.println("Queue after deletion:");
        q.display();
	}
}