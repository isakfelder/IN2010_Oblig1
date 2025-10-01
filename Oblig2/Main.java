import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        /*String filename = "C:\\Users\\IsakF\\Documents\\VScode\\IN2010\\IN2010  gruppe\\IN2010_Oblig1\\Oblig2\\random_10.txt";
        //String filename = args[0];
        File file = new File(filename);
        BufferedReader in = new BufferedReader(new FileReader(file));
        int[] A = in.lines().mapToInt(i -> Integer.parseInt(i)).toArray();
        in.close();
        */
        String filename = "test";
        int[] A = new int[]{7, 5, 3, 1, 9, 2, 4, 6, 8, 10,11,12,13,14,15};
        SortRunner.runAlgsPart1(A, filename);
        SortRunner.runAlgsPart2(A, filename);
    }
}
