import java.util.Arrays;

public class Mergesort extends Sorter{
    int sammenligninger;
    int bytter;
    int[] data;
    long time;

    public Mergesort() {}

    @Override
    public void initializePart1(int[] A) {
        super.initializePart1(A);
    }

    @Override
    public void initializePart2(int[] A) {
        super.initializePart2(A);
    }

    @Override
    public void sort() {
        if (n > 0) {
            mergeSortRec(0, n - 1);
        }
    }

    public void mergeSort(int[] arr, int l, int r) {
        this.A = arr;
        this.n = arr.length;

        int left = Math.max(0, l);
        int right = Math.min(r, arr.length - 1);
        if (left < right) {
            mergeSortRec(left, right);
        }
    }

    private void mergeSortRec(int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSortRec(l, m);
            mergeSortRec(m + 1, r);
            merge(l, m, r);
        }
    }

    //sammenslår to subarrays av arr[]
    //første subarray er arr[l..m]
    //andre subarray er arr[m+1..r]
    public void merge(int l, int m, int r) {
        //finner størrelsen av to subarrays for å bli sammenslått
        int n1 = m - l + 1;
        int n2 = r - m;

        //lager to midlertidige arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        //kopier data til midlertidige arrays
        for (int i = 0; i < n1; i++) {
            L[i] = A[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = A[m + 1 + j];
        }

        //sammenslå midlertidige arrays
        int i = 0, j = 0;

        //initial index of merged subarray
        int k = l;
        while (i < n1 && j < n2) {
            comparison();
            comparison();
            if (L[i] <= R[j]) {
                A[k] = L[i];
                swap();
                i++;
            } else {
                A[k] = R[j];
                swap();
                j++;
            }
            k++;
        }
        //kopier gjenstående elemter fra L[] hvis noen
        while (i < n1) {
            comparison();
            A[k] = L[i];
            swap();
            i++;
            k++;
        }

        //kopier gjenstående elementer fra R[] hvis noen
        while (j < n2) {
            comparison();
            A[k] = R[j];
            swap();
            j++;
            k++;
        }
    }

    /* 

    //hovedfunksjon som sorterer arr[l..4] som bruker merge()
    public void mergeSort(int arr[], int l, int r) {
        if (l < r) {
            //finn middelpunktet
            int m = l + (r - 1) / 2;

            //sorter første og andre halvdeler
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            //merge sorterte halvdeler
            merge(arr, l, m, r);
        }
    }*/

    @Override
    public String algorithmName() {
        return "mergeSort";
    }
}
