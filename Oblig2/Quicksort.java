public class Quicksort extends Sorter{
    public Quicksort(){}
    public int sammenligninger;
    public int bytter;
    int[] data;
    long time;

    @Override
    public void sort() {
        sammenligninger = 0;
        bytter = 0;
        long t = System.nanoTime();
        sort(data, 0, data.length - 1);
        time = (System.nanoTime() - t) / 1000;
    }

    @Override
    public String algorithmName() {
        return "quicksort";
    }
    
    public void sort(int[] arr, int low, int high){
        //int pivot = (high + low)/2;
        if (low < high){
            int pivot = arr[high];
            int temp;
            int j = low-1;
            for (int i = low;i<=high; i++){
                temp = arr[i];
                if (temp <= pivot){
                    j++;
                    arr[i] = arr[j];
                    arr[j] = temp;
                }

            }
        
            sort(arr,0,j-1);
            sort(arr,j+1,high);
        }

    }

    public static void main(String[]  args){
        int [] test = {4,76,53,23,99,101,15,7,150};

        Quicksort test_tall = new Quicksort();
        test_tall.sort(test,0,test.length-1);

        for (int inn : test){
            System.out.println(inn);

        }

         
    }
    
}
