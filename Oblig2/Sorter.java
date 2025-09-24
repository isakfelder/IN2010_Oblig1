import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Sorter {
    public static void main(String[] args) throws IOException {
        String filename = "C:\\Users\\IsakF\\Documents\\VScode\\IN2010\\IN2010  gruppe\\IN2010_Oblig1\\Oblig2\\random_10.txt";
        //String filename = args[0]; denne skal brukes!

        // Leser alle tallene linje for linje
        List<Integer> tallListe = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linje;
            while ((linje = br.readLine()) != null) {
                tallListe.add(Integer.parseInt(linje.trim()));
            }
        }

        // Konverterer til int[]
        int[] A = tallListe.stream().mapToInt(Integer::intValue).toArray();

        // Skriver ut tallene
        //System.out.println(Arrays.toString(A));

        Mergesort mergesorter = new Mergesort();
        mergesorter.mergeSort(A, 1, A.length);

        //System.out.println(mergesorter.get_Bytter());
        //System.out.println(mergesorter.get_Sammenligninger());
        
        // Her kan du kalle sorteringsmetodene
        // SortRunner.runAlgsPart1(A, filename);
        // SortRunner.runAlgsPart2(A, filename);
    }
}
