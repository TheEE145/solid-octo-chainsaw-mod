package octo.core.util.depedency;

import mindustry.mod.Mods;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;
import mindustry.world.Block;

import octo.core.util.ModUtils;

import org.jetbrains.annotations.Contract;
import static mindustry.Vars.*;

/**
 * Used to modify a mod or dependency mod using this module class
 * if a need mod enabled when module can add item to this mod or modify item from this mod
 *
 * @author TheEE145
 */
public abstract class ModDependencyModule {
    public Mods.LoadedMod loadedMod;

    @Contract(pure = true)
    public ModDependencyModule(Mods.LoadedMod loadedMod) {
        this.loadedMod = loadedMod;
    }

    @Contract(pure = true)
    public ModDependencyModule() {
        this(null);
    }

    public void init() {
    }

    public boolean validMod() {
        return ModUtils.isValidMod(this.loadedMod);
    }

    public String prefix(String name) {
        if(this.validMod()) {
            return this.loadedMod.name + "-" + name;
        }

        return name;
    }

    public Item itemFromMod(String name) {
        return content.item(this.prefix(name));
    }

    public Liquid liquidFromMod(String name) {
        return content.liquid(this.prefix(name));
    }

    public Block blockFromMod(String name) {
        return content.block(this.prefix(name));
    }

    public UnitType unitTypeFromMod(String name) {
        return content.unit(this.prefix(name));
    }

    public StatusEffect effectFromMod(String name) {
        return content.statusEffect(this.prefix(name));
    }

    public void preload() {
    }

    public abstract void loadContent();
}