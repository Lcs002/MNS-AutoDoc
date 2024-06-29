package io.github.lcs002.generator;

import io.github.lcs002.config.Config;
import io.github.lcs002.config.ConfigController;
import io.github.lcs002.data.providers.SpecificMobProvider;
import io.github.lcs002.data.providers.UniqueGearProvider;
import io.github.lcs002.generator.generators.MainGenerator;
import io.github.lcs002.generator.generators.SpecificMobsGenerator;
import io.github.lcs002.generator.generators.UniqueGearsGenerator;
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
        generators = new ArrayList<>();
        generators.add(new MainGenerator(ConfigController.getConfig()));
        generators.add(new SpecificMobsGenerator(ConfigController.getConfig(), new SpecificMobProvider()));
        generators.add(new UniqueGearsGenerator(ConfigController.getConfig(), new UniqueGearProvider()));
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
