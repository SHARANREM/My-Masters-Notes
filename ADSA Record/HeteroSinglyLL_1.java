import java.util.LinkedList;
import java.util.Scanner;

class HeteroSinglyLL_1 {

	static LinkedList<Object> list = null;

	static void create() {
		if (list == null) {
			list = new LinkedList<>();
			System.out.println("List Created");
		} else {
			System.out.println("List Already Created");
		}
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
			return;
		}

		if (index >= 0 && index < list.size()) {
			list.remove(index);
			System.out.println("Deleted");
		} else {
			System.out.println("Invalid Index");
		}
	}

	static void traverse() {
		if (list == null || list.isEmpty()) {
			System.out.println("List is Empty");
			return;
		}

		for (Object data : list)
			System.out.print(data + " --> ");

		System.out.println("null");
	}

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int choice;

		do {
			System.out.println("\n1. Create");
			System.out.println("2. Insert");
			System.out.println("3. Delete");
			System.out.println("4. Traverse");
			System.out.println("5. Exit");
			System.out.print("Enter choice: ");
			choice = s.nextInt();

			switch (choice) {

				case 1:
					create();
					break;

				case 2:
					System.out.println("0. Integer");
					System.out.println("1. String");
					System.out.println("2. Boolean");
					System.out.println("3. Float");
					System.out.println("4. Double");
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
					System.out.print("Enter index to delete: ");
					delete(s.nextInt());
					break;

				case 4:
					traverse();
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
