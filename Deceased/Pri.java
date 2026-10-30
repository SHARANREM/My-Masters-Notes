import java.util.Scanner;
class Pri{
	public static void main(String[] args){
		        Scanner sc = new Scanner(System.in);

		        System.out.print("Enter number of vertices: ");
		        int n = sc.nextInt();

		        int[][] graph = new int[n][n];

		        System.out.println("Enter weighted adjacency matrix:");

		        for (int i = 0; i < n; i++) {
		            for (int j = 0; j < n; j++) {
		                graph[i][j] = sc.nextInt();
		            }
		        }
		        boolean[] selected = new boolean[n];
		        selected[0] = true;

		        int edge = 0;
		        int total = 0;

		        while(edge<n-1){
					int min = Integer.MAX_VALUE;
					int x = -1;
					int y = -1;
					for(int i = 0; i < n; i++){
						if(selected[i]){
							for(int j=0;j<n;j++){
								if(!selected[j] && graph[i][j] != 0){
									if(graph[i][j] < min){
										min = graph[i][j];
										x = i;
										y = j;
									}
								}
							}
						}
					}
					if(x == -1){
						System.out.println("Cant form");
						return;
					}
					System.out.println(x + " " + y + " " + min);
					total += min;
					selected[y] = true;
					edge++;
				}

	}
}