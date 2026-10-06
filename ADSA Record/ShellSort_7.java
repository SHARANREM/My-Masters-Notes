import java.util.Scanner;

class ShellSort_7 {

    static void shellSort(int arr[], int n) {

        for (int gap = n / 2; gap > 0; gap /= 2) {

            for (int i = gap; i < n; i++) {

                int temp = arr[i];
                int j = i;

                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }

                arr[j] = temp;
            }
        }
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.print("Enter the Number of Array Elements: ");
        int n = s.nextInt();

        int arr[] = new int[n];

        System.out.println("Enter " + n + " Values:");

        for (int i = 0; i < n; i++)
            arr[i] = s.nextInt();

        System.out.println("Before Sorting:");

        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " -> ");

        System.out.println();

        shellSort(arr, n);

        System.out.println("After Sorting:");

        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " -> ");

        System.out.println();

        s.close();
    }
}
