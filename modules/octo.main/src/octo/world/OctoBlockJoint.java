package octo.world;

import mindustry.gen.Building;
import mindustry.gen.Bullet;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.meta.Stat;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;

import octo.core.graphics.Regions;
import octo.core.util.Time;

public class OctoBlockJoint extends Block {
    public TextureRegion mirrorRegion = null;
    public StatusEffect damageStatus;
    public boolean mirror = false;

    public boolean bulletCollides = true;
    public boolean isSpike = false;
    public float spikeDamage = 0;

    public OctoBlockJoint(String name) {
        super(name);

        this.destructible = true;
        this.solid = this.hasShadow = false;
        this.rotate = this.rotateDraw = this.quickRotate = true;
    }

    @Override
    public void drawShadow(Tile tile) {
    }

    @Override public void load() {
        super.load();

        if(this.mirror) {
            this.mirrorRegion = Regions.getRegion(name + "-mirrored");
        }
    }

    @Override public void setStats() {
        super.setStats();

        if(this.isSpike) {
            this.stats.add(Stat.damage, this.spikeDamage);
        }
    }

    public class OctoBlockJointBuild extends Building {
        @Override public boolean collide(Bullet other) {
            return bulletCollides && this.isActive();
        }

        @Override public void unitOn(Unit unit) {
            if(this.isActive()) {
                if(unit != null && !unit.isFlying() && isSpike && Time.every(30)) {
                    unit.damage(spikeDamage);

                    if(damageStatus != null) {
                        unit.apply(damageStatus, 240);
                    }
                }

                this.unitTouch(unit);
            }
        }

        @Override public void draw() {
            Draw.alpha(this.isActive() ? 1f : 0.5f);

            TextureRegion reg = region;
            if(this.rotation == 2 || this.rotation == 1) {
                reg = mirrorRegion;
            }

            Draw.rect(reg, this.x, this.y, this.drawrot());
            this.drawTeamTop();
        }

        public boolean isActive() {
            Building build = this.nearby(this.rotation);
            return build != null && !(build instanceof OctoBlockJointBuild) && build.block.solid;
        }

        public void unitTouch(Unit ignored) {
        }
    }
}