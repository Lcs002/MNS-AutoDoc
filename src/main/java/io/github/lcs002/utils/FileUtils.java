package io.github.lcs002.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtils {
    public static File createFile(String filePath) {
        File file = new File(filePath);
        try {
            if (file.createNewFile()) System.out.println("File created: " + file.getName());
            else System.out.println("File already exists. Overwriting file: " + file.getName());
        }
        catch (IOException e) {
            System.out.println("Error while creating file '" + filePath + "'.\n" + e.getMessage());
            throw new RuntimeException(e);
        }
        return file;
    }

    public static void writeToFile(String file_path, String content) {
        try (FileWriter writer = new FileWriter(file_path)) {
            writer.write(content);
        }
        catch (IOException e) {
            System.out.println("Error while writing to file '" + file_path + "'.\n" + e.getMessage());
        }
    }

    public static String readConfigFile(String file_path) {
        String genConfigSerialized = null;
        try {
            genConfigSerialized = new String(Files.readAllBytes(Paths.get(file_path)));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return genConfigSerialized;
    }

    public static String[] getJsonFilesFromDir(String dir) {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(s -> s.endsWith(".json"))
                    .toArray(String[]::new);
        }
        catch (IOException e) {
            System.out.println("Error while reading files from directory '" + dir + "'.\n" + e.getMessage());
            return new String[0];
        }
    }
}
