package io.github.lcs002.generator.generators;

import io.github.lcs002.config.Config;
import io.github.lcs002.config.configs.UniqueGearsGeneratorConfig;
import io.github.lcs002.data.DataProvider;
import io.github.lcs002.data.mmorpg.StatMod;
import io.github.lcs002.data.mmorpg.UniqueGear;
import io.github.lcs002.generator.ResourceGenerator;
import io.github.lcs002.utils.MarkdownUtils;

import java.util.ArrayList;
import java.util.List;

public class UniqueGearsGenerator extends ResourceGenerator<UniqueGearsGeneratorConfig, UniqueGear> {

    public UniqueGearsGenerator(Config config, DataProvider<UniqueGear> dataProvider) {
        super(config, dataProvider);
    }

    @Override
    protected void setContentBody(StringBuilder content) {
        content.append(generateUniqueGears());
    }

    @Override
    protected Class<UniqueGearsGeneratorConfig> getGenConfigClass() {
        return UniqueGearsGeneratorConfig.class;
    }

    private String generateUniqueGears() {
        String[] localizedHeaders = generateHeader();
        String[][] localizedContent = generateContent();
        return MarkdownUtils.table(localizedHeaders, localizedContent);
    }

    private String[] generateHeader() {
        String[] localizedHeaders = new String[genConfig.tableContent.length];
        for (int i = 0; i < genConfig.tableContent.length; i++) {
            UniqueGear.Attribute attribute = genConfig.tableContent[i];
            localizedHeaders[i] = attribute.localizer.parseKey(config.localization);
        }
        return localizedHeaders;
    }

    private String[][] generateContent() {
        ArrayList<String[]> uniqueGearsList = new ArrayList<>();
        for (UniqueGear gear : dataProvider.getData()) {
            uniqueGearsList.add(generateGear(gear));
        }
        return uniqueGearsList.toArray(new String[0][0]);
    }

    private String[] generateGear(UniqueGear gear) {
        System.out.println("Generating gear: " + gear.guid);

        String[] gearData = new String[genConfig.tableContent.length];
        for (int i = 0; i < genConfig.tableContent.length; i++) {
            gearData[i] = String.valueOf(genConfig.tableContent[i].localizer.parseValue(
                    switch (genConfig.tableContent[i]) {
                        case UniqueGear.Attribute.BASE_GEAR -> gear.baseGear;
                        case UniqueGear.Attribute.FLAVOR_TEXT -> gear.flavorText;
                        case UniqueGear.Attribute.FORCE_ITEM_ID -> gear.forceItemId;
                        case UniqueGear.Attribute.GUID -> gear.guid;
                        case UniqueGear.Attribute.LEAGUE -> gear.league;
                        case UniqueGear.Attribute.MIN_TIER -> gear.minTier;
                        case UniqueGear.Attribute.RARITY -> gear.rarity;
                        case UniqueGear.Attribute.REPLACES_NAME -> gear.replacesName;
                        case UniqueGear.Attribute.RUNABLE -> gear.runable;
                        case UniqueGear.Attribute.UNIQUE_STATS -> generateStats(gear.uniqueStats);
                    },
                    config.localization
            ));
        }

        return gearData;
    }

    private String generateStats(List<StatMod> stats) {
          StringBuilder statsString = new StringBuilder();

          for (StatMod stat : stats) {
              for (int i = 0; i < genConfig.statContent.length; i++) {
                  statsString.append(genConfig.statContent[i].localizer.parseValue(
                          switch (genConfig.statContent[i]) {
                              case StatMod.Attribute.STAT -> stat.stat;
                              case StatMod.Attribute.MIN -> stat.min;
                              case StatMod.Attribute.MAX -> stat.max;
                              case StatMod.Attribute.TYPE -> stat.type;
                          },
                          config.localization
                  ));
              }
          }
          return statsString.toString();
    }
}
