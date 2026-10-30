
import java.util.Scanner;

class HeteroSinglyLL {

    // Node class
    /*static class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }*/

    static class Node{
		Object data;
		Node next;
		Node(Object data){
			this.data = data;
			this.next = null;
		}
	}

	static Node head = null;
	static boolean created = false;

	static void create() {
		if (!created) {
			created = true;
			System.out.println("List Created");
		} else {
			System.out.println("List Already Created");
		}
	}

    // Insert at the end
    static void insert(Object data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newNode;
        }

        System.out.println("Inserted");
    }

    // Delete by index
    static void delete(int index) {
        if (head == null) {
            System.out.println("List is Empty");
            return;
        }

        if (index < 0) {
            System.out.println("Invalid Index");
            return;
        }

        if (index == 0) {
            head = head.next;
            System.out.println("Deleted");
            return;
        }

        Node temp = head;

        for (int i = 0; temp != null && i < index - 1; i++) {
            temp = temp.next;
        }

        if (temp == null || temp.next == null) {
            System.out.println("Invalid Index");
        } else {
            temp.next = temp.next.next;
            System.out.println("Deleted");
        }
    }



    // Display the list
    static void traverse() {
        if (head == null) {
            System.out.println("List is Empty");
            return;
        }

        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " --> ");
            temp = temp.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int choice;
        boolean created = false;

        do {
            System.out.println("\n1.Create  2.Insert  3.Delete  4.Traverse  5.Exit");
            System.out.print("Enter choice: ");
            choice = s.nextInt();

            switch (choice) {
                case 1:
                    if (!created) {
                        created = true;
                        System.out.println("List Created");
                    } else {
                        System.out.println("List Already Created");
                    }
                    break;

                case 2:
                    if (!created) {
                        System.out.println("Create the list first");
                        break;
                    }

                    System.out.println(
                        "0.Integer  1.String  2.Boolean  3.Float  4.Double"
                    );
                    System.out.print("Enter data type: ");
                    int type = s.nextInt();

                    System.out.print("Enter value: ");

                    switch (type) {
                        case 0:
                            insert(s.nextInt());
                            break;
                        case 1:
                            insert(s.next());
                            break;
                        case 2:
                            insert(s.nextBoolean());
                            break;
                        case 3:
                            insert(s.nextFloat());
                            break;
                        case 4:
                            insert(s.nextDouble());
                            break;
                        default:
                            System.out.println("Invalid Data Type");
                    }
                    break;

                case 3:
                    if (!created) {
                        System.out.println("Create the list first");
                        break;
                    }

                    System.out.print("Enter index to delete: ");
                    delete(s.nextInt());
                    break;

                case 4:
                    if (!created) {
                        System.out.println("Create the list first");
                    } else {
                        traverse();
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 5);

        s.close();
    }
}
