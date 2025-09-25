public class Insertion_sort implements viktig{
    int sammenligning;
    int bytter;
    public Insertion_sort(){}

    public int[] sort(int[] arr){
        for (int i = 1; i < arr.length; i++){
            sammenligning++;
            int key = arr[i];
            int j = i - 1;

            while(j >= 0 && arr[j] > key){
                arr [j+1] = arr[j];
                j--;
            }
            sammenligning++;
            sammenligning++;

            arr[j+1] = key;

        }
        sammenligning++;
        

        return arr;

    }

    @Override
    public int get_sammenligninger(){
        return sammenligning;
    }

    @Override
    public int get_bytter() {
        return bytter;
        
    }
}
