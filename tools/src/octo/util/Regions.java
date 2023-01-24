package octo.util;

import arc.Core;
import arc.graphics.Texture;
import arc.graphics.g2d.TextureRegion;
import arc.scene.style.TextureRegionDrawable;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Regions {
    private static TextureRegion errorRegion = null;

    @Contract("null -> fail; _ -> new")
    public static @NotNull TextureRegionDrawable toDrawable(TextureRegion reg) {
        return new TextureRegionDrawable(Objects.requireNonNull(reg));
    }

    @Contract(pure = true)
    public static Texture textureOf(TextureRegion reg) {
        return Objects.requireNonNull(reg).texture;
    }

    @Contract(pure = true)
    public static @NotNull TextureRegion errorRegion() {
        if(errorRegion == null) {
            errorRegion = Core.atlas.find("error");
        }

        return errorRegion;
    }

    public static boolean hasRegion(String prefix) {
        return Core.atlas.has(prefix) || Core.atlas.has("octo-" + prefix);
    }

    public static @NotNull TextureRegion getRegion(String prefix) {
        if(StringUtils.isEmpty(prefix) || prefix.equals("error")) {
            return errorRegion();
        }

        TextureRegion reg = Core.atlas.find("octo-" + prefix, Core.atlas.find(prefix));
        return reg == null ? errorRegion() : reg;
    }
}