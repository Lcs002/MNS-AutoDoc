package io.github.lcs002.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtils {

    public static boolean exists(String path) {
        return new File(path).exists();
    }

    public static void createDir(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            if (dir.mkdirs()) Printer.printDebug("Directory created: " + dir.getName());
            else Printer.printDebug("Failed to create directory: " + dir.getName());
        }
    }

    public static File createFile(String filePath) {
        File file = new File(filePath);
        try {
            if (file.createNewFile()) Printer.printDebug("File created: " + file.getName());
            else Printer.printDebug("File already exists. Overwriting file: " + file.getName());
        }
        catch (IOException e) {
            Printer.print("Error while creating file '" + filePath + "'.\n" + e.getMessage());
            throw new RuntimeException(e);
        }
        return file;
    }

    public static void writeToFile(String file_path, String content) {
        try (FileWriter writer = new FileWriter(file_path)) {
            writer.write(content);
        }
        catch (IOException e) {
            Printer.print("Error while writing to file '" + file_path + "'.\n" + e.getMessage());
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

    public static Path[] getJsonFilesFromDir(String dir) {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                    .filter(Files::isRegularFile)
                    .filter(s -> s.toString().endsWith(".json"))
                    .toArray(Path[]::new);
        }
        catch (IOException e) {
            Printer.print("Error while reading files from directory '" + dir + "'.\n" + e.getMessage());
            return new Path[0];
        }
    }
}
