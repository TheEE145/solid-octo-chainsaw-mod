package octo.content.modModules;

import mindustry.type.Category;
import mindustry.type.Item;
import mindustry.type.ItemStack;

import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;

import octo.content.OctoItems;
import octo.util.depedency.ModDependencyModule;
import octo.world.OctoBlockJoint;

import static mindustry.Vars.mods;
import static octo.util.TechTreeUtils.*;

public class BetamindyDependencyModule extends ModDependencyModule {
    //region new

    public static Block
            bittriumWall,
            bittriumWallLarge,
            bittriumSpike,
    end;

    //end new
    //region mod

    public static Item bittrium;

    //end mod

    public BetamindyDependencyModule() {
        super(null);
    }

    @Override
    public void init() {
        this.loadedMod = mods.getMod("betamindy");
    }

    @Override
    public void preload() {
        OctoItems.researchStruct = this.itemFromMod("source");
    }

    @Override
    public void loadContent() {
        //region mod

        bittrium = this.itemFromMod("bittrium");

        //end mod
        //region blocks

        bittriumWall = new Wall(this.prefix("bittrium-wall")) {{
            this.health = 4040;

            this.requirements(Category.defense, ItemStack.with(
                    bittrium, 6
            ));
        }};

        bittriumWallLarge = new Wall(this.prefix("bittrium-wall-large")) {{
            this.health = 16160;
            this.size = 2;

            this.requirements(Category.defense, ItemStack.with(
                    bittrium, 24
            ));
        }};

        bittriumSpike = new OctoBlockJoint(this.prefix("bittrium-spike")) {{
            this.mirror = true;
            this.isSpike = true;
            this.spikeDamage = 235;
            this.bulletCollides = false;

            this.requirements(Category.defense, ItemStack.with(
                    bittrium, 7
            ));
        }};

        //end blocks
        //region tech

        margeNode(this.blockFromMod("cryo-wall"), () -> {
            node(bittriumWall, () -> {
                node(bittriumWallLarge);
                node(bittriumSpike);
            });
        });

        margeNode(this.itemFromMod("cryonite"), () -> {
            node(bittrium);
        });

        //end tech
    }
}