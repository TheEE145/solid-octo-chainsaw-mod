package octo.type.darkenergy.i;

import arc.struct.Seq;
import mindustry.gen.Building;
import octo.type.darkenergy.DarkEnergy;
import octo.type.darkenergy.DarkModule;

public interface IDarkEnergyBuild {
    DarkModule getModule();
    Building getThis();

    default void updateTileCall() {
        DarkModule module = this.getModule();
        module.fix();

        Building this2 = this.getThis();
        Seq<DarkModule> moduleSeq = new Seq<>();

        for (Building building : this2.proximity) {
            if (building == this) {
                return;
            }

            DarkModule module2 = DarkEnergy.moduleOf(building);
            if (module2 != null) moduleSeq.add(module2);
        }

        moduleSeq.add(module);
        float delta = moduleSeq.sumf(mod -> mod.energy) / moduleSeq.size;
        moduleSeq.each(mod -> mod.setEnergyAndReturn(delta).fix());
        module.update(this2);
    }
}