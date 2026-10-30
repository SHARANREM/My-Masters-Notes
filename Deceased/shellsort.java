class shellsort{
	private static void ss(int[] a,int n){
		for(int gap=n/2;gap>0;gap/=2){
			for(int i=gap;i<n;i++){
				int temp = a[i];
				int j = i;

				while(j>=gap && a[j-gap]>temp){
					a[j]=a[j-gap];
					j-=gap;
				}
				a[j] = temp;
			}
		}
	}

	public static void main(String[] args){
		int[] a = {2,5,6,8,4,5,2,1};
		ss(a,a.length);
		for(int x:a){
			System.out.print(x+" ");
		}
	}
}