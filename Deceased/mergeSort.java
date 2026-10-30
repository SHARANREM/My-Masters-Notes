class mergeSort{
	private static void mergeSort(int[] arr){
		int len = arr.length;
		if(len<=1) return;

		int mid = len/2;
		int[] la = new int[mid];
		int[] ra = new int[len-mid];

		int i = 0,j=0;
		for(;i<len;i++){
			if(i<mid){
				la[i] = arr[i];
			}
			else{
				ra[j] = arr[i];
				j++;
			}
		}
		mergeSort(la);
		mergeSort(ra);
		merge(la,ra,arr);
	}
	private static void merge(int[] la, int[] ra, int[] arr){
		int len = arr.length;
		int leftSize = len/2;
		int rightSize = len-leftSize;

		int i=0,l=0,r=0;
		while(l<leftSize && r<rightSize){
			if(la[l]<ra[r]){
				arr[i] = la[l];
				i++;
				l++;
			}
			else{
				arr[i] = ra[r];
				r++;
				i++;
			}
		}
		while(l<leftSize){
			arr[i] = la[l];
			l++;
			i++;
		}
		while(r<rightSize){
					arr[i] = ra[r];
					r++;
					i++;
		}
	}
	public static void main(String[] args){
		int[] a = {4,5,2,3,54,1,3,4};
		mergeSort(a);
		for(int x : a){
			System.out.println(x+" ");
		}
	}
}