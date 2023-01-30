package octo.util;

import arc.struct.Seq;
import arc.util.Log;
import mindustry.gen.Building;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static mindustry.Vars.*;

public class BlockUtils {
    @Contract("null, _ -> new")
    public static @NotNull Seq<Building> buildingsNearby(Building building, float range) {
        if(building == null || Float.isNaN(range)) {
            return new Seq<>();
        }

        Seq<Building> result = new Seq<>();

        int tx = building.tileX();
        int ty = building.tileY();
        int tl = (int) (range / 4);

        for(int x = tx - tl; x < tx + tl; x++) {
            if(x < 0 || x > world.width()) {
                continue;
            }

            for(int y = ty - tl; y < ty + tl; y++) {
                if(y < 0 || y > world.height()) {
                    continue;
                }

                Building build = world.build(x, y);
                if(build != null && DefMath.collision(building, build, range)) {
                    result.add(build);
                }
            }
        }

        return result;
    }
}