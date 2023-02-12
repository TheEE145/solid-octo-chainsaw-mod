package octo.world;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;

import mindustry.gen.Building;
import mindustry.world.blocks.defense.Wall;

import octo.core.graphics.Regions;
import octo.core.graphics.Splitter;

public class OctoWall extends Wall {
    public TextureRegion[] joints = new TextureRegion[16];
    public boolean jointsEnabled = false;

    public OctoWall(String name) {
        super(name);
        this.flashColor = Color.white;
    }

    @Override
    public void load() {
        super.load();

        if(this.jointsEnabled) {
            this.joints = Splitter.getRegions(Regions.getRegion(this.name + "-sheet"), 4, 4, 32);
        }
    }

    public class OctoWallBuilding extends Building {
        public byte reg;

        @Override
        public void draw() {
            if(!jointsEnabled) {
                super.draw();
                return;
            }

            this.reg = 0;
            for(int i = 0; i < 4; i++) {
                Building build = this.nearby(i);
                if(build != null && build.block == this.block) {
                    this.reg += 1 << i;
                }
            }

            Draw.rect(joints[this.reg], this.x, this.y);
            this.drawTeamTop();
        }

        public boolean jointEnabled(Building building) {
            return building != null && building.block == OctoWall.this;
        }
    }
}