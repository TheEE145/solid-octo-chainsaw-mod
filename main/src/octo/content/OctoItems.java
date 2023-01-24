package octo.content;

import mindustry.graphics.Pal;
import mindustry.type.Item;
import octo.util.AnimatedTextureRegion;

public class OctoItems {
    public static @SuppressWarnings("unused") Item
        octoMat,
    end;

    public static void load() {
        octoMat = new Item("octo-mat", Pal.heal) {{
            this.radioactivity = 0.5f;
            this.charge = 2f;
        }};
    }

    public static void loadAnimated() {
        octoMat.fullIcon = octoMat.uiIcon = AnimatedTextureRegion.load(octoMat.name, reg -> {
            reg.updateNext();
            reg.maxTick++;
        });
    }
}