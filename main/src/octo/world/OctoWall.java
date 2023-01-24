package octo.world;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;

import mindustry.gen.Building;
import mindustry.world.blocks.defense.Wall;

import octo.util.Regions;

public class OctoWall extends Wall {
    public TextureRegion[] joints = new TextureRegion[5];
    public boolean jointsEnabled = false;

    public OctoWall(String name) {
        super(name);
        this.flashColor = Color.white;
    }

    @Override
    public void load() {
        super.load();

        if(this.jointsEnabled) {
            for(int i = 0; i < this.joints.length; i++) {
                this.joints[i] = Regions.getRegion(this.name + i);
            }
        }
    }

    public class OctoWallBuilding extends Building {
        public void draw(int id, float rot) {
            Draw.rect(joints[id], this.x, this.y, rot);
            this.drawTeamTop();
        }

        @Override
        public void draw() {
            if(!jointsEnabled) {
                super.draw();
                return;
            }

            boolean left, right, top, bottom;
            int bTop    = (top    = this.jointEnabled(this.nearby(0,  1))) ? 1 : 0;
            int bBottom = (bottom = this.jointEnabled(this.nearby(0, -1))) ? 1 : 0;
            int bLeft   = (left   = this.jointEnabled(this.nearby(1,  0))) ? 1 : 0;
            int bRight  = (right  = this.jointEnabled(this.nearby(-1, 0))) ? 1 : 0;

            Draw.rect(region, this.x, this.y, this.drawrot());
            if((top && bottom && !left && !right) || (left && right && !top && !bottom)) {
                this.draw(4, left ? 0 : 90);
            } else {
                switch(bTop + bBottom + bLeft + bRight) {
                    case 1 -> this.draw(0, top ? -90 : (bottom ? 90 : (left ? 180 : 0)));
                    case 2 -> this.draw(1, (right ? 0 : 180) + (top ? (right ? -90 : 0) : (right ? 0 : -90)));
                    case 3 -> this.draw(2, top && bottom ? (left ? 90 : -90) : (top ? 180 : 0));
                    case 4 -> this.draw(3, 0);
                    //join 0 draw == super.draw() method
                    default -> super.draw();
                }
            }
        }

        public boolean jointEnabled(Building building) {
            return building != null && building.block == OctoWall.this;
        }
    }
}