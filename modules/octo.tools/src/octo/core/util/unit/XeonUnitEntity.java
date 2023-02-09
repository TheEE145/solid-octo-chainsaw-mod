package octo.core.util.unit;

import mindustry.gen.UnitEntity;
import mindustry.type.UnitType;

public class XeonUnitEntity extends UnitEntity {
    public transient boolean loaded = false;

    public void load() {
    }

    @Override
    public void setType(UnitType type) {
        super.setType(type);

        if(!this.loaded) {
            this.load();
            this.loaded = true;
        }
    }
}