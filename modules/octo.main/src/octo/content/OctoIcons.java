package octo.content;

import arc.scene.style.TextureRegionDrawable;
import static octo.core.graphics.ModIcons.loadIcon;

public class OctoIcons {
    public static TextureRegionDrawable mod;

    public static void load() {
        mod = loadIcon("icon-mod");
    }
}