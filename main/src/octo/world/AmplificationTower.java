package octo.world;

import arc.util.Time;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;

import mindustry.world.Block;
import mindustry.gen.Building;
import mindustry.graphics.Layer;
import mindustry.world.blocks.logic.LogicBlock;
import mindustry.world.blocks.defense.turrets.Turret;

import mindustry.world.meta.Stat;
import octo.util.BlockUtils;

import static mindustry.Vars.renderer;

public class AmplificationTower extends Block {
    public float turretReloadSpeed = 0.5f;
    public float range = 40;

    public int processorSpeed = 2;
    public int maxStroke = 6;

    public AmplificationTower(String name) {
        super(name);

        this.solid = this.hasShadow = this.squareSprite = false;
        this.destructible = this.underBullets = this.update = true;
    }

    @Override
    public void setStats() {
        super.setStats();

        this.stats.addPercent(Stat.boostEffect, turretReloadSpeed);
        this.stats.add(Stat.productionTime, processorSpeed);
    }

    public class AmplificationTowerBuild extends Building implements Ranged {
        @Override
        public void updateTile() {
            if(this.isActive()) {
                BlockUtils.buildingsNearby(this, range).each(building -> {
                    if(building != null) {
                        if(building instanceof Turret.TurretBuild build && build.isShooting()) {
                            build.reloadCounter += turretReloadSpeed;
                        }

                        if(building instanceof LogicBlock.LogicBuild build) {
                            for(int i = 0; i < processorSpeed; i++) {
                                build.updateTile();
                            }
                        }
                    }
                });
            }
        }

        public boolean isActive() {
            return this.enabled();
        }

        @Override
        public void draw() {
            super.draw();

            if(this.isActive()) {
                Draw.draw(Layer.shields, () -> {
                    Draw.color(Color.red);

                    if(!renderer.animateShields) {
                        Draw.alpha(0.25f);
                    }

                    Fill.circle(this.x, this.y, range);

                    if(!renderer.animateShields) {
                        Draw.color(Color.red);
                        Lines.stroke(2);

                        Lines.circle(this.x, this.y , range);
                    }
                });

                Draw.draw(Layer.shields + 1, () -> {
                    float progress = Time.globalTime % 180;

                    if(progress > 60) {
                        progress = 1f - ((progress - 60) / 120);

                        Draw.color(Color.white);
                        Draw.alpha(progress);
                        Lines.stroke(progress * maxStroke);

                        Lines.circle(this.x, this.y, range * (1 - progress));
                    }
                });

                Draw.reset();
            }
        }

        @Override
        public float range() {
            return range;
        }
    }
}