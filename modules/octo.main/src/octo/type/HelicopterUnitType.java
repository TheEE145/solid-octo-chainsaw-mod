package octo.type;

import mindustry.gen.Unit;
import arc.struct.Seq;

import mindustry.graphics.MultiPacker;
import octo.core.util.unit.XeonUnitType;
import octo.core.graphics.Drawl;

public class HelicopterUnitType extends XeonUnitType {
    public final Seq<Rotor> rotors = new Seq<>();

    public HelicopterUnitType(String name) {
        super(name);

        this.constructor = HelicopterEntity::new;
        this.fallSpeed = 0.006f;
        this.engineSize = 0;
        this.flying = true;
    }

    public void drawRotor(Unit unit) {
        if(unit instanceof HelicopterEntity entity) {
            for(Rotor.RotorMount mount : entity.rotors) {
                if(mount != null) {
                    mount.draw(unit);
                }
            }
        }
    }

    @Override
    public void createIcons(MultiPacker packer) {
        super.createIcons(packer);

        this.rotors.forEach(rotor -> {
            this.outline(packer, rotor.topRegion, rotor.name + "-outline");
            this.outline(packer, rotor.topRegion, rotor.name + "-top-outline");
        });
    }

    @Override
    public void draw(Unit unit) {
        super.draw(unit);
        Drawl.l(this.unitLayer(unit));
        drawRotor(unit);
    }

    @Override
    public void load() {
        super.load();

        this.rotors.forEach(Rotor::load);
    }
}