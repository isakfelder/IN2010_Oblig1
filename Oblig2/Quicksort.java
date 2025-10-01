public class Quicksort extends Sorter{
    public Quicksort(){}

    @Override
    public void sort() {
        quicksort(A, 0, A.length- 1);
        
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
    
    public void quicksort(int[] A, int low, int high){

        comparison();
        if (low < high){
            int pivot = A[high];
            int j = low - 1;
            for (int i = low;  i <= high; i++){
                comparison();
                comparison();
                if (A[i] <= pivot){
                    j++;
                    int tmp = A[i];
                    A[i] = A[j];
                    A[j] = tmp; 
                    swap();// bruker Sorter.swap for å telle bytter
                }
            }
            // Viktig: bruk 'low' videre, ikke 0
            quicksort(this.A, low, j - 1);
            quicksort(this.A, j + 1, high);
        }else{return;}
    }
    
}
