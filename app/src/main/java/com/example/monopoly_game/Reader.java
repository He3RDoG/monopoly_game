package com.example.monopoly_game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Reader {
    public String[] readProprieties() {
        File file = new File("java/com/example/monopoly_game/proprieties.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        LinkedList<String> a = new LinkedList<>();
        while (scanner.hasNextLine()) {
            a.add(scanner.nextLine());
        }
        return (String[]) a.toArray();
    }
}
