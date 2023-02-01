package octo.util;

import mindustry.gen.Building;
import arc.math.geom.Vec2;
import arc.struct.Seq;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static mindustry.Vars.*;

public class BlockUtils {
    @Contract(value = "null -> null", pure = true)
    public static @Nullable Vec2 to_vec2(Building building) {
        if(building == null || building.block == null) {
            return null;
        }

        return new Vec2(building.x, building.y);
    }

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