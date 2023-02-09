package octo.core.util.unit;

import arc.graphics.Color;
import arc.math.Mathf;
import org.jetbrains.annotations.NotNull;

import mindustry.gen.Unit;
import mindustry.graphics.Layer;
import mindustry.type.UnitType;

public class XeonUnitType extends UnitType {
    public XeonUnitType(String name) {
        super(name);

        this.outlineColor = Color.valueOf("4A4B53");
    }

    public float unitLayer(@NotNull Unit unit) {
        return unit.elevation > 0.5f ? (this.lowAltitude ? Layer.flyingUnitLow : Layer.flyingUnit) :
                this.groundLayer + Mathf.clamp(this.hitSize / 4000f, 0, 0.01f);
    }
}