import java.util.*;

class HeteroDoublyLL_2 {
    public static void main(String[] args) {
        LinkedList<Object> list = null;
        Scanner s = new Scanner(System.in);
        int choice, type;

        do {
            System.out.println("\n0.Create  1.Insert  2.Delete  3.Forward  4.Backward  5.Exit");
            System.out.print("Enter: ");
            choice = s.nextInt();

            switch (choice) {
                case 0:
                    if (list == null) {
                        list = new LinkedList<>();
                        System.out.println("Created");
                    } else System.out.println("Already Created");
                    break;

                case 1:
                    if (list == null) {
                        System.out.println("Create the list first");
                        break;
                    }
                    System.out.println("0.Integer  1.String  2.Boolean  3.Float  4.Double");
                    type = s.nextInt();
                    System.out.print("Enter Value: ");

                    switch (type) {
                        case 0 -> list.add(s.nextInt());
                        case 1 -> list.add(s.next());
                        case 2 -> list.add(s.nextBoolean());
                        case 3 -> list.add(s.nextFloat());
                        case 4 -> list.add(s.nextDouble());
                        default -> System.out.println("Invalid Data Type");
                    }
                    break;

                case 2:
                    if (list == null || list.isEmpty())
                        System.out.println("List is Empty");
                    else {
                        System.out.print("Enter index (0 to " + (list.size() - 1) + "): ");
                        int i = s.nextInt();
                        if (i >= 0 && i < list.size()) {
                            list.remove(i);
                            System.out.println("Removed");
                        } else System.out.println("Invalid Index");
                    }
                    break;

                case 3:
                case 4:
                    if (list == null || list.isEmpty())
                        System.out.println("List is Empty");
                    else {
                        System.out.println(choice == 3 ? "Forward:" : "Backward:");
                        if (choice == 3)
                            for (Object x : list) System.out.print(x + " --> ");
                        else
                            for (int i = list.size() - 1; i >= 0; i--)
                                System.out.print(list.get(i) + " --> ");
                        System.out.println("null");
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }
        } while (choice != 5);

        if (list != null) System.out.println("Size of the List: " + list.size());
        s.close();
    }
}
