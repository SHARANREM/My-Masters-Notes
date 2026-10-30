class QuickSort{
	static void quicksort(int[] arr, int start, int end){
		if(start<end){
			int p = partition(arr,start,end);

			quicksort(arr,start,p-1);
			quicksort(arr,p+1,end);
		}
	}

	static int partition(int[] arr,int start, int end){
		int pivot = arr[end];
		int i = start-1;

		for(int j = start;j<end;j++){
			if(arr[j]<pivot){
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		int temp = arr[i+1];
		arr[i+1]=arr[end];
		arr[end] = temp;

		return i+1;
	}

	public static void main(String[] args){
		int[] a = {8,5,2,4,7,4,5,1,3,4};

		quicksort(a,0,a.length-1);

		for(int x : a){
			System.out.print(x+" ");
		}
	}
}