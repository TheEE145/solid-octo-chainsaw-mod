package octo.type.darkenergy;

import arc.func.Func;
import mindustry.gen.Building;
import mindustry.world.consumers.Consume;

public class ConsumeDarkEnergy extends Consume {
    public Func<Building, DarkModule> moduleFunc = (ignored) -> this.emergency;
    public DarkModule emergency = new DarkModule();
    public float consume;

    public ConsumeDarkEnergy(float consume) {
        this.consume = consume;
    }

    @Override
    public float efficiency(Building build) {
        DarkModule module = this.moduleFunc.get(build);

        if(module != null) {
            if(module.energy > 0) {
                return module.energy / this.consume;
            }
        }

        return 0;
    }

    @Override
    public void update(Building build) {
        DarkModule module = this.moduleFunc.get(build);

        if(module != null) {
            if(module.energy > 0) {
                module.energy = Math.max(0, module.energy - this.consume);
            }
        }
    }
}