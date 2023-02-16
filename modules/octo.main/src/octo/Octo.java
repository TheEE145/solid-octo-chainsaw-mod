package octo;

import arc.ApplicationListener;
import arc.Events;
import arc.struct.Seq;
import arc.Core;

import mindustry.core.ContentLoader;
import mindustry.ctype.UnlockableContent;
import mindustry.game.EventType.*;
import static mindustry.Vars.*;
import mindustry.mod.Mods;

import mindustry.type.Liquid;
import mindustry.ui.dialogs.ResearchDialog;
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
import org.apache.commons.lang3.StringUtils;

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

        content.each(cont -> {
            if(cont instanceof UnlockableContent content && !content.isVanilla()) {
                if(content.localizedName.equals(content.name)) {
                    String name = content.name.substring(content.name.indexOf('-'));
                    StringBuilder local = new StringBuilder();

                    if(name.contains("-")) {
                        String[] buffer = name.split("-");
                        int fx = 0;
                        for(int i = 0; i < buffer.length; i++) {
                            if(StringUtils.isEmpty(buffer[i])) {
                                fx++;
                                continue;
                            }

                            String txt = buffer[i].substring(1);
                            String fr = buffer[i].substring(0, 1);
                            local.append(i == fx ? fr.toUpperCase() : fr).append(txt);

                            if(i < buffer.length - 1) {
                                local.append(" ");
                            }
                        }
                    } else {
                        local
                                .append(name.substring(0, 1).toUpperCase())
                                .append(name.substring(1));
                    }

                    content.localizedName = local.toString();
                }
            }
        });

        String get = OctoItems.end.description;

        Events.run(Trigger.update, () -> {
            OctoItems.end.description = OctoItems.end.unlocked() ? get : Core.bundle.get("empty");
        });

        //spoiler there`s not contributors, it`s just borrow and freesound.org
        OctoUI.contributors = Seq.with(FileFinder.getSearcher("contributors_mod")
                .log().nextProtocol().readString("UTF-8").split("\n")).filter((str) -> {
                    return !str.isEmpty() && !str.startsWith("//");
        });

        Core.app.post(() -> ui.research = new ResearchDialog());
    }
}