package octo;

import arc.scene.style.TextureRegionDrawable;
import arc.struct.Seq;
import arc.Core;

import mindustry.ctype.ContentType;
import mindustry.ctype.UnlockableContent;
import mindustry.world.meta.StatValue;

import octo.content.OctoStats;
import octo.util.Regions;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class Country extends UnlockableContent {
    public Seq<Supplier<TextureRegionDrawable>> ways = new Seq<>();
    public String prefix;

    public Country(String name) {
        super("country-" + name);

        this.prefix = name;
        this.localizedName = Core.bundle.get("country." + name + ".name");
        this.description = Core.bundle.get("country." + name + ".description");

        if(this.uiIcon == null) {
            this.uiIcon = Regions.getRegion("country-" + name);
        }

        if(this.fullIcon == null) {
            this.fullIcon = Regions.errorRegion();
        }

        this.alwaysUnlocked = true;
    }

    @Override
    public void setStats() {
        super.setStats();

        if(this.ways != null && this.ways.size > 0) {
            this.stats.add(OctoStats.interests, (table) -> {
                table.row();

                table.table(icons -> {
                    icons.left();

                    for(Supplier<TextureRegionDrawable> get : this.ways) {
                        TextureRegionDrawable drawable = get.get();

                        icons.image(drawable).width(24f).height(24f).pad(6f)
                                .tooltip("@interests." + drawable.getName());
                    }
                });
            });
        }
    }

    @Contract(pure = true)
    public static @NotNull StatValue get(Country country) {
        if(country == null) {
            return ignored -> {};
        }

        return (table) -> {
            table.row();
            table.table(coun -> {
                coun.image(country.uiIcon).tooltip(country.description).pad(3f);

                coun.table(info -> {
                    info.labelWrap(country.localizedName).row();
                    info.labelWrap("[orange](" + country.prefix.toUpperCase() + ")");
                }).growY().pad(3f);
            });
        };
    }

    @Override public ContentType getContentType() {
        return ContentType.bullet;
    }
}