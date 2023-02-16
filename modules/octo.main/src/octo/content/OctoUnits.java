package octo.content;

import mindustry.content.Fx;
import mindustry.content.UnitTypes;
import mindustry.type.UnitType;
import mindustry.type.Weapon;

import octo.core.util.unit.XeonUnitType;
import octo.core.util.unit.XeonUnits;
import octo.core.graphics.Outliner;

import octo.type.HelicopterUnitType;
import octo.type.HelicopterEntity;
import octo.type.Rotor;

public class OctoUnits {
    public static @SuppressWarnings("unused") UnitType
            type10, sehnsucht, ichWill, weissesFleisch,
    end;

    static {
        XeonUnits.add(HelicopterEntity.class, HelicopterEntity::new);
    }

    public static void load() {
        XeonUnitType.outliner = Outliner::outlineRegion;
        XeonUnits.setupID();

        sehnsucht = new HelicopterUnitType("sehnsucht") {{
            this.speed = 4.0f;
            this.accel = 0.7f;
            this.drag = 0.04f;

            this.health = 200;
            this.rotateSpeed = 7;
            this.hitSize = 8;

            this.rotors.add(new Rotor("green-rotor") {{
                this.rotorLayer = -this.rotorLayer;
                this.drawTop = false;
                this.scl = 0.5f;
            }});

            this.weapons.add(new Weapon("heal-weapon") {{
                this.reload = 15;
                this.y = -1;
                this.x = 4;

                this.layerOffset = -1;
                this.bullet = UnitTypes.nova.weapons.get(0).bullet;
            }});
        }};

        type10 = new HelicopterUnitType("type10") {{
            this.speed = 2.7f;
            this.accel = 0.8f;
            this.drag = 0.04f;

            this.health = 160;
            this.rotateSpeed = 6f;
            this.hitSize = 8 * 4;

            this.rotors.addAll(
                    new Rotor("orange-rotor") {{
                        this.y = 4;
                    }},

                    new Rotor("orange-rotor") {{
                        this.rotorLayer = -this.rotorLayer;
                        this.drawTop = false;
                        this.scl /= 2f;
                        this.y = -16;
                    }}
            );

            this.weapons.addAll(
                    new Weapon("zenith-missiles") {{
                        this.y = -4;
                        this.x = 8;
                        this.reload = 15;
                        this.bullet = OctoBullets.type10missile;
                    }},

                    new Weapon("artillery") {{
                        this.reload = 30;
                        this.y = 8;
                        this.x = 6;

                        this.layerOffset = -1;
                        this.bullet = OctoBullets.type10laser;
                    }}
            );
        }};

        weissesFleisch = new HelicopterUnitType("weisses-fleisch") {{
            this.speed = 1.7f;
            this.accel = 0.6f;
            this.drag = 0.04f;

            this.health = 360;
            this.rotateSpeed = 4f;
            this.hitSize = 8 * 4.5f;

            this.rotors.addAll(
                    new Rotor("orange-rotor") {{
                        this.y = 4;
                        this.x = 8;
                    }},

                    new Rotor("orange-rotor") {{
                        this.y = 4;
                        this.x = -8;
                    }},

                    new Rotor("orange-rotor") {{
                        this.rotorLayer = -this.rotorLayer;
                        this.drawTop = false;
                        this.scl /= 2f;
                        this.y = -16;
                    }}
            );

            this.weapons.addAll(
                    new Weapon("zenith-missiles") {{
                        this.y = -2;
                        this.x = 8;
                        this.reload = 10;
                        this.bullet = OctoBullets.type10missile;
                    }},

                    new Weapon("zenith-missiles") {{
                        this.y = -6;
                        this.x = 12;
                        this.reload = 10;
                        this.bullet = OctoBullets.type10missile;
                    }},

                    new Weapon("artillery") {{
                        this.reload = 15;
                        this.y = 8;
                        this.x = 6;

                        this.layerOffset = -1;
                        this.bullet = OctoBullets.type10laser;
                    }},

                    new Weapon("artillery") {{
                        this.reload = 15;
                        this.y = 6;
                        this.x = 14;

                        this.layerOffset = -1;
                        this.bullet = OctoBullets.type10laser;
                    }}
            );
        }};

        ichWill = new HelicopterUnitType("ich-will") {{
            this.speed = 1.4f;
            this.accel = 0.4f;
            this.drag = 0.04f;

            this.health = 660;
            this.rotateSpeed = 3f;
            this.hitSize = 48;

            this.rotors.addAll(
                    new Rotor("orange-rotor") {{
                        this.y = 16;
                        this.x = 16;
                    }},

                    new Rotor("orange-rotor") {{
                        this.y = 16;
                        this.x = -16;
                    }},

                    new Rotor("orange-rotor") {{
                        this.y = -12;
                        this.x = 10;
                    }},

                    new Rotor("orange-rotor") {{
                        this.y = -12;
                        this.x = -10;
                    }}
            );
        }};
    }
}