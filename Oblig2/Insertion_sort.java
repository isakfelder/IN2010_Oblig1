//isakfe / olavwa oblig2 in2010 h25

public class Insertion_sort extends Sorter{
    
    public Insertion_sort(){}

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
        return "Insertion_sort";
    }

    @Override
    public void sort(){
        sort_kall(A); 
    }

    public int[] sort_kall(int[] A){
        for (int i = 1; i < A.length; i++){
            int key = A[i];
            int j = i - 1;

            while(j >= 0 && A[j] > key){
                comparison();
                A [j+1] = A[j];
                j--;
            }

            A[j+1] = key;
            swap();// kan sette flyttingen i while som et bytte men gjorde ikke kommer an på hva man tenker.
        }
        return A;
    }
}
