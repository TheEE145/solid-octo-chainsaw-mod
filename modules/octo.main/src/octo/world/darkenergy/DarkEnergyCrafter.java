package octo.world.darkenergy;

import arc.Core;
import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.gen.Building;
import mindustry.ui.Bar;
import mindustry.world.blocks.production.GenericCrafter;
import octo.type.darkenergy.DarkEnergy;
import octo.type.darkenergy.DarkEnergyBlock;
import octo.type.darkenergy.DarkModule;
import octo.type.darkenergy.i.ConnectableDark;
import octo.type.darkenergy.i.IDarkEnergyBlock;
import octo.type.darkenergy.i.IDarkEnergyBuild;

public class DarkEnergyCrafter extends GenericCrafter
        implements IDarkEnergyBlock
{
    public float darkEnergyProduce = 0;

    public DarkEnergyCrafter(String name) {
        super(name);
    }

    public void consumeDark(float amountPerTick) {
        DarkEnergy.consume(this, amountPerTick);
    }

    @Override
    public void setBars() {
        super.setBars();

        this.addBar("dark", (DarkEnergyCrafterBuild build) -> new Bar(
                () -> Core.bundle.get("bar.energy.dark"),
                () -> Color.purple, build.module::progress
        ));
    }

    public class DarkEnergyCrafterBuild extends GenericCrafterBuild
            implements IDarkEnergyBuild, ConnectableDark
    {
        public DarkModule module = new DarkModule();

        public float darkEnergyProduce() {
            return darkEnergyProduce * this.efficiency;
        }

        @Override
        public void updateTile() {
            this.updateTileCall();

            this.getModule().energy += this.darkEnergyProduce();
        }

        @Override
        public DarkModule getModule() {
            return module;
        }

        @Override
        public Building getThis() {
            return this;
        }
    }
}