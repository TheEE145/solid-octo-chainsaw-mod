package octo.core.util.unit;

import arc.Events;
import arc.struct.Seq;
import mindustry.game.EventType;
import mindustry.gen.Groups;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused") //used class
public class XeonUnits {
    public static @NotNull Seq<XeonUnitEntity> units() {
        Seq<XeonUnitEntity> units = new Seq<>();

        if(Groups.unit == null) {
            return units;
        }

        Groups.unit.each(unit -> {
            try {
                units.add((XeonUnitEntity) unit);
            } catch(ClassCastException exception) {
                //nothing
            }
        });

        return units;
    }

    static {
        Events.run(EventType.Trigger.update, () -> {
            units().each(xeon -> {
                if(!xeon.loaded) {
                    xeon.load();
                    xeon.loaded = true;
                }
            });
        });
    }
}