package io.github.lcs002.generator;

import io.github.lcs002.config.Config;
import io.github.lcs002.config.ConfigController;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GeneratorController {
    private static GeneratorController instance;

    public static GeneratorController getInstance() {
        if (instance == null) {
            instance = new GeneratorController();
        }
        return instance;
    }

    public List<Generator> generators;

    private GeneratorController() {}

    public void init() {
        instance = new GeneratorController();

        instance.generators = new ArrayList<>();

        Reflections reflections = new Reflections("io.github.lcs002.generator");
        Set<Class<? extends Generator>> classes = reflections.getSubTypesOf(Generator.class);
        for (Class<? extends Generator> generatorClass : classes) {
            try {
                Generator generator = generatorClass.getDeclaredConstructor().newInstance();
                generator.init(ConfigController.getConfig());
                instance.generators.add(generator);
            } catch (Exception e) {
                System.out.println("Error while creating generator instance: " + e.getMessage());
            }
        }
    }

    public void generate() {
        System.out.println("Starting generation...");
        for (Generator generator : generators) {
            System.out.println("#".repeat(80));
            generator.generate();
        }
        System.out.println("#".repeat(80));
        System.out.println("Generation completed.");
    }
}
