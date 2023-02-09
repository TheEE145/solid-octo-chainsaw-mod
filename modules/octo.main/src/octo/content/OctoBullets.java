package octo.content;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.StatusEffects;
import mindustry.entities.bullet.*;
import mindustry.graphics.Pal;

public class OctoBullets {
    public static @SuppressWarnings("unused") BulletType
            exodus1monolith,
            exodus1octomat,
            exodus1soul,
            octoCharShoot,
            soulStormShoot,
            polandLaser,
            type10missile,
    end;

    public static void load() {
        exodus1monolith = new BasicBulletType(9, 14) {{
            this.status = StatusEffects.disarmed;
            this.buildingDamageMultiplier = 0.5f;
            this.backColor = this.frontColor = Pal.darkOutline;

            this.homingPower = 0.75f;
            this.homingDelay = 0;
        }};

        exodus1soul = new BasicBulletType(4, 10) {{
            this.buildingDamageMultiplier = 0.30f;
            this.backColor = this.frontColor = Items.titanium.color;

            this.homingPower = 0.5f;
            this.homingDelay = 20;
        }};

        exodus1octomat = new BasicBulletType(6, 7) {{
            this.buildingDamageMultiplier = 0.25f;
            this.backColor = this.frontColor = Pal.heal;

            this.homingPower = 0.5f;
            this.homingDelay = 30;
        }};

        soulStormShoot = new ContinuousFlameBulletType(1624) {{
            length = 18 * 8;

            knockback = 2f;
            pierceCap = 3;

            colors = new Color[] {
                    Color.valueOf("FF795E").a(0.55f),
                    Color.valueOf("E46B58").a(0.7f),
                    Color.valueOf("C85C51").a(0.8f),
                    Color.valueOf("A04553"),
                    Color.white
            };

            flareColor = Color.valueOf("F15454");
            lightColor = hitColor = flareColor;
        }};

        octoCharShoot = new LaserBoltBulletType(6, 10) {{
            this.status = StatusEffects.electrified;
            this.buildingDamageMultiplier = 0.75f;
            this.backColor = Color.green;
            this.frontColor = Pal.heal;
        }};

        polandLaser = new ContinuousFlameBulletType(Float.POSITIVE_INFINITY) {{
            length = 1000;

            knockback = 2f;
            pierceCap = 3;

            colors = new Color[] {
                    Color.valueOf("465ab8").a(0.55f),
                    Color.valueOf("66a6d2").a(0.7f),
                    Color.valueOf("89e8b6").a(0.8f),
                    Color.valueOf("cafcbe"),
                    Color.white
            };

            flareColor = Color.valueOf("89e8b6");
            lightColor = hitColor = flareColor;
        }};

        type10missile = new MissileBulletType(7f, 15) {{
            width = 8f;
            height = 8f;
            shrinkY = 0f;
            drag = -0.003f;
            keepVelocity = false;
            splashDamageRadius = 45f;
            splashDamage = 10f;
            lifetime = 50f;
            trailColor = Pal.unitBack;
            backColor = Pal.unitBack;
            frontColor = Pal.unitFront;
            hitEffect = Fx.blastExplosion;
            despawnEffect = Fx.blastExplosion;
            weaveScale = 6f;
            weaveMag = 1f;
        }};
    }
}