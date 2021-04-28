package files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public interface Timestamp {
    public static String path = System.getProperty("user.dir") + "\\src\\files\\resources\\log\\log.csv";

    static void timestamp(String s){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            bufferedWriter.append(s + "," + LocalDateTime.now() + "\n");
            bufferedWriter.close();
        } catch (IOException e){
            System.out.println("Eroare la scrierea in fisierul Log.csv");
        }
    }
}