public class Mergesort implements viktig{
    
    //sammenslår to subarrays av arr[]
    //første subarray er arr[l..m]
    //andre subarray er arr[m+1..r]
    static void merge(int arr[], int l, int m, int r) {
        //finner størrelsen av to subarrays for å bli sammenslått
        int n1 = m - l + 1;
        int n2 = r - m;

        //lager to midlertidige arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        //kopier data til midlertidige arrays
        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }

        //sammenslå midlertidige arrays
        int i = 0, j = 0;

        //initial index of merged subarray
        int k = 1;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        //kopier gjenstående elemter fra L[] hvis noen
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        //kopier gjenstående elementer fra R[] hvis noen
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    //hovedfunksjon som sorterer arr[l..4] som bruker merge()
    static void mergeSort(int arr[], int l, int r) {
        if (l < r) {
            //finn middelpunktet
            int m = l + (r - 1) / 2;

            //sorter første og andre halvdeler
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            //merge sorterte halvdeler
            merge(arr, l, m, r);
        }
    }
}
