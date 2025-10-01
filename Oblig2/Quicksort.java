public class Quicksort extends Sorter{
    public Quicksort(){}
    public int sammenligninger;
    public int bytter;
    long time;

    @Override
    public void sort() {
        sammenligninger = 0;
        bytter = 0;
        long t = System.nanoTime();
        if (n > 0) {
            quicksort(A, 0, n - 1);
        }
        time = (System.nanoTime() - t) / 1000;
    }

    @Override
    public void initializePart1(int[] A) {
        super.initializePart1(A);
    }

    @Override
    public void initializePart2(int[] A) {
        super.initializePart2(A);
    }

    @Override
    public String algorithmName() {
        return "quicksort";
    }
    
    public void quicksort(int[] arr, int low, int high){
        // sørg for at swap() og andre metoder jobber på samme array
        if (this.A != arr) {
            this.A = arr;
            this.n = arr.length;
        }

        if (low < high){
            int pivot = A[high];
            int j = low - 1;
            for (int i = low; i <= high; i++){
                // bruk leq for å telle sammenligninger
                if (leq(A[i], pivot)){
                    j++;
                    swap(i, j); // bruker Sorter.swap for å telle bytter
                }
            }
            // Viktig: bruk 'low' videre, ikke 0
            quicksort(this.A, low, j - 1);
            quicksort(this.A, j + 1, high);
        }
    }

    public static void main(String[]  args){
        int [] test = {4,76,53,23,99,101,15,7,150};

        Quicksort test_tall = new Quicksort();
        test_tall.quicksort(test,0,test.length-1);

        for (int inn : test){
            System.out.println(inn);

        }

         
    }
    
}
