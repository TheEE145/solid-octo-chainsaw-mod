package octo.content;

import arc.graphics.Color;
import mindustry.content.Items;
import mindustry.ctype.UnlockableContent;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import mindustry.type.Liquid;
import octo.Octo;
import octo.util.AnimatedTextureRegion;

public class OctoItems {
    public static @SuppressWarnings("unused") Item
        octoMat,
        soul,
        monolith,
        researchStruct,
    end;

    public static @SuppressWarnings("unused") Liquid
            soulLiquid,
    endLiquid;

    public static void load() {
        //region items

        octoMat = new Item("octo-mat", Pal.heal) {{
            this.radioactivity = 0.5f;
            this.charge = 2f;
            this.alwaysUnlocked = true;
        }};

        soul = new Item("soul", Items.titanium.color) {{
            this.radioactivity = -1;
            this.charge = 5f;
        }};

        monolith = new Item("monolith", Pal.darkOutline) {{
            this.explosiveness = 5;
            this.radioactivity = 320;
            this.charge = 720;
        }};

        if(!Octo.betamindyModule.validMod()) {
            researchStruct = new Item("research-struct", Color.white) {{
                this.charge = -999;
                this.alwaysUnlocked = true;
            }};
        }

        //end items
        //region liquids

        soulLiquid = new Liquid("soul-liquid", Items.titanium.color) {{
            this.temperature = -2;
        }};

        //end liquids
    }

    public static void load(UnlockableContent content, int bonusTick) {
        if(content == null) {
            return;
        }

        AnimatedTextureRegion reg = AnimatedTextureRegion.load(content.name).updateNext();
        reg.maxTick += bonusTick;

        content.fullIcon = reg;
        content.uiIcon = reg;
    }

    public static void loadAnimated() {
        //region items

        load(octoMat,  2);
        load(soul,     6);
        load(monolith, 0);

        //end items
        //region liquids

        load(soulLiquid, 5);

        //end liquids
    }
}