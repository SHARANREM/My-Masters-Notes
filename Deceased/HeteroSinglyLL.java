
class Node{
	Object data;
	Node next;
	Node(Object data){
		this.data = data;
	}
}

class SingleLL{

	Node head;
	int size = 0;
	boolean created=false;
	void create(){
		if(!created){
			System.out.println("Created");
			created = true;
		}
		else{
			System.out.println("Already Created");
		}
	}

	void insert(Object data){
		Node newNode = new Node(data);
		if(!created){System.out.println("Create First!!");return;}
		if(head == null){
			head = newNode;
			return;
		}

		Node temp = head;

		while(temp.next!=null){
			temp = temp.next;
		}

		temp.next = newNode;

		System.out.println("Inserted");
		size++;
	}

	void delete(int index){
		if(!created){System.out.println("Create First!!");return;}
		if(isEmpty()){
			System.out.println("The list is Empty!");
			return;
		}

		if(index<0 || index>=size){
			System.out.println("The Index is not within the range");
			return;
		}

		if(index == 0){
			head = head.next;
		}
		else{
			Node temp = head;
			for(int i = 0; i<index-1 ; i++){
				temp = temp.next;
			}
			temp.next = temp.next.next;
		}
		size--;
		System.out.println("Deleted");
	}

	void traverse(){
		if(!created){System.out.println("Create First!!");return;}
		if(isEmpty()){
			System.out.println("The list is empty");
			return;
		}
		Node temp = head;
		while(temp != null){
			System.out.print(temp.data + " --> ");
			temp = temp.next;
		}
		System.out.println("null");
	}

	boolean	 isEmpty(){
		if(!created){System.out.println("Create First!!");return false;}
		if(size<0){
			return true;
		}return false;
	}
}

class SLLS{
	public static void main(String[] args){
		SingleLL list = new SingleLL();
		list.insert(10);
		list.traverse();
		list.delete(2);
        list.create();

        list.insert(10);
        list.insert("Hi");
        list.insert(30);
        list.insert(40);

        list.traverse();

        list.delete(2);
		list.delete(2);

        list.traverse();

        System.out.println("Size: " + list.size);
	}
}
