package octo.content;

import arc.graphics.g2d.Lines;
import arc.math.geom.Vec2;
import arc.math.Mathf;

import mindustry.entities.Effect;
import mindustry.graphics.Drawf;
import mindustry.content.Fx;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.stroke;

public class OctoFx {
    public static @SuppressWarnings("unused") Effect
            railEnd,
            railShoot,
            railLine,
    end;

    public static void load() {
        railEnd = new Effect(14f, e -> {
            color(e.color);
            Drawf.tri(e.x, e.y, e.fout() * 1.5f, 5f, e.rotation);
        });

        railShoot = new Effect(10, e -> {
            color(e.color);
            float w = 1.2f + 7 * e.fout();

            Drawf.tri(e.x, e.y, w, 30f * e.fout(), e.rotation);
            color(e.color);

            for(int i : Mathf.signs) {
                Drawf.tri(e.x, e.y, w * 0.9f,
                        18f * e.fout(), e.rotation + i * 90f);
            }

            Drawf.tri(e.x, e.y, w, 4f * e.fout(), e.rotation + 180f);
        });

        railLine = new Effect(20f, e -> {
            if(!(e.data instanceof Vec2 v)) {
                return;
            }

            color(e.color);
            stroke(e.fout() * 0.9f + 0.6f);

            Fx.rand.setSeed(e.id);
            for(int i = 0; i < 7; i++) {
                Fx.v.trns(e.rotation, Fx.rand.random(
                        8f, v.dst(e.x, e.y) - 8f
                ));

                Lines.lineAngleCenter(
                        e.x + Fx.v.x,
                        e.y + Fx.v.y,
                        e.rotation + e.finpow(),

                        e.foutpowdown() * 20f *
                                Fx.rand.random(0.5f, 1f) + 0.3f
                );
            }

            e.scaled(14f, b -> {
                stroke(b.fout() * 1.5f);
                color(e.color);
                Lines.line(e.x, e.y, v.x, v.y);
            });
        });
    }
}