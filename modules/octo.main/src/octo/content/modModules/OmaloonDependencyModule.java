package octo.content.modModules;

import mindustry.type.Category;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import octo.core.util.depedency.ModDependencyModule;
import octo.world.OctoBlockJoint;

import static mindustry.Vars.mods;

public class OmaloonDependencyModule extends ModDependencyModule {
    //region new

    public static @SuppressWarnings("unused") Block
            omaliteAlloySpike,
    end;

    //end new
    //region mod

    public static Item omaliteAlloy;

    //end mod
    //region override

    @Override
    public void init() {
        this.loadedMod = mods.getMod("ol");
    }

    @Override
    public void loadContent() {
        //region mod

        omaliteAlloy = this.itemFromMod("omalite-alloy");

        //end mod
        //region blocks

        omaliteAlloySpike = new OctoBlockJoint(this.prefix("omalite-alloy-spike")) {{
            this.mirror = true;
            this.isSpike = true;
            this.spikeDamage = 105;
            this.bulletCollides = false;
            this.block32 = () -> blockFromMod("omalite-alloy-wall");

            this.requirements(Category.defense, ItemStack.with(
                    omaliteAlloy, 7
            ));
        }};

        //end blocks
    }

    //end override
}