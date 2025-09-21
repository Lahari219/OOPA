package ExceptionHandling;

import java.io.*;

public class FileHandlingDemo {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("data.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found!");
        } catch (IOException e) {
            System.out.println("Error: IO Exception occurred!");
        }
    }
}