package octo.content;

import mindustry.content.*;

import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootHelix;

import mindustry.entities.pattern.ShootSpread;
import mindustry.type.Category;
import mindustry.type.ItemStack;

import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ContinuousLiquidTurret;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.blocks.defense.turrets.Turret;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.draw.*;
import mindustry.world.meta.BuildVisibility;

import octo.gen.ModSounds;
import octo.util.Regions;

import octo.world.AmplificationTower;
import octo.world.IllegalItemSource;
import octo.world.OctoBlockJoint;
import octo.world.OctoWall;

public class OctoBlocks {
    public @SuppressWarnings("unused") static Block
            //turrets
            earthPower,

            //br
            matilda,
            matilda2,
            matildaSplit,

            //britany
            alecto,
            AT4,
            AT8,
            AT7,
            AT7Plus,

            //usa
            octoChar,
            octoFrag,
            octoTheta,
            octoGamma,
            octoDelta,
            octoAlpha,
            octoSigma,
            octoFloat,
            octoTerra,
            octoDouble,
            octoString,
            octoKraken,

            //wolfenstein
            soulstorm,
            objectMines1,
            object70,
            DORA,

            illangeist5,
            illangeist5x,
            illangeist16,
            illangeist42,
            illangeist42x,
            illangeist84,
            illangeist112,

            exodus1,
            exodus1x,
            exodus4,
            exodus8,
            exodus8x,
            exodus24,
            exodus56,
            exodus70,
            exodus115,

            monolith135,
            monolith157,
            monolith178,
            monolith194,
            monolith203,
            monolith233,

            //defence
            octoSpike,
            amplificationTower,

            //walls
            octoMatWall,

            //cores
            coreOctogen,

            //crafters
            soulCollector,
            soulHardener,
            soulSmelter,

            //illegal
            illegalItemSource,
    end;

    public static void fixCoreIcon(Block block) {
        if(block != null) {
            block.uiIcon = Regions.getRegion(block.name + "-ui");
        }
    }

    public static void load() {
        //region turrets

        //wolfenstein
        exodus1 = new ItemTurret("exodus1") {{
            this.drawer = new DrawTurret("reinforced-");
            this.range = ((Turret) Blocks.duo).range * 1.5f;
            this.shootSound = ModSounds.gunshot;
            this.health = 350;
            this.shootEffect = Fx.none;
            this.size = 1;
            this.reload = 10;

            this.shoot = new ShootSpread() {{
                this.shots = 4;
                this.shotDelay = 5;
            }};

            ammo(
                    OctoItems.monolith, OctoBullets.exodus1monolith,
                    OctoItems.soul,     OctoBullets.exodus1soul,
                    OctoItems.octoMat,  OctoBullets.exodus1octomat
            );

            requirements(Category.turret, ItemStack.with(
                    OctoItems.monolith, 10,
                    OctoItems.soul,     20,
                    OctoItems.octoMat,  35
            ));
        }};

        exodus1x = new ItemTurret("exodus1x") {{
            this.drawer = new DrawTurret("reinforced-");
            this.range = ((Turret) Blocks.duo).range * 2f;
            this.shootSound = ModSounds.gunshot;
            this.health = 650;
            this.shootEffect = Fx.none;
            this.size = 1;
            this.reload = 5;

            this.shoot = new ShootAlternate(4);

            ammo(
                    OctoItems.monolith, OctoBullets.exodus1monolith,
                    OctoItems.soul,     OctoBullets.exodus1soul,
                    OctoItems.octoMat,  OctoBullets.exodus1octomat
            );

            requirements(Category.turret, ItemStack.with(
                    OctoItems.monolith, 25,
                    OctoItems.soul,     45,
                    OctoItems.octoMat,  70
            ));
        }};

        exodus4 = new ItemTurret("exodus4") {{
            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        exodus8 = new ItemTurret("exodus8") {{
            this.size = 2;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        exodus8x = new ItemTurret("exodus8x") {{
            this.size = 2;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        exodus24 = new ItemTurret("exodus24") {{
            this.size = 2;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        exodus56 = new ItemTurret("exodus56") {{
            this.size = 3;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        exodus70 = new ItemTurret("exodus70") {{
            this.size = 3;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        exodus115 = new ItemTurret("exodus115") {{
            this.size = 4;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        illangeist5 = new ItemTurret("illangeist5") {{
            this.size = 3;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        illangeist5x = new ItemTurret("illangeist5x") {{
            this.size = 3;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        illangeist16 = new ItemTurret("illangeist16") {{
            this.size = 3;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        illangeist42 = new ItemTurret("illangeist42") {{
            this.size = 4;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        illangeist42x = new ItemTurret("illangeist42x") {{
            this.size = 4;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        illangeist84 = new ItemTurret("illangeist84") {{
            this.size = 4;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        illangeist112 = new ItemTurret("illangeist112") {{
            this.size = 5;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        monolith135 = new ItemTurret("monolith135") {{
            this.size = 3;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        monolith157 = new ItemTurret("monolith157") {{
            this.size = 4;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        monolith178 = new ItemTurret("monolith178") {{
            this.size = 4;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        monolith194 = new ItemTurret("monolith194") {{
            this.size = 5;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        monolith203 = new ItemTurret("monolith203") {{
            this.size = 5;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        monolith233 = new ItemTurret("monolith233") {{
            this.size = 6;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        object70 = new ItemTurret("object70") {{
            this.size = 5;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        objectMines1 = new ItemTurret("object-1") {{
            this.size = 6;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        DORA = new Turret("dora") {{
            this.size = 9;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));
        }};

        soulstorm = new ContinuousLiquidTurret("soulstorm") {{
            this.health = 3500;
            this.size = 5;

            this.shootX = -8.6f;
            this.shootY = 10f;

            this.drawer = new DrawTurret("reinforced-");
            this.shootEffect = Fx.none;
            this.range = 20 * 8;

            ammo(OctoItems.soulLiquid, OctoBullets.soulStormShoot);
            this.requirements(Category.turret, ItemStack.empty);
        }};

        //usa
        octoChar = new PowerTurret("octo-char") {{
            this.drawer = new DrawTurret("reinforced-");
            this.range = ((Turret) Blocks.scatter).range;
            this.shootSound = ModSounds.laserShoot;
            this.health = Blocks.scatter.health;
            this.shootEffect = Fx.none;
            this.reload = 20;
            this.size = 2;

            this.shoot = new ShootHelix() {{
                this.offset = 4;
                this.shots = 3;
            }};

            this.shootType = OctoBullets.octoCharShoot;

            this.consumePower(2);
            this.requirements(Category.turret, ItemStack.with(
                    OctoItems.octoMat, 24
            ));

            this.alwaysUnlocked = true;
        }};

        octoFrag = new PowerTurret("octo-frag") {{
            this.size = 2;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        octoAlpha = new PowerTurret("octo-alpha") {{
            this.size = 2;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        octoTheta = new PowerTurret("octo-theta") {{
            this.size = 3;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        octoGamma = new PowerTurret("octo-gamma") {{
            this.size = 3;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        octoDelta = new PowerTurret("octo-delta") {{
            this.size = 4;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        octoSigma = new PowerTurret("octo-sigma") {{
            this.size = 4;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        octoFloat = new PowerTurret("octo-float") {{
            this.size = 5;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        octoString = new PowerTurret("octo-string") {{
            this.size = 5;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        octoDouble = new PowerTurret("octo-double") {{
            this.size = 6;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        octoKraken = new PowerTurret("octo-kraken") {{
            this.size = 7;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        octoTerra = new PowerTurret("octo-terra") {{
            this.size = 7;

            this.requirements(Category.turret, BuildVisibility.hidden, ItemStack.with(
                    OctoItems.researchStruct, Integer.MAX_VALUE
            ));

            this.drawer = new DrawTurret("reinforced-");
        }};

        //poland
        earthPower = new PowerTurret("earth-power") {{
            this.health = Integer.MAX_VALUE;
            this.size = 7;

            this.shootSound = ModSounds.laserShoot;
            this.drawer = new DrawTurret("reinforced-");
            this.shootEffect = Fx.none;
            this.range = 1000;

            this.shoot = new ShootHelix() {{
                this.scl = 56;
                this.shots = 20;
            }};

            this.shootType = OctoBullets.polandLaser;
            this.requirements(Category.turret, ItemStack.with(
                    OctoItems.octoMat,  9999,
                    OctoItems.soul,     9999,
                    OctoItems.monolith, 4999
            ));

            this.buildVisibility = BuildVisibility.sandboxOnly;
        }};

        //end turrets
        //region defense

        octoSpike = new OctoBlockJoint("octo-spike") {{
            this.mirror = true;
            this.isSpike = true;
            this.spikeDamage = 85;
            this.bulletCollides = false;

            this.requirements(Category.defense, ItemStack.with(
                    OctoItems.octoMat, 7
            ));
        }};

        amplificationTower = new AmplificationTower("amplification-tower") {{
            this.health = 40;

            this.requirements(Category.effect, ItemStack.with(
                    OctoItems.octoMat, 12,
                    OctoItems.monolith, 6
            ));
        }};

        //end defense
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
        //region cores

        coreOctogen = new CoreBlock("core-octogen") {{
            this.health = 3750;
            this.size = 5;

            this.alwaysUnlocked = true;
            this.requirements(Category.effect, ItemStack.empty);
        }};

        //end cores
        //region crafters

        soulCollector = new GenericCrafter("soul-collector") {{
            this.outputItem = new ItemStack(OctoItems.soul, 6);
            this.consumePower(1);
            this.size = 3;

            this.craftTime = 120;
            this.requirements(Category.crafting, ItemStack.with(
                OctoItems.octoMat, 24
            ));
        }};

        soulSmelter = new GenericCrafter("soul-smelter") {{
            this.outputLiquid = new LiquidStack(OctoItems.soulLiquid, 1);
            this.consumeItem(OctoItems.soul, 3);
            this.consumePower(1);
            this.size = 3;

            this.drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(OctoItems.soulLiquid),
                    new DrawDefault()
            );

            this.craftTime = 120;
            this.liquidCapacity = 120;
            this.requirements(Category.crafting, ItemStack.with(
                    OctoItems.octoMat, 24,
                    OctoItems.soul, 12
            ));
        }};

        soulHardener = new GenericCrafter("soul-hardener") {{
            this.outputItem = new ItemStack(OctoItems.soul, 3);
            this.consumeLiquid(OctoItems.soulLiquid, 1);
            this.consumePower(1);
            this.size = 3;

            this.drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(OctoItems.soulLiquid),
                    new DrawDefault()
            );

            this.craftTime = 120;
            this.liquidCapacity = 120;
            this.requirements(Category.crafting, ItemStack.with(
                    OctoItems.octoMat, 24,
                    OctoItems.soul, 12
            ));
        }};

        //end crafters
        //region illegal

        illegalItemSource = new IllegalItemSource("illegal-item-source") {{
            this.requirements(Category.distribution, BuildVisibility.sandboxOnly, ItemStack.with());
            this.alwaysUnlocked = true;
        }};

        //end illegal
    }
}