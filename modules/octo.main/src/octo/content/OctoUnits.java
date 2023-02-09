package octo.content;

import mindustry.type.UnitType;

import octo.core.graphics.Outliner;
import octo.core.util.unit.XeonUnitType;
import octo.type.HelicopterUnitType;
import octo.type.Rotor;

public class OctoUnits {
    public static @SuppressWarnings("unused") UnitType
            type10,
    end;

    public static void load() {
        XeonUnitType.outliner = Outliner::outlineRegion;

        type10 = new HelicopterUnitType("type10") {{
            this.speed = 2.7f;
            this.accel = 0.8f;
            this.drag = 0.04f;

            this.health = 160;
            this.rotateSpeed = 6f;

            this.rotors.addAll(
                    new Rotor("type10-rotor") {{
                        this.y = 4;
                    }},

                    new Rotor("type10-rotor") {{
                        this.y = -16;
                        this.scl /= 2f;
                        this.drawTop = false;
                        this.rotorLayer = -5.0f;
                    }}
            );
        }};
    }
}