package com.phonebook.dal;

import com.phonebook.model.Contact;

import java.io.*;
import java.util.*;

public class FileCSV {
    private File file;
    private  ArrayList<Contact> list;
    private final String HEADER = "Phone Number,Group,Name,Gender,Address,DOB,Email";

    public FileCSV(File file) {
        this.file = file;
    }

    public ArrayList<Contact> getList() {
        return list;
    }

    public void read(){
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            list = new ArrayList<>();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(HEADER)) {
                    continue;
                }
                String[] st = line.split(",");
                Contact contact = new Contact(st[0],st[1],st[2],st[3],st[4],st[5],st[6]);
                System.out.print(contact.toString());
                list.add(contact);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write() {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(HEADER);
            bw.append("\n");
            for (Contact ct :
                    list) {
                bw.append(ct.toString());
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
