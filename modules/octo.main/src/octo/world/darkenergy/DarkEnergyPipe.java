package octo.world.darkenergy;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import octo.core.graphics.Regions;
import octo.core.graphics.Splitter;
import octo.core.util.BlockUtils;
import octo.type.darkenergy.DarkEnergy;
import octo.type.darkenergy.DarkEnergyBlock;
import octo.type.darkenergy.i.ConnectableDark;

public class DarkEnergyPipe extends DarkEnergyBlock {
    public TextureRegion[] joints = new TextureRegion[16];

    public DarkEnergyPipe(String name) {
        super(name);

        this.underBullets = true;
        this.squareSprite = this.targetable =
                this.solid = false;
    }

    @Override
    public void load() {
        super.load();

        this.joints = Splitter.getRegions(Regions.getRegion(this.name + "-sheet"), 4, 4, 32);
    }

    public class DarkEnergyPipeBuild extends DarkEnergyBuild {
        @Override
        public void draw() {
            Draw.rect(joints[BlockUtils.getJoint(this, (build -> {
                if(build != null) {
                    if(build instanceof ConnectableDark) {
                        return true;
                    }

                    return build.block == this.block ||
                            DarkEnergy.moduleOf(build) != null;
                }

                return false;
            }))], this.x, this.y);
            this.drawTeamTop();
        }
    }
}