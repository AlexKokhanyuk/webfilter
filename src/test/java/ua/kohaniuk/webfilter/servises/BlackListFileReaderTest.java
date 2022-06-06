package ua.kohaniuk.webfilter.servises;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class BlackListFileReaderTest {


    void createFile() {
        FileWriter fileWriter;
        String fileName = ".\\blacklistTest.txt";
        File file = new File(fileName);
        try {
            if (file.exists()) {
                Files.delete(Path.of(fileName));
            }
            fileWriter = new FileWriter(fileName, true);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 100000; i++) {
                for (int j = 0; j < 4; j++) {
                    stringBuilder.append(getRandomNumber(0, 256));
                    if (j < 3) {
                        stringBuilder.append(".");
                    }
                }
                stringBuilder.append("\n");
            }
            stringBuilder.append("0.0.0.0");
            fileWriter.write(String.valueOf(stringBuilder));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void getSetOfIP() {
        createFile();
        BlackListFileReader blackListFileReader =
                new BlackListFileReader(".\\blacklistTest.txt", ".\\blacklistTest.txt");
        blackListFileReader.toRead();
        boolean contains = blackListFileReader.getSetOfIP().contains("0.0.0.0");
        deleteFile();
        assertEquals(contains, true);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @AfterAll
    static void deleteFile() {
        String fileName = ".\\blacklistTest.txt";
        File file = new File(fileName);
        if (file.exists()) {
            try {
                Files.delete(Path.of(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}