package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWeiter {
    public static void main(String[] args) {
        String [] lines = new String[] {"Good morning", "Good afternoon","Good night"};

        String path = "/home/aldo/teste_writer.txt";

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))){
            bw.write("Teste de escrita!!!");
            bw.newLine();
            bw.newLine();
            bw.newLine();
            for(String line : lines){
                bw.write(line);
                bw.newLine();
            }
        }catch (IOException e){
             e.printStackTrace();
        }
    }
}
