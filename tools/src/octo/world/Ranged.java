package octo.world;

import arc.math.geom.Position;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;

public interface Ranged extends Position {
    float range();

    default void drawSelect() {
        Drawf.dashCircle(this.getX(), this.getY(), this.range(), Pal.place);
    }
}