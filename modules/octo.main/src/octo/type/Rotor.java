package octo.type;

import arc.func.Prov;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;

import arc.math.Angles;
import mindustry.gen.Unit;
import mindustry.graphics.Drawf;
import octo.core.graphics.Drawl;
import octo.core.graphics.Regions;
import org.jetbrains.annotations.Contract;

import java.util.Objects;

public class Rotor {
    public Prov<RotorMount> constructor;
    public final String name;

    public TextureRegion
        bladeRegion,
        bladeBlurRegion,
        topRegion;

    public float
            x = 0,
            y = 0,
            rotorSpeed = 12,
            minimumRotorSpeed = 3,
            rotorLayer = 0.5f,
            topLayer = 1f,
            rotorBlurAlpha = 0.8f;

    public boolean drawTop = true;
    public int blades = 4;

    @Contract(pure = true)
    public Rotor(String name) {
        this.name = name;
        this.constructor = RotorMount::new;
    }

    public void load() {
        this.bladeRegion = Regions.getRegion(this.name);
        this.bladeBlurRegion = Regions.getRegion(this.name + "-blur", this.bladeRegion);
        this.topRegion = Regions.getRegion(this.name + "-top");
    }

    public RotorMount createMount() {
        return this.constructor.get();
    }

    public class RotorMount {
        public float rotorSpeed;
        public float rotorRotation;

        @Contract(pure = true)
        public RotorMount() {
            this.rotorSpeed = Rotor.this.rotorSpeed;
        }

        public void update(Unit unit) {
            Objects.requireNonNull(unit);

            if((unit.dead || unit.health <= 0) && this.rotorSpeed > minimumRotorSpeed) {
                this.rotorSpeed--;
            }

            this.rotorRotation = (this.rotorRotation + this.rotorSpeed) % 360;
        }

        public void draw(Unit unit) {
            Objects.requireNonNull(unit);

            float x = unit.x + Angles.trnsx(unit.rotation - 90, Rotor.this.x, Rotor.this.y);
            float y = unit.y + Angles.trnsy(unit.rotation - 90, Rotor.this.x, Rotor.this.y);

            Drawl.lRes(rotorLayer);
            Draw.alpha(rotorBlurAlpha);
            for(float angle = 0; angle < 360; angle += (360f / blades)) {
                Draw.rect(bladeBlurRegion, x, y, angle + rotorRotation);
            }

            Drawl.lRes(-rotorLayer + topLayer);
            if(drawTop) {
                Draw.alpha(1);
                Drawf.spinSprite(topRegion, x, y, unit.rotation);
            }

            Drawl.lRes(-topLayer);
            Draw.reset();
        }
    }
}