package octo.world.darkenergy;

import octo.type.darkenergy.DarkEnergyBlock;
import octo.type.darkenergy.DarkModule;

public class DarkEnergyHole extends DarkEnergyBlock {
    public DarkEnergyHole(String name) {
        super(name);
    }

    @Override
    public void setBars() {
        super.setBars();

        this.removeBar("dark");
    }

    public class DarkEnergyHoleBuild extends DarkEnergyBuild {
        @Override
        public DarkModule getModule() {
            return new DarkModule().setEnergyAndReturn(Float.NEGATIVE_INFINITY);
        }
    }
}