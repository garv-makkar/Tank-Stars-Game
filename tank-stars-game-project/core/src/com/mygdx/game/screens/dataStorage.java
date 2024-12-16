package com.mygdx.game.screens;

import java.io.Serializable;
import java.nio.file.Paths;
import java.io.Serializable;
import java.nio.file.Paths;
import java.nio.file.Files;

public class dataStorage {


    public static void saveData(Serializable data, String fileName) throws Exception {
        try (java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(new java.io.FileOutputStream(Paths.get(fileName).toFile()))) {
            out.writeObject(data);
        }
    }

    public static Object loadData(String fileName) throws Exception {
        try (java.io.ObjectInputStream in = new java.io.ObjectInputStream(new java.io.FileInputStream(Paths.get(fileName).toFile()))) {
            return in.readObject();
        }
    }



}
