package octo;

import arc.ApplicationListener;
import arc.struct.Seq;
import arc.Core;

import mindustry.core.ContentLoader;
import mindustry.ctype.UnlockableContent;
import mindustry.game.EventType.*;
import static mindustry.Vars.*;
import mindustry.mod.Mods;

import octo.content.modModules.BetamindyDependencyModule;
import octo.content.modModules.OmaloonDependencyModule;
import octo.gen.IconManager;
import octo.gen.ModSounds;
import octo.type.darkenergy.DarkEnergy;
import octo.ui.ModDialog;
import octo.content.*;

import octo.core.util.depedency.ModDependencyContainer;
import octo.core.events.MindustryEventApi;
import octo.core.util.annotations.Mod;
import octo.core.graphics.ModIcons;
import octo.core.util.FileFinder;
import octo.core.util.Log;

public @Mod class Octo extends mindustry.mod.Mod implements ApplicationListener {
    public static final BetamindyDependencyModule betamindyModule;
    public static final OmaloonDependencyModule omaloonModule;
    public static Mods.LoadedMod mod;

    public static final ModDependencyContainer container = new ModDependencyContainer(
            betamindyModule = new BetamindyDependencyModule(),
            omaloonModule = new OmaloonDependencyModule()
    );

    @Override
    public void update() {
        //XeonUnits.setLoaders();
    }

    public Octo() {
        ModIcons.onload = OctoIcons::load;
        Core.app.addListener(this);

        Log.info("loading events api");
        MindustryEventApi.bus.get(ClientLoadEvent.class)
                .addEventListener(IconManager::load)
                .addEventListener(OctoUI::loadSettings)
                .addEventListener(this::post)
                .addEventListener(OctoItems::loadAnimated)
                .addEventListener(OctoStats::post)
                .addEventListener(OctoUI::load)
                .addEventListener(OctoCommands::registerCommands)
                .addEventListener(ModDialog::load);

        Log.info("loading sound structure");
        MindustryEventApi.bus.get(FileTreeInitEvent.class).addEventListener(ModSounds::load);
        MindustryEventApi.bus.get(DisposeEvent.class).addEventListener(ModSounds::dispose);

        Log.info("loaded mod constructor");
        Log.fine("if you hear sounds you have ears");
    }

    @Override
    public void loadContent() {
        container.init();
        container.preload();
        OctoStats.load();
        OctoStats.loadCountries();
        //ModStatusEffects.load()
        OctoItems.load();
        OctoFx.load();
        OctoBullets.load();
        OctoUnits.load();
        OctoBlocks.load();
        //ModPlanets.load()
        //ModSectorPresets.load()
        OctoTech.load();
    }

    public void post() {
        FileFinder.modToSearch = mod = mods.getMod(this.getClass());
        container.loadContent();

        content.blocks().each(DarkEnergy::fixModules);
        if(!Core.settings.getBool("nocoreteams") && !headless) {
            OctoBlocks.fixCoreIcon(OctoBlocks.coreOctogen);
        }

        //spoiler there`s not contributors, it`s just borrow and freesound.org
        OctoUI.contributors = Seq.with(FileFinder.getSearcher("contributors_mod")
                .log().nextProtocol().readString("UTF-8").split("\n")).filter((str) -> {
                    return !str.isEmpty() && !str.startsWith("//");
        });
    }
}