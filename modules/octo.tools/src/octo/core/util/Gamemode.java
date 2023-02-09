package octo.core.util;

import arc.Events;
import mindustry.game.EventType;
import mindustry.gen.Groups;
import org.jetbrains.annotations.Contract;

import static mindustry.Vars.*;

public enum Gamemode {
    CREATIVE(() -> {
        if(state != null && state.rules != null) {
            state.rules.infiniteResources = true;
            state.rules.bannedBlocks.clear();
            state.rules.bannedUnits.clear();

            state.rules.hideBannedBlocks = false;
            state.rules.hiddenBuildItems.clear();
        }
    }),

    SURVIVAL(() -> {
        //nothing, default mindustry
    }),

    HARDCORE(() -> {
        if(state != null && state.rules != null) {
            state.rules.infiniteResources = false;
            state.rules.damageExplosions = true;
            state.rules.reactorExplosions = true;

            if(Time.every(60)) {
                Groups.unit.each(unit -> {
                    unit.damage(5);
                });
            }
        }
    });

    public final Runnable runnable;

    @Contract(pure = true)
    Gamemode(Runnable runnable) {
        this.runnable = runnable;
    }

    public static Gamemode mode = Gamemode.SURVIVAL;

    static {
        Events.run(EventType.Trigger.update, () -> {
            if(mode.runnable != null) {
                mode.runnable.run();
            }
        });
    }
}