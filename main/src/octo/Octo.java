package octo;

import arc.Core;
import arc.struct.Seq;
import mindustry.Vars;
import mindustry.game.EventType.*;
import mindustry.mod.Mods;
import octo.content.*;

import octo.content.modModules.BetamindyDependencyModule;
import octo.ui.ModDialog;
import octo.util.FileFinder;
import octo.util.Log;
import octo.util.depedency.ModDependencyContainer;
import octo.eventbus.MindustryEventApi;
import octo.annotations.Mod;
import octo.gen.IconManager;
import octo.gen.ModSounds;

public @Mod class Octo extends mindustry.mod.Mod {
    public static final BetamindyDependencyModule betamindyModule;
    public static Mods.LoadedMod mod;

    public static final ModDependencyContainer container = new ModDependencyContainer(
            betamindyModule = new BetamindyDependencyModule()
    );

    public Octo() {
        Log.info("loading events api");
        MindustryEventApi.bus.get(ClientLoadEvent.class)
                .addEventListener(IconManager::load)
                .addEventListener(OctoUI::loadSettings)
                .addEventListener(OctoItems::loadAnimated)
                .addEventListener(OctoStats::post)
                .addEventListener(OctoUI::load)
                .addEventListener(OctoCommands::registerCommands)
                .addEventListener(this::post)
                .addEventListener(ModDialog::load);

        Log.info("loading sound structure");
        MindustryEventApi.bus.get(FileTreeInitEvent.class).addEventListener(ModSounds::load);
        MindustryEventApi.bus.get(DisposeEvent.class).addEventListener(ModSounds::dispose);

        Log.info("loaded mod constructor");
        Log.fine("if you hear sounds you have ears");
    }

    @Override
    public void loadContent() {
        //content
        container.init();
        container.preload();

        OctoStats.load();
        OctoStats.loadCountries();
        //ModStatusEffects.load()
        OctoItems.load();
        OctoBullets.load();
        //ModUnitTypes.load()
        OctoBlocks.load();
        //ModPlanets.load()
        //ModSectorPresets.load()
        OctoTech.load();

        container.loadContent();
    }

    public void post() {
        if(!Core.settings.getBool("nocoreteams") && !Vars.headless) {
            OctoBlocks.fixCoreIcon(OctoBlocks.coreOctogen);
        }

        FileFinder.modToSearch = mod = Vars.mods.getMod(this.getClass());

        //spoiler there`s not contributors, it`s just borrow and freesound.org
        OctoUI.contributors = Seq.with(FileFinder.getSearcher("contributors_mod")
                .log().nextProtocol().readString("UTF-8").split("\n"));

        Log.fine("you see messages about search protocol?");
    }
}