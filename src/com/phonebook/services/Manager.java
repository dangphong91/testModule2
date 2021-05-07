package com.phonebook.services;

import com.phonebook.dal.*;
import com.phonebook.model.*;

import java.io.*;
import java.util.*;

public class Manager {
    private FileCSV fileCSV;
    private ArrayList<Contact> list;

    public Manager(File file) {
        this.fileCSV = new FileCSV(file);
    }

    public int find(String phoneNumber) {
        list = fileCSV.getList();
        for (Contact ct:
                list) {
            if (ct.getPhoneNumber().equals(phoneNumber)) {
                System.out.println(ct.toString());
                return list.indexOf(ct);
            }
        }
        return -1;
    }

    public void addStudent(Contact contact) {
        if (find(contact.getPhoneNumber()) == -1) {
            list.add(contact);
            fileCSV.write();
            System.out.println("Add success.");
        }
        else {
            System.out.println("Can't add. Phone number exists.");
        }
    }

    public void editStudent(String phoneNumber,String group,String name,String gender,String address,String dob,String email) {
        int i = find(phoneNumber);
        if (i != -1) {
            list.get(i).setGroup(group);
            list.get(i).setName(name);
            list.get(i).setGender(gender);
            list.get(i).setAddress(address);
            list.get(i).setDob(dob);
            list.get(i).setEmail(email);
            fileCSV.write();
            System.out.println("Edit success.");
        }
        else {
            System.out.println("Can't edit. Phone number not exists.");
        }
    }

    public boolean removeStudent(String phoneNumber) {
        int i = find(phoneNumber);
        if (i != -1) {
            list.remove(i);
            fileCSV.write();
            System.out.println("Remove success.");
            return true;
        }
        else {
            System.out.println("Can't remove. Phone number not exists.");
            return false;
        }
    }

    public boolean checkPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^[0]+\\d{9}$");
    }

    public boolean checkEmail(String email) {
        return (email.matches("^\\w*@[a-z]*.com$") || email.matches("^\\w*@[a-z]*.vn$"));
    }

    public void readFile() {
        fileCSV.read();
    }

    public void writeFile() {
        fileCSV.write();
    }

    public void menu() {
        System.out.println("Menu:\n" +
                "1.Xem danh sách.\n" + // đọc từ file
                "2.Thêm mới.\n" +
                "3.Cập nhật.\n" +
                "4.Xóa số điện thoại.\n" +
                "5.Tìm kiếm.\n" +
                "6.Ghi vào file.\n" +
                "7.Thoát.");
    }
    public void run() {
        Scanner sc = new Scanner(System.in);
        String choice;
        String phoneNumber,group,name,gender,address,dob,email;
        while (true) {
            menu();
            System.out.println("Chọn chức năng:");
            choice = sc.nextLine();
            switch (choice) {
                case "1" :
                    System.out.println("List Phone Number:");
                    readFile();
                    break;
                case "2" :
                    System.out.println("Add Contact:");
                    System.out.println("Phone Number:");
                    phoneNumber = sc.nextLine();
                    if (checkPhoneNumber(phoneNumber)) {
                        System.out.println("Group:");
                        group = sc.nextLine();
                        System.out.println("Name:");
                        name = sc.nextLine();
                        System.out.println("Gender:");
                        gender = sc.nextLine();
                        System.out.println("Address:");
                        address = sc.nextLine();
                        System.out.println("DOB:");
                        dob = sc.nextLine();
                        System.out.println("Email:");
                        email = sc.nextLine();
                        Contact ct = new Contact(phoneNumber, group, name, gender, address, dob, email);
                        addStudent(ct);
                    } else {
                        System.out.println("Phone number invalid.");
                    }
                    break;
                case "3" :
                    System.out.println("Edit Contact:");
                    System.out.println("Phone Number:");
                    phoneNumber = sc.nextLine();
                    if (checkPhoneNumber(phoneNumber)) {
                        System.out.println("Group:");
                        group = sc.nextLine();
                        System.out.println("Name:");
                        name = sc.nextLine();
                        System.out.println("Gender:");
                        gender = sc.nextLine();
                        System.out.println("Address:");
                        address = sc.nextLine();
                        System.out.println("DOB:");
                        dob = sc.nextLine();
                        System.out.println("Email:");
                        email = sc.nextLine();
                        editStudent(phoneNumber, group, name, gender, address, dob, email);
                    } else {
                        System.out.println("Phone number invalid.");
                    }
                    break;
                case "4" :
                    System.out.println("Remove Contact:");
                    System.out.println("Phone Number:");
                    phoneNumber = sc.nextLine();
                    if (checkPhoneNumber(phoneNumber)) {
                        removeStudent(phoneNumber);
                    } else {
                        System.out.println("Phone number invalid.");
                    }
                    break;
                case "5" :
                    System.out.println("Find Contact:");
                    System.out.println("Phone Number:");
                    phoneNumber = sc.nextLine();
                    if (checkPhoneNumber(phoneNumber)) {
                        find(phoneNumber);
                    } else {
                        System.out.println("Phone number invalid.");
                    }
                    break;
                case "6" :
                    System.out.println("Write File:");
                    writeFile();
                    break;
                case "7" :
                    System.exit(0);
                default:
                    System.out.println("Not Now ...");
            }
        }
    }
}
