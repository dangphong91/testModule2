package com.phonebook.presentation;

import com.phonebook.services.Manager;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("phonebook.csv");
        Manager phonebook1 = new Manager(file);
        phonebook1.run();
    }
}
