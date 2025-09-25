import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Sorter {
    File input;
    public static void main(String[] args) {
        System.out.println("hva heter filen? ");
        Scanner sc = new Scanner(System.in);
        List<Integer> test = new List<Integer>
        
        System.out.println("n, alg1_cmp, alg1_swaps, alg1_time, alg2_cmp, alg2_swaps, alg2_time");
        if(sc.hasNextLine()){
            Insertion_sort test_in = new Insertion_sort();
            long t = System.nanoTime();
            test_in(test);
            long time = (System.nanoTime()-t)/1000;
        }

        while(sc.hasNextLine()){
            test.add(sc.nextLine);
            //sliter mer at array lengden må forandre seg som er sullete nesten bedre å lage arraylists, og så lage for loop for vær array   
        }
        
        
    }
}
