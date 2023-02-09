package octo.core.util;

import mindustry.mod.Mods;
import org.jetbrains.annotations.Contract;

import static mindustry.Vars.mods;

public class ModUtils {
    public static boolean isValidMod(String name) {
        return isValidMod(mods.getMod(name));
    }

    @Contract("null -> false")
    public static boolean isValidMod(Mods.LoadedMod mod) {
        return mod != null && mod.enabled();
    }
}