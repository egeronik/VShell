package com.company;

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.*;
import java.util.zip.*;


public class fileWork {
    final static private String fileSysName = "sus.zip";
    private String parent="";

    private void cat(String fileName){
        boolean exists=false;
        try{
            ZipInputStream  zin = new ZipInputStream(new FileInputStream(fileSysName));
            ZipEntry entry;
            while((entry = zin.getNextEntry()) != null){
                String clearName;
                if(entry.getName().lastIndexOf('/')==-1) clearName=entry.getName();
                else clearName = entry.getName().substring(entry.getName().lastIndexOf('/')+1);
                if(clearName.equals(fileName)){

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
                if(file.getParent()==null&&parent.equals("")) System.out.println(entry.getName());
                else if(file.getParent()!=null&&file.getParent().equals(parent))
                   // if(entry.getName().lastIndexOf('/')!=-1) System.out.println(entry.getName().substring(entry.getName().lastIndexOf('/'),entry.getName().length()));
                    System.out.println(entry.getName());
                //System.out.println(' ' + file.getParent());


            }

            zin.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void cd(String directoryName){
        if(directoryName.equals("..")){
            if(parent.lastIndexOf('\\')==-1) parent="";
            else parent=parent.substring(0,parent.lastIndexOf('\\'));
            return;
        }
        if(!parent.equals("")) directoryName= parent+'/'+directoryName;
        try {
            boolean exitsts = false;
            ZipInputStream zin = new ZipInputStream(new FileInputStream(fileSysName));
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {

                if (entry.getName().equals(directoryName) ){
                    directoryName = directoryName.replace('/','\\');
                    directoryName = directoryName.substring(0,directoryName.length()-1);
                    parent =  directoryName;
                    return;
                }


            }
            System.out.println("Directory not exists");
            zin.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }



    }

    private void pwd(){
        System.out.println(parent);
    }

    public void initSystem()  {

        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.next();
        while(!input.equals("exit")){
            switch (input) {
                case "cat" -> {
                    input = scanner.next();
                    cat(input);
                }
                case "ls" -> ls();
                case "cd" -> {
                    input = scanner.next();
                    cd(input);
                }
                case "pwd" -> pwd();
            }
            input= scanner.next();
        }


    }






}
