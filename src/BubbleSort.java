import java.io.*;
import java.util.Random;

public class BubbleSort {

    public static int[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = rand.nextInt(101);
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);
            for (int num : array) {
                writer.println(num);
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error writing to file");
        }
    }

    public static int[] readFileToArray(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            reader.close();
            
            int[] array = new int[lines];
            reader = new BufferedReader(new FileReader(filename));
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                array[index++] = Integer.parseInt(line);
            }
            reader.close();
            return array;
        } catch (Exception e) {
            System.out.println("Error reading file");
            return null;
        }
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select 1 or 2:");
        System.out.println("(1) Generate random array and save");
        System.out.println("(2) Read, sort, and save sorted array");
        int choice = Integer.parseInt(input.readLine());
        
        switch (choice) {
            case 1:
                writeArrayToFile(createRandomArray(10), "random.txt");
                System.out.println("Saved to random.txt");
                break;
            case 2:
                int[] arr = readFileToArray("random.txt");
                bubbleSort(arr);
                writeArrayToFile(arr, "sorted.txt");
                System.out.println("Sorted and saved to sorted.txt");
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}
