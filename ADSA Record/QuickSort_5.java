public class QuickSort_5 {

    static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int p = partition(a, low, high);

            quickSort(a, low, p - 1);
            quickSort(a, p + 1, high);
        }
    }

    static int partition(int[] a, int low, int high) {
        int pivot = a[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (a[j] < pivot) {
                i++;

                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        int temp = a[i + 1];
        a[i + 1] = a[high];
        a[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {

        int[] a = { 40, 20, 60, 10, 30 };

        System.out.println("Before sorting:");

        for (int x : a)
            System.out.print(x + " ");

        quickSort(a, 0, a.length - 1);

        System.out.println("\nAfter sorting:");

        for (int x : a)
            System.out.print(x + " ");
    }
}
