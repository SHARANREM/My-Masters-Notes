
import java.util.Scanner;

class HeteroDoublyLL {

    // Node class
    static class Node {
        Object data;
        Node prev, next;

        Node(Object data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    static Node head = null;
    static Node tail = null;
    static boolean created = false;
    static int size = 0;

    // Create the list
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
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
        System.out.println("Inserted");
    }

    // Delete by index
    static void delete(int index) {
        if (head == null) {
            System.out.println("List is Empty");
            return;
        }

        if (index < 0 || index >= size) {
            System.out.println("Invalid Index");
            return;
        }

        Node temp = head;

        // Move to the required node
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        // Update the previous node
        if (temp.prev != null) {
            temp.prev.next = temp.next;
        } else {
            head = temp.next;
        }

        // Update the next node
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        } else {
            tail = temp.prev;
        }

        size--;
        System.out.println("Deleted");
    }

    // Forward traversal
    static void forward() {
        if (head == null) {
            System.out.println("List is Empty");
            return;
        }

        Node temp = head;
        System.out.print("Forward: ");

        while (temp != null) {
            System.out.print(temp.data + " --> ");
            temp = temp.next;
        }

        System.out.println("null");
    }

    // Backward traversal
    static void backward() {
        if (tail == null) {
            System.out.println("List is Empty");
            return;
        }

        Node temp = tail;
        System.out.print("Backward: ");

        while (temp != null) {
            System.out.print(temp.data + " --> ");
            temp = temp.prev;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int choice, type;

        do {
            System.out.println(
                "\n0.Create  1.Insert  2.Delete  " +
                "3.Forward  4.Backward  5.Exit"
            );

            System.out.print("Enter: ");
            choice = s.nextInt();

            switch (choice) {
                case 0:
                    create();
                    break;

                case 1:
                    if (!created) {
                        System.out.println("Create the list first");
                        break;
                    }

                    System.out.println(
                        "0.Integer  1.String  2.Boolean  " +
                        "3.Float  4.Double"
                    );

                    System.out.print("Enter Data Type: ");
                    type = s.nextInt();

                    System.out.print("Enter Value: ");

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

                case 2:
                    if (!created) {
                        System.out.println("Create the list first");
                        break;
                    }

                    if (head == null) {
                        System.out.println("List is Empty");
                    } else {
                        System.out.print(
                            "Enter index (0 to " + (size - 1) + "): "
                        );
                        delete(s.nextInt());
                    }
                    break;

                case 3:
                    if (!created) {
                        System.out.println("Create the list first");
                    } else {
                        forward();
                    }
                    break;

                case 4:
                    if (!created) {
                        System.out.println("Create the list first");
                    } else {
                        backward();
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 5);

        System.out.println("Size of the List: " + size);
        s.close();
    }
}
