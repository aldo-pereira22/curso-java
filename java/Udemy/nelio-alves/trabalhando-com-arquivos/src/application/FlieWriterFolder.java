package application;

import java.io.File;
import java.util.Scanner;

public class FlieWriterFolder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the folder path: ");
        String strPath = sc.next();


        File path = new File(strPath);
        File [] folders = path.listFiles(File::isDirectory);
        System.out.println("FOLDERS: ");
        for(File folder: folders){
            System.out.println(folder);
        }

        File[] files = path.listFiles(File::isFile);
        System.out.println("\n\n\n\n\n\nFiles: ");
        for (File file: files){
            System.out.println(file);
        }/

        sc.close();
    }
}
