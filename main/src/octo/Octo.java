package octo;

import octo.content.*;
import mindustry.game.EventType.*;

import octo.content.modModules.BetamindyDependencyModule;
import octo.util.depedency.ModDependencyContainer;
import octo.eventbus.MindustryEventApi;
import octo.annotations.Mod;
import octo.gen.IconManager;
import octo.gen.ModSounds;

public @Mod class Octo extends mindustry.mod.Mod {
    public static final BetamindyDependencyModule betamindyModule;

    public static final ModDependencyContainer container = new ModDependencyContainer(
            betamindyModule = new BetamindyDependencyModule()
    );

    public Octo() {
        MindustryEventApi.bus.get(ClientLoadEvent.class)
                .addEventListener(IconManager::load)
                .addEventListener(OctoItems::loadAnimated)
                .addEventListener(OctoStats::post)
                .addEventListener(OctoUI::loadSettings)
                .addEventListener(OctoUI::load)
                .addEventListener(OctoCommands::registerCommands)
                .addEventListener(this::post);

        MindustryEventApi.bus.get(FileTreeInitEvent.class).addEventListener(ModSounds::load);
        MindustryEventApi.bus.get(DisposeEvent.class).addEventListener(ModSounds::dispose);
    }

    @Override
    public void loadContent() {
        container.init();
        container.preload();

        OctoStats.load();
        OctoStats.loadCountries();
        //ModStatusEffects.load()
        OctoItems.load();
        //ModBullets.load()
        //ModUnitTypes.load()
        OctoBlocks.load();
        //ModPlanets.load()
        //ModSectorPresets.load()
        OctoTech.load();

        container.loadContent();
    }

    public void post() {
        OctoBlocks.fixCoreIcon(OctoBlocks.coreOctogen);
    }
}