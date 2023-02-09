package octo.content;

import arc.Core;
import arc.graphics.Color;
import mindustry.Vars;
import mindustry.content.Items;
import mindustry.ctype.UnlockableContent;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import mindustry.type.Liquid;
import octo.Octo;
import octo.core.graphics.AnimatedTextureRegion;

public class OctoItems {
    public static @SuppressWarnings("unused") Item
        octoMat,
        soul,
        monolith,
        researchStruct,
        octoMatAlloy,
        pentagonium,
        chargedPentagonium,
        silver,
        steel,
        slime,
        substance43,
        warlockCube,
        magmaCube,
        gold,
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

        octoMatAlloy = new Item("octo-mat-alloy", Pal.heal) {{
            this.radioactivity = 0.75f;
            this.charge = 1f;
        }};

        gold = new Item("gold", Color.yellow) {{
            this.charge = 350;
        }};

        magmaCube = new Item("magma-cube", Color.red) {{
            this.charge = 2f;

            this.flammability = 100f;
            this.explosiveness = 50f;
        }};

        pentagonium = new Item("pentagonium", Items.sporePod.color) {{
            this.radioactivity = 560;
            this.charge = 600;

            this.flammability = 200;
        }};

        silver = new Item("silver", Color.gray) {{
            this.charge = 0;
            this.radioactivity = 0;
            this.flammability = 0;
            this.explosiveness = 0.25f;
        }};

        slime = new Item("slime", Color.green) {{
            this.charge = 0;
            this.radioactivity = 1;
            this.flammability = 2;
            this.explosiveness = 0.25f;
        }};

        steel = new Item("steel", Color.gray) {{
            this.charge = 0;
            this.radioactivity = 0;
            this.flammability = 0;
            this.explosiveness = 0;
        }};

        substance43 = new Item("substance43", Items.sporePod.color) {{
            this.charge = 0.25f;
            this.radioactivity = 500;
            this.flammability = 200;
            this.explosiveness = 100;
        }};

        warlockCube = new Item("warlock-cube", Color.cyan) {{
            this.charge = 100;
            this.radioactivity = 0.10f;
            this.explosiveness = 2;
            this.flammability = -500f;
        }};

        chargedPentagonium = new Item("charged-pentagonium", Color.yellow) {{
            this.charge = 1670;
            this.explosiveness = 5;
            this.radioactivity = 880;
            this.flammability = 200;
        }};

        end = new Item("end", Color.white) {{
            this.charge =
                    this.flammability =
                            this.radioactivity =
                                    this.explosiveness =
                                            Float.POSITIVE_INFINITY;

            this.alwaysUnlocked = true;
        }};

        if(!Octo.betamindyModule.validMod()) {
            researchStruct = new Item("research-struct", Color.white) {{
                this.charge = -999;
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
        if(content == null || Vars.headless) {
            return;
        }

        AnimatedTextureRegion reg = AnimatedTextureRegion.load(content.name);
        reg.maxTick += bonusTick;

        if(Core.settings.getBool("animitems")) {
            reg.updateNext();
        } else {
            for(int i = 0; i < reg.frames.length / 2; i++) {
                reg.nextFrame();
            }
        }

        content.fullIcon = reg;
        content.uiIcon = reg;
    }

    public static void loadAnimated() {
        //region items

        load(octoMat, 2);
        load(soul, 6);
        load(monolith, 0);
        load(chargedPentagonium, 2);
        load(end, -2);

        //end items
        //region liquids

        load(soulLiquid, 5);

        //end liquids
    }
}