package octo.type.darkenergy;

import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.world.consumers.Consume;

import octo.type.darkenergy.i.IDarkEnergyBuild;
import org.jetbrains.annotations.Contract;
import java.util.Objects;

public class DarkEnergy {
    @Contract("null -> fail")
    public static void fixModules(Block block) {
        ConsumeDarkEnergy[] root = new ConsumeDarkEnergy[1];

        for(Consume consume : Objects.requireNonNull(block).consumers) {
            if(consume instanceof ConsumeDarkEnergy darkEnergy) {
                if(root[0] == null) {
                    darkEnergy.moduleFunc = (building) -> {
                        if(building instanceof IDarkEnergyBuild) {
                            return ((IDarkEnergyBuild) building).getModule();
                        } else {
                            return darkEnergy.moduleFunc.get(building);
                        }
                    };

                    root[0] = darkEnergy;
                } else {
                    darkEnergy.moduleFunc = build -> {
                        return root[0].moduleFunc.get(build);
                    };
                }
            }
        }
    }

    @Contract("null, _ -> fail")
    public static void consume(Block block, float amountPefTick) {
        Objects.requireNonNull(block).consume(new ConsumeDarkEnergy(amountPefTick));
    }

    @Contract("null -> null")
    public static DarkModule moduleOf(Building building) {
        if(building == null) {
            return null;
        }

        if(building instanceof IDarkEnergyBuild build) {
            return build.getModule();
        } else {
            for(Consume consume : building.block.consumers) {
                if(consume instanceof ConsumeDarkEnergy energy) {
                    return energy.moduleFunc.get(building);
                }
            }

            return null;
        }
    }
}