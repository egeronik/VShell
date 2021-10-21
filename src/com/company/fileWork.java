package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.*;
import java.util.zip.*;


public class fileWork {
    static private String fileSysName = "sus.zip";
    private String parent=null;

    private void cat(String fileName){
        boolean exists=false;
        try{
            ZipInputStream  zin = new ZipInputStream(new FileInputStream(fileSysName));
            ZipEntry entry;
            while((entry = zin.getNextEntry()) != null){

                if(entry.getName().equals(fileName)){
                    exists=true;
                    break;
                }
            }

            if(!exists){
                System.out.println("File not exist");
                return;
            }
            for (int c = zin.read(); c != -1; c = zin.read()) {
                System.out.print((char)(c));
            }
            System.out.println();
            zin.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void ls() {
        try {
            ZipInputStream zin = new ZipInputStream(new FileInputStream(fileSysName));
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                File file = new File(entry.getName());
                if(file.getParent()==parent)
                    System.out.println(entry.getName());

            }

            zin.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void initSystem()  {

        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.next();
        while(input!="exit"){
            switch (input){
                case "cat":
                    input = scanner.next();
                    cat(input);
                    break;
                case "ls":
                    ls();
                    break;
                case "exit":
                    return;
            }
            input= scanner.next();
        }


    }






}
