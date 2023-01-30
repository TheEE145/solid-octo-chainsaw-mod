package octo.util.depedency;

import arc.struct.Seq;
import org.jetbrains.annotations.Contract;

/**
 * if you want to load more than 1 mod modules use this container
 * @author TheEE145
 */
public class ModDependencyContainer {
    public final Seq<ModDependencyModule> modules;

    @Contract(pure = true)
    public ModDependencyContainer(Seq<ModDependencyModule> modules) {
        this.modules = modules == null ? new Seq<>() : modules;
    }

    public ModDependencyContainer(ModDependencyModule... modules) {
        this(Seq.with(modules));
    }

    public ModDependencyContainer() {
        this(new Seq<>());
    }

    public void init() {
        this.modules.each(ModDependencyModule::init);
    }

    public void preload() {
        this.modules.each(module -> {
            if(module.validMod()) {
                module.preload();
            }
        });
    }

    public void loadContent() {
        this.modules.each(module -> {
            if(module.validMod()) {
                module.loadContent();
            }
        });
    }
}