package io.github.lcs002.generator;

import io.github.lcs002.config.ConfigController;
import io.github.lcs002.config.configs.SpecificMobsGeneratorConfig;
import io.github.lcs002.config.configs.SupportGemGeneratorConfig;
import io.github.lcs002.config.configs.UniqueGearsGeneratorConfig;
import io.github.lcs002.data.model.Entity;
import io.github.lcs002.data.model.SupportGem;
import io.github.lcs002.data.model.UniqueGear;
import io.github.lcs002.data.providers.concrete.SpecificMobFromJsonProvider;
import io.github.lcs002.data.providers.concrete.StatInfoFromJsonProvider;
import io.github.lcs002.data.providers.concrete.SupportGemFromJsonProvider;
import io.github.lcs002.data.providers.concrete.UniqueGearFromJsonProvider;
import io.github.lcs002.data.view.concrete.*;
import io.github.lcs002.utils.Printer;
import org.jline.jansi.Ansi;

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
        generators.add(new Generator<>(ConfigController.getConfig().mainGeneratorConfig));
        generators.add(new ResourceGenerator<>(
                ConfigController.getConfig().specificMobsGeneratorConfig,
                new SpecificMobFromJsonProvider(ConfigController.getConfig().dataDir),
                new EntityConfigsTableView(
                        ConfigController.getConfig().specificMobsGeneratorConfig.attributes,
                        new StatModsListedView(ConfigController.getConfig().specificMobsGeneratorConfig.stats)
                )
        ));
        generators.add(new ResourceGenerator<>(
                ConfigController.getConfig().uniqueGearsGeneratorConfig,
                new UniqueGearFromJsonProvider(ConfigController.getConfig().dataDir),
                new UniqueGearsTableView(
                        ConfigController.getConfig().uniqueGearsGeneratorConfig.attributes,
                        new StatModsListedView(ConfigController.getConfig().uniqueGearsGeneratorConfig.stats)
                )
        ));
        generators.add(new ResourceGenerator<>(
                ConfigController.getConfig().supportGemsGeneratorConfig,
                new SupportGemFromJsonProvider(ConfigController.getConfig().dataDir),
                new SupportGemsTableView(
                        ConfigController.getConfig().supportGemsGeneratorConfig.attributes,
                        new StatModsListedView(ConfigController.getConfig().supportGemsGeneratorConfig.stats)
                )

        ));
        generators.add(new ResourceGenerator<>(
                ConfigController.getConfig().statInfoGeneratorConfig,
                new StatInfoFromJsonProvider(ConfigController.getConfig().dataDir),
                new StatInfosTableView(ConfigController.getConfig().statInfoGeneratorConfig.attributes)
        ));
    }

    public void generate() {
        Printer.print("Starting generation...\n");
        for (Generator generator : generators) {
            generator.generate();
        }
        Printer.print(Ansi.ansi().fgBrightGreen().a("\nGeneration completed.").reset().toString());
    }
}
