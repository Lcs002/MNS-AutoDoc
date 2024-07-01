package io.github.lcs002.data.providers;

import io.github.lcs002.data.JsonData;
import io.github.lcs002.utils.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class DataFromJsonProvider<T extends JsonData<T>> implements DataProvider<T> {
    private final String path;
    private final Class<T> clazz;

    public DataFromJsonProvider(String path, Class<T> clazz) {
        this.path = path;
        this.clazz = clazz;
    }
    
    @Override
    public T[] getData() {
        ArrayList<T> datas = new ArrayList<>();

        Path[] files = FileUtils.getJsonFilesFromDir(path);

        for (Path file : files) {
            try {
                String serialized = new String(Files.readAllBytes(file));
                T data = clazz.getDeclaredConstructor().newInstance().fromJson(serialized);
                datas.add(data);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return datas.toArray((T[]) new JsonData[0]);
    }
}
