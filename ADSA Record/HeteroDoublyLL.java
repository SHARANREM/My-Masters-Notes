import java.util.LinkedList;
import java.util.Scanner;
class HeteroDoublyLL {
    public static void main(String[] args) {
        LinkedList<Object> sl = null;
        Scanner s = new Scanner(System.in);
        int c = 0;
        int o,d;
        do{
			System.out.println("Options");
			System.out.println("0. Create");
			System.out.println("1. Insert");
			System.out.println("2. Delete");
			System.out.println("3. Traverse Forward");
			System.out.println("4. Traverse Backwards");
			System.out.println("5. Exit");
			System.out.print("Enter :");
			o = s.nextInt();
			switch(o){
				case 0:
					if(sl == null){
						sl = new LinkedList<>();
						System.out.println("Created");
					}
					else{
						System.out.println("Already Created");

					}
					break;
				case 1:
					System.out.println("Dtypes: ");
					System.out.println("0. int");
					System.out.println("1. string");
					System.out.println("2. boolean");
					System.out.println("3. float");
					System.out.println("4. double");
					System.out.print("Enter :");
					d = s.nextInt();
					System.out.print("Enter Value:");
					switch(d){
						case 0:
							sl.add(s.nextInt());
							break;
						case 1:
							s.nextLine();
							sl.add(s.nextLine());
							break;
						case 2:
							sl.add(s.nextBoolean());
							break;
						case 3:
							sl.add(s.nextFloat());
							break;
						case 4:
							sl.add(s.nextDouble());
							break;

					}
					System.out.println("Operation Done");
					break;

				case 2:
					System.out.println("Currently we have from 0 to "+(sl.size()-1));
					System.out.print("Enter the index to delete : ");

					sl.remove(s.nextInt());
					System.out.println("Removed ");
					break;
				case 3:
					System.out.println("Here is the current List(Forward) : ");
					for(int ll = 0;ll<sl.size();ll++){
							System.out.print(sl.get(ll)+"-->");
					}
					System.out.println("null");
					break;
				case 4:
					System.out.println("Here is the current List(Backward) : ");
					for(int ll = sl.size()-1; ll>=0 ; ll--){
						System.out.print(sl.get(ll)+"-->");
					}
					System.out.println("null");
					break;
				case 5:
					c=1;
					break;
			}

		} while(c == 0);

        System.out.println("Size of the List ended : "+sl.size());

    }
}