import java.util.LinkedList;
import java.util.Scanner;
class HeteroSinglyLL{
    public static void main(String[] args) {
        LinkedList<Object> sl = null;
        Scanner s = new Scanner(System.in);
        String c;
        int o,d;
        do{
			System.out.println("Options");
			System.out.println("0. Create");
			System.out.println("1. Insert");
			System.out.println("2. Delete");
			System.out.println("3. Traverse");
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
					System.out.println("Here is the current List : ");
					for(int ll = 0;ll<sl.size();ll++){
							System.out.print(sl.get(ll)+"-->");
					}
					System.out.println("null");
					break;
			}
		System.out.print("Continue or Exit : ");
		} while(!(c=s.next()).equals("exit"));

        System.out.println("Size of the List ended : "+sl.size());

    }
}