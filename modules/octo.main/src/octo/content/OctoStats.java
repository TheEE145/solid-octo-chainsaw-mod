package octo.content;

import arc.Core;
import arc.struct.ObjectMap;

import mindustry.Vars;
import mindustry.ctype.ContentType;
import mindustry.ctype.UnlockableContent;

import mindustry.gen.Icon;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;
import mindustry.world.meta.Stats;

import octo.Country;
import java.util.function.Supplier;

import static mindustry.Vars.content;

public class OctoStats {
    public static final ObjectMap<String, Supplier<Country>> modCountries = new ObjectMap<>();

    public static @SuppressWarnings("unused") Stat
            country,
            interests,
            end2;

    public static @SuppressWarnings("unused") Country
            brazil,
            britany,
            imperium,
            japan,
            korea,
            other,
            pentagon,
            poland,
            sharded,
            usa,
            wolfenstein,
            end;

    static {
        modCountries.put("betamindy-", () -> korea);
        modCountries.put("prog-mats-", () -> britany);
        modCountries.put("byte-logic-mod-", () -> japan);
        modCountries.put("dusted-lands-", () -> usa);
        modCountries.put("ol-", () -> poland);
        modCountries.put("uaw-", () -> sharded);
    }

    public static void load() {
        country = new Stat("country", StatCat.general);
        interests = new Stat("interests", StatCat.general);
    }

    public static void loadCountries() {
        brazil = new Country("br") {{
            this.ways.add(
                    () -> Icon.units,
                    () -> Icon.box,
                    () -> Icon.editor
            );
        }};

        imperium = new Country("int") {{
            this.ways.add(
                    () -> Icon.defense,
                    () -> Icon.add,
                    () -> Icon.distribution
            );
        }};

        pentagon = new Country("pen") {{
            this.ways.add(
                    () -> Icon.defense,
                    () -> Icon.units,
                    () -> Icon.commandAttack
            );
        }};

        usa = new Country("usa") {{
            this.ways.add(
                    () -> Icon.defense,
                    () -> Icon.units,
                    () -> Icon.editor,
                    () -> Icon.add
            );
        }};

        sharded = new Country("sh") {{
            this.ways.add(
                    () -> Icon.defense,
                    () -> Icon.units,
                    () -> Icon.commandAttack
            );
        }};

        wolfenstein = new Country("wol") {{
            this.ways.add(
                    () -> Icon.units,
                    () -> Icon.commandAttack
            );
        }};

        britany = new Country("gb");
        japan   = new Country("jp");
        korea   = new Country("ko");
        other   = new Country("other");
        poland  = new Country("pol");
    }

    public static void post() {
        content.each(cont -> {
            if(cont instanceof UnlockableContent content) {
                OctoStats.setupCountry(content);
            }
        });

        OctoStats.setupCountry(OctoStats.wolfenstein.stats, OctoStats.sharded);
        OctoStats.setupCountry(OctoStats.pentagon.stats, OctoStats.usa);
    }

    public static void setupCountry(Stats stats, Country country) {
        if(stats != null && country != null) {
            stats.add(OctoStats.country, Country.get(country));
        }
    }

    public static void setupCountry(UnlockableContent content) {
        if(content != null && !(content instanceof Country)) {
            Stats stats = content.stats;
            String name = content.name;

            String country = Core.bundle.get(
                    content.getContentType() + "." + name + ".country", "other"
            );

            Country cou = Vars.content.getByName(
                    ContentType.bullet, "octo-country-" + country
            );

            if(cou == null || cou == other) {
                for(String prefix : modCountries.keys()) {
                    if(name.startsWith(prefix)) {
                        cou = modCountries.get(prefix).get();
                        break;
                    }
                }
            }

            setupCountry(stats, cou == null ? other : cou);
        }
    }
}