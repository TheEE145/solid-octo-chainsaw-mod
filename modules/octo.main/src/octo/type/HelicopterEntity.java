package octo.type;

import arc.struct.Seq;
import octo.core.util.unit.XeonUnitEntity;

public class HelicopterEntity extends XeonUnitEntity {
    public final Seq<Rotor.RotorMount> rotors = new Seq<>();

    @Override
    public void load() {
        if(type instanceof HelicopterUnitType helicopterUnitType) {
            helicopterUnitType.rotors.forEach(rotor -> {
                this.rotors.add(rotor.createMount());
            });
        }
    }

    @Override
    public void update() {
        super.update();

        if(this.dead || this.health <= 0) {
            this.rotation++;
        }

        this.rotors.each(rotorMount -> {
            if(rotorMount != null) {
                rotorMount.update(this);
            }
        });
    }
}