package io.github.lcs002.generator;

import io.github.lcs002.config.ConfigController;
import io.github.lcs002.data.providers.SpecificMobProvider;
import io.github.lcs002.data.providers.UniqueGearProvider;
import io.github.lcs002.data.views.EntityConfigsTableView;
import io.github.lcs002.data.views.StatModsListedView;
import io.github.lcs002.data.views.UniqueGearsTableView;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println("Configgg " + ConfigController.getConfig());
        generators.add(new Generator<>(ConfigController.getConfig().mainGeneratorConfig));
        generators.add(new ResourceGenerator<>(
                ConfigController.getConfig().specificMobsGeneratorConfig,
                new SpecificMobProvider(),
                new EntityConfigsTableView(
                        ConfigController.getConfig().specificMobsGeneratorConfig.tableContent,
                        new StatModsListedView(ConfigController.getConfig().specificMobsGeneratorConfig.statContent)
                )
        ));
        generators.add(new ResourceGenerator<>(
                ConfigController.getConfig().uniqueGearsGeneratorConfig,
                new UniqueGearProvider(),
                new UniqueGearsTableView(
                        ConfigController.getConfig().uniqueGearsGeneratorConfig.tableContent,
                        new StatModsListedView(ConfigController.getConfig().uniqueGearsGeneratorConfig.statContent)
                )
        ));
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
