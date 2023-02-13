package octo.world;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;

import mindustry.gen.Building;
import mindustry.world.blocks.defense.Wall;

import octo.core.graphics.Regions;
import octo.core.graphics.Splitter;
import octo.core.util.BlockUtils;

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
        @Override
        public void draw() {
            if(!jointsEnabled) {
                super.draw();
                return;
            }

            Draw.rect(joints[BlockUtils.getJoint(this, (build -> {
                return build != null && build.block == this.block;
            }))], this.x, this.y);
            this.drawTeamTop();
        }
    }
}