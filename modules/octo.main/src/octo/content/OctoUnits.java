package octo.content;

import mindustry.type.UnitType;

import mindustry.type.Weapon;
import octo.core.graphics.Outliner;
import octo.core.util.unit.XeonUnitType;
import octo.core.util.unit.XeonUnits;
import octo.type.HelicopterEntity;
import octo.type.HelicopterUnitType;
import octo.type.Rotor;

public class OctoUnits {
    public static @SuppressWarnings("unused") UnitType
            type10,
    end;

    static {
        XeonUnits.add(HelicopterEntity.class, HelicopterEntity::new);
    }

    public static void load() {
        XeonUnitType.outliner = Outliner::outlineRegion;
        XeonUnits.setupID();

        type10 = new HelicopterUnitType("type10") {{
            this.speed = 2.7f;
            this.accel = 0.8f;
            this.drag = 0.04f;

            this.health = 160;
            this.rotateSpeed = 6f;
            this.hitSize = 8 * 3;

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

            this.weapons.add(new Weapon("zenith-missiles") {{
                this.y = -4;
                this.x = 8;

                this.reload = 30;
                this.bullet = OctoBullets.type10missile;
            }});
        }};
    }
}