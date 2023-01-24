package octo.content;

import arc.graphics.Color;
import mindustry.content.Blocks;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.bullet.LaserBoltBulletType;
import mindustry.entities.pattern.ShootHelix;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.blocks.defense.turrets.Turret;
import octo.gen.ModSounds;
import octo.world.OctoWall;

public class OctoBlocks {
    public @SuppressWarnings("unused") static Block
            //turrets
            octoChar,

            //walls
            octoMatWall,
    end;

    public static void load() {
        //region turrets

        octoChar = new PowerTurret("octo-char") {{
            this.health = Blocks.scatter.health;
            this.range = ((Turret) Blocks.scatter).range;
            this.shootSound = ModSounds.laserShoot;
            this.shootEffect = Fx.none;
            this.reload = 20;
            this.size = 2;

            this.shoot = new ShootHelix() {{
                this.offset = 4;
                this.shots = 3;
            }};

            this.shootType = new LaserBoltBulletType(6, 10) {{
                this.status = StatusEffects.electrified;
                this.backColor = Color.green;
                this.frontColor = Pal.heal;
            }};

            this.consumePower(2);
            this.requirements(Category.turret, ItemStack.with(
                    OctoItems.octoMat, 24
            ));
        }};

        //end turrets
        //region walls

        octoMatWall = new OctoWall("octo-mat-wall") {{
            this.jointsEnabled = true;
            this.squareSprite = false;
            this.health = 575;

            this.requirements(Category.defense, ItemStack.with(
                    OctoItems.octoMat, 6
            ));
        }};

        //end walls
    }
}