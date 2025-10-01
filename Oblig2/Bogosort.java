import java.util.Arrays;

public class Bogosort extends Sorter {

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
        return "Bogosort";
    }

    @Override
    public void sort(){
        bogosort(A);
        
    }
    
    public void bogosort(int[] arr) {
        while (!isSorted(arr)) {
            shuffle(arr);
        }
    }

    public boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            comparison();
            comparison();
            if (arr[i] > arr[i + 1]) return false;
        }
        return true;
    }

    public void shuffle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            comparison();
            int j = (int)(Math.random() * arr.length); // stokker
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            swap();
        }
    }
}
