package octo.util;

import arc.scene.style.TextureRegionDrawable;
import arc.util.Strings;

import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class ModIcons {
    public static TextureRegionDrawable mod;

    public static void load() {
        mod = loadIcon("icon-mod");
    }

    public static @NotNull TextureRegionDrawable loadIcon(String name) {
        TextureRegionDrawable drawable = Regions.toDrawable(
                Regions.getRegion(Objects.requireNonNull(name))
        );

        drawable.setName(Strings.kebabToCamel(name));
        return drawable;
    }
}
