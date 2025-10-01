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

    public int[] sort_kall(int[] noe){
        for (int i = 1; i < noe.length; i++){
            comparison();
            int key = noe[i];
            int j = i - 1;


            while(j >= 0 && noe[j] > key){
                comparison();
                comparison();
                noe [j+1] = noe[j];
                j--;
            }

            noe[j+1] = key;
             swap();// kan sette flyttingen i while som et bytte men gjorde ikke kommer an på hva man tenker.
           

        }

        return noe;

    }
}
