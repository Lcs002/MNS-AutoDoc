package io.github.lcs002.generator.generators;

import io.github.lcs002.config.Config;
import io.github.lcs002.config.ConfigController;
import io.github.lcs002.config.configs.SpecificMobsGeneratorConfig;
import io.github.lcs002.data.DataProvider;
import io.github.lcs002.data.mmorpg.EntityConfig;
import io.github.lcs002.data.mmorpg.SpecialMobStats;
import io.github.lcs002.data.mmorpg.StatMod;
import io.github.lcs002.generator.ResourceGenerator;
import io.github.lcs002.utils.MarkdownUtils;

import java.util.ArrayList;
import java.util.List;

public class SpecificMobsGenerator extends ResourceGenerator<SpecificMobsGeneratorConfig, EntityConfig> {

    public SpecificMobsGenerator(Config config, DataProvider<EntityConfig> dataProvider) {
        super(config, dataProvider);
    }

    @Override
    protected void setContentBody(StringBuilder content) {
        content.append(generateMobs());
    }

    @Override
    public Class<SpecificMobsGeneratorConfig> getGenConfigClass() {
        return SpecificMobsGeneratorConfig.class;
    }

    private String generateMobs()
    {
        ArrayList<String[]> mobsList = new ArrayList<>();
        for (EntityConfig entityConfig : dataProvider.getData()) {
            mobsList.add(generateMob(entityConfig));
        }

        String[][] mobs = new String[mobsList.size()][genConfig.tableContent.length];
        for (int i = 0; i < mobsList.size(); i++) {
            mobs[i] = mobsList.get(i);
        }

        String[] localizedHeaders = new String[genConfig.tableContent.length];
        for (int i = 0; i < genConfig.tableContent.length; i++) {
            EntityConfig.Attribute attributeFormat = genConfig.tableContent[i];
            localizedHeaders[i] = attributeFormat.localizer.parseKey(ConfigController.getConfig().localization);
        }

        return MarkdownUtils.table(localizedHeaders, mobs);
    }

    private String[] generateMob(EntityConfig entityConfig) {
        System.out.println("Generating mob: " + entityConfig.identifier);

        String[] mobData = new String[genConfig.tableContent.length];
        for (int i = 0; i < genConfig.tableContent.length; i++) {
            EntityConfig.Attribute attributeFormat = genConfig.tableContent[i];
            mobData[i] = String.valueOf(switch (attributeFormat) {
                case EntityConfig.Attribute.IDENTIFIER -> identifierToName(String.valueOf(entityConfig.identifier));
                case EntityConfig.Attribute.DMG_MULTI -> entityConfig.dmgMulti;
                case EntityConfig.Attribute.HP_MULTI -> entityConfig.hpMulti;
                case EntityConfig.Attribute.MAX_LVL -> entityConfig.maxLvl;
                case EntityConfig.Attribute.MIN_LVL -> entityConfig.minLvl;
                case EntityConfig.Attribute.LOOT_MULTI -> entityConfig.lootMulti;
                case EntityConfig.Attribute.EXP_MULTI -> entityConfig.exp_multi;
                case EntityConfig.Attribute.SET_HEALTH_DAMAGE_OVERRIDE -> entityConfig.setHealthDamageOverride;
                case EntityConfig.Attribute.SET_RAR -> entityConfig.setRar;
                case EntityConfig.Attribute.STAT_MULTI -> entityConfig.statMulti;
                case EntityConfig.Attribute.STATS -> getStats(entityConfig.stats);
            });
        }

        return mobData;
    }

    private String identifierToName(String name) {
        name = name.toLowerCase();
        name = name.split(":")[1];
        name = name.replace('_', ' ');
        char[] nameChars = name.toCharArray();
        if (nameChars.length > 0) {
            nameChars[0] = Character.toUpperCase(nameChars[0]);
            for (int i = 0; i < name.length(); i++)
                if (nameChars[i] == ' ') nameChars[i+1] = Character.toUpperCase(nameChars[i+1]);

        }
        return new String(nameChars);
    }

    private String getStats(SpecialMobStats stats) {
        StringBuilder stringBuilder = new StringBuilder();
       List<StatMod> statsList = stats.stats;
        for (StatMod stat : statsList) {
            for (StatMod.Attribute statContent : genConfig.statContent) {
                switch (statContent) {
                    case STAT:
                        stringBuilder.append(MarkdownUtils.bold(StatMod.Attribute.STAT.localizer.parseValue(stat.stat, ConfigController.getConfig().localization).toString()));
                        stringBuilder.append(" ");
                        break;
                    case TYPE:
                        stringBuilder.append(stat.type.toUpperCase());
                        break;
                    case MIN:
                        stringBuilder.append(MarkdownUtils.italic(StatMod.Attribute.MIN.localizer.parseValue(stat.min, ConfigController.getConfig().localization).toString()));
                        break;
                    case MAX:
                        if (stat.min != (stat.max)) {
                            stringBuilder.append(MarkdownUtils.italic(" to "));
                            stringBuilder.append(MarkdownUtils.italic(StatMod.Attribute.MAX.localizer.parseValue(stat.max, ConfigController.getConfig().localization).toString()));
                        }
                        stringBuilder.append(" ");
                        break;
                    case null:
                        break;
                }
            }
            stringBuilder.append("<br>");
        }
        return stringBuilder.toString();
    }
}
