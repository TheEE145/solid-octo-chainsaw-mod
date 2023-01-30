package octo.world;

import arc.util.Time;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;

import mindustry.world.Block;
import mindustry.gen.Building;
import mindustry.graphics.Layer;
import mindustry.world.Tile;
import mindustry.world.blocks.logic.LogicBlock;
import mindustry.world.blocks.defense.turrets.Turret;

import octo.util.BlockUtils;

public class AmplificationTower extends Block {
    public float turretReloadSpeed = 2.5f;
    public float towerAlpha = 0.25f;
    public float range = 40;

    public int processorSpeed = 2;
    public int maxStroke = 3;

    public AmplificationTower(String name) {
        super(name);

        this.destructible = this.underBullets = true;
        this.solid = this.hasShadow = this.squareSprite = false;
    }

    @Override
    public void drawShadow(Tile tile) {
    }

    public class AmplificationTowerBuild extends Building implements Ranged {
        @Override
        public void updateTile() {
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

        @Override
        public void draw() {
            super.draw();

            Draw.draw(Layer.shields - 1, () -> {
                Draw.color(Color.red);
                Draw.alpha(towerAlpha);

                Fill.circle(this.x, this.y, range);
                float progress = Time.globalTime % 180;

                if(progress > 60) {
                    progress = 1f - ((progress - 60) / 120);

                    Draw.color(Color.white);
                    Draw.alpha(progress);
                    Lines.stroke(progress * maxStroke);

                    Lines.circle(this.x, this.y, range * (1 - progress));
                }
            });
        }

        @Override
        public float range() {
            return range;
        }
    }
}