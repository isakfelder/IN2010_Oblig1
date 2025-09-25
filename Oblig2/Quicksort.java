public class Quicksort implements viktig{
    int sammenligning;
    int bytter;

    public Quicksort(){}

    public void sort(int[] arr){
        sort_kall(arr,0,arr.length);
    }
    
    public void sort_kall(int[] arr, int low, int high){
        sammenligning++;//if setningen som testes uansett om det skjer eller ikke
        if (low < high){
            int pivot = arr[high];
            int temp;
            int j = low-1;
            for (int i = low;i<=high; i++){
                sammenligning++;//for loop vær gang den kjører
                sammenligning++;// if setningen som testes uansett om det skjer eller ikke

                if (arr[i] <= pivot){
                    temp = arr[i];
                    j++;
                    arr[i] = arr[j];
                    arr[j] = temp;
                    bytter++;// bytter 2 plasser bare en gang hvis man ikke teller med midlertidig
                }

            }
            sammenligning++;//for loop slutter med en samenligning som må telles
        
            sort_kall(arr,0,j-1);
            sort_kall(arr,j+1,high);
        }

    }

    @Override
    public int get_sammenligninger(){
        return sammenligning;
    }

    @Override
    public int get_bytter() {
        return bytter;
        
    }    

    public static void main(String[]  args){
        int [] test = {4,76,53,23,99,101,15,7,150};

        Quicksort test_tall = new Quicksort();
        test_tall.sort_kall(test,0,test.length-1);

        for (int inn : test){
            System.out.println(inn);

        }

         
    }
    
}
