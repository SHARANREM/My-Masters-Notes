import java.util.*;

class HeteroSinglyLL_1 {
    static LinkedList<Object> list;

    static void create() {
        if (list == null) {
            list = new LinkedList<>();
            System.out.println("List Created");
        } else System.out.println("List Already Created");
    }

    static void insert(Object data) {
        if (list == null) {
            System.out.println("Create the list first");
            return;
        }
        list.add(data);
        System.out.println("Inserted");
    }

    static void delete(int index) {
        if (list == null || list.isEmpty()) {
            System.out.println("List is Empty");
        } else if (index >= 0 && index < list.size()) {
            list.remove(index);
            System.out.println("Deleted");
        } else System.out.println("Invalid Index");
    }

    static void traverse() {
        if (list == null || list.isEmpty()) {
            System.out.println("List is Empty");
            return;
        }
        for (Object x : list) System.out.print(x + " --> ");
        System.out.println("null");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1.Create  2.Insert  3.Delete  4.Traverse  5.Exit");
            System.out.print("Enter choice: ");
            choice = s.nextInt();

            switch (choice) {
                case 1 -> create();

                case 2 -> {
                    System.out.println("0.Integer  1.String  2.Boolean  3.Float  4.Double");
                    System.out.print("Enter data type: ");
                    int type = s.nextInt();
                    System.out.print("Enter value: ");

                    switch (type) {
                        case 0 -> insert(s.nextInt());
                        case 1 -> insert(s.next());
                        case 2 -> insert(s.nextBoolean());
                        case 3 -> insert(s.nextFloat());
                        case 4 -> insert(s.nextDouble());
                        default -> System.out.println("Invalid Data Type");
                    }
                }

                case 3 -> {
                    System.out.print("Enter index to delete: ");
                    delete(s.nextInt());
                }

                case 4 -> traverse();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid Choice");
            }
        } while (choice != 5);

        s.close();
    }
}
