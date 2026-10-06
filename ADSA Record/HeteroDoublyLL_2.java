import java.util.LinkedList;
import java.util.Scanner;

class HeteroDoublyLL_2 {
	public static void main(String[] args) {

		LinkedList<Object> list = null;
		Scanner s = new Scanner(System.in);
		int choice, type;

		do {
			System.out.println("\nOptions");
			System.out.println("0. Create");
			System.out.println("1. Insert");
			System.out.println("2. Delete");
			System.out.println("3. Traverse Forward");
			System.out.println("4. Traverse Backward");
			System.out.println("5. Exit");
			System.out.print("Enter: ");
			choice = s.nextInt();

			switch (choice) {

				case 0:
					if (list == null) {
						list = new LinkedList<>();
						System.out.println("Created");
					} else {
						System.out.println("Already Created");
					}
					break;

				case 1:
					if (list == null) {
						System.out.println("Create the list first");
						break;
					}

					System.out.println("Data Types");
					System.out.println("0. Integer");
					System.out.println("1. String");
					System.out.println("2. Boolean");
					System.out.println("3. Float");
					System.out.println("4. Double");
					System.out.print("Enter: ");
					type = s.nextInt();

					System.out.print("Enter Value: ");

					switch (type) {
						case 0:
							list.add(s.nextInt());
							break;

						case 1:
							s.nextLine();
							list.add(s.nextLine());
							break;

						case 2:
							list.add(s.nextBoolean());
							break;

						case 3:
							list.add(s.nextFloat());
							break;

						case 4:
							list.add(s.nextDouble());
							break;

						default:
							System.out.println("Invalid Data Type");
					}
					break;

				case 2:
					if (list == null || list.isEmpty()) {
						System.out.println("List is Empty");
						break;
					}

					System.out.println("Index: 0 to " + (list.size() - 1));
					System.out.print("Enter index to delete: ");
					int index = s.nextInt();

					if (index >= 0 && index < list.size()) {
						list.remove(index);
						System.out.println("Removed");
					} else {
						System.out.println("Invalid Index");
					}
					break;

				case 3:
					if (list == null || list.isEmpty()) {
						System.out.println("List is Empty");
						break;
					}

					System.out.println("List (Forward):");

					for (Object value : list)
						System.out.print(value + " --> ");

					System.out.println("null");
					break;

				case 4:
					if (list == null || list.isEmpty()) {
						System.out.println("List is Empty");
						break;
					}

					System.out.println("List (Backward):");

					for (int i = list.size() - 1; i >= 0; i--)
						System.out.print(list.get(i) + " --> ");

					System.out.println("null");
					break;

				case 5:
					System.out.println("Exiting...");
					break;

				default:
					System.out.println("Invalid Choice");
			}

		} while (choice != 5);

		if (list != null)
			System.out.println("Size of the List: " + list.size());

		s.close();
	}
}
