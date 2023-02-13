package octo.type.darkenergy;

import arc.Core;
import arc.struct.Seq;
import arc.graphics.Color;

import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.ui.Bar;

import octo.core.util.Log;
import octo.type.NonStatic;
import octo.type.darkenergy.i.ConnectableDark;
import octo.type.darkenergy.i.IDarkEnergyBlock;
import octo.type.darkenergy.i.IDarkEnergyBuild;

public class DarkEnergyBlock extends Block implements IDarkEnergyBlock {
    public DarkEnergyBlock(String name) {
        super(name);

        this.destructible = this.solid =
                this.update = true;
    }

    @Override
    public void setBars() {
        super.setBars();

        this.addBar("dark", (DarkEnergyBuild build) -> new Bar(
                () -> Core.bundle.get("bar.energy.dark"),
                () -> Color.purple, build.module::progress
        ));
    }

    public @NonStatic class DarkEnergyBuild extends Building
            implements IDarkEnergyBuild, ConnectableDark
    {
        public DarkModule module = new DarkModule();

        @Override
        public void updateTile() {
            this.updateTileCall();
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