import java.util.Arrays;

public class Mergesort extends Sorter{
    int sammenligninger;
    int bytter;
    int[] data;
    long time;

    public Mergesort() {}

    @Override
    public void initializePart1(int[] A) {
        data = A.clone();
    }

    @Override
    public void initializePart2(int[] A) {
        data = A.clone();
    }

    @Override
    public void sort() {
        sammenligninger = 0;
        bytter = 0;
        long t = System.nanoTime();
        mergeSort(data, 0, data.length - 1);
        time = (System.nanoTime() - t) / 1000;
    }

    //sammenslår to subarrays av arr[]
    //første subarray er arr[l..m]
    //andre subarray er arr[m+1..r]
    public void merge(int arr[], int l, int m, int r) {
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
        int k = l;
        while (i < n1 && j < n2) {
            sammenligninger++;
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                bytter++;
                i++;
            } else {
                arr[k] = R[j];
                bytter++;
                j++;
            }
            k++;
        }
        //kopier gjenstående elemter fra L[] hvis noen
        while (i < n1) {
            arr[k] = L[i];
            bytter++;
            i++;
            k++;
        }

        //kopier gjenstående elementer fra R[] hvis noen
        while (j < n2) {
            arr[k] = R[j];
            bytter++;
            j++;
            k++;
        }
    }

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
    }

    /* 
    @Override
    public void runResetAndIncBy(int inc) {
        int[] subarray = Arrays.copyOfRange(data, 0, inc);
        sort();
    }*/

    @Override
    public String algorithmName() {
        return "mergeSort";
    }

    @Override 
    public String headerString() {
        return "merge_cmp, merge_swaps, merge_time";
    }
}
