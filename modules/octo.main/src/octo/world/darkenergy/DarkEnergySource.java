package octo.world.darkenergy;

import octo.type.darkenergy.DarkEnergyBlock;
import octo.type.darkenergy.DarkModule;

public class DarkEnergySource extends DarkEnergyBlock {
    public DarkEnergySource(String name) {
        super(name);
    }

    @Override
    public void setBars() {
        super.setBars();

        this.removeBar("dark");
    }

    public class DarkEnergySourceBuild extends DarkEnergyBuild {
        @Override
        public DarkModule getModule() {
            return new DarkModule().setEnergyAndReturn(DarkModule.limit);
        }
    }
}