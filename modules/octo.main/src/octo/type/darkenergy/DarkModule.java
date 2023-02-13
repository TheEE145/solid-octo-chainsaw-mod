package octo.type.darkenergy;

import arc.util.io.Reads;
import arc.util.io.Writes;

import mindustry.gen.Building;
import mindustry.world.modules.BlockModule;
import octo.core.util.Time;

import java.util.Objects;

public class DarkModule extends BlockModule {
    public static final float limit = 100;
    public float energy = 0;

    public DarkModule() {
        super();
    }

    public void fix() {
        if(this.fixed()) {
            return;
        }

        this.energy = Math.max(0, this.energy);
    }

    public void update(Building building) {
        if(this.energy > limit && Time.every(3)) {
            building.damage(this.energy / limit);
        }
    }

    public DarkModule setEnergyAndReturn(float energy) {
        this.energy = energy;
        return this;
    }

    public boolean fixed() {
        return this.energy > 0;
    }

    public float progress() {
        return this.energy / limit;
    }

    @Override
    public void write(Writes write) {
        Objects.requireNonNull(write).f(this.energy);
    }

    @Override
    public void read(Reads read) {
        this.energy = Objects.requireNonNull(read).f();
    }
}