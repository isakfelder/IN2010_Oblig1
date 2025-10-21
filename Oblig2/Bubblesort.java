//isakfe / olavwa oblig2 in2010 h25

public class Bubblesort extends Sorter {
    public Bubblesort(){}

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
        return "Bubblesort";
    }

    @Override
    public void sort(){
        this.n = A.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                comparison();
                if (A[j] > A[j + 1]) {
                    int tmp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = tmp;
                    swap();
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}
