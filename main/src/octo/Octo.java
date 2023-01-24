package octo;

import arc.*;
import arc.util.*;
import mindustry.ui.dialogs.*;
import mindustry.game.EventType.*;

import octo.annotations.Mod;
import octo.content.OctoBlocks;
import octo.content.OctoItems;
import octo.gen.ModSounds;
import octo.util.Regions;

public @Mod class Octo extends mindustry.mod.Mod {
    public Octo() {
        Log.info("Loaded Octo constructor.");

        Events.on(ClientLoadEvent.class, e -> {
            //show beta dialog
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("@octo.beta");

                dialog.cont.add("@octo.beta.text").row();
                dialog.cont.image(Regions.getRegion("frog")).pad(20f).row();
                dialog.cont.button("@confirm", dialog::hide).size(200f, 50f);
                dialog.show();
            });

            //load animations
            OctoItems.loadAnimated();
        });

        //sounds creation / disposing
        Events.on(FileTreeInitEvent.class, ignored -> ModSounds.load());
        Events.on(DisposeEvent.class, ignored -> ModSounds.dispose());
    }

    @Override
    public void loadContent() {
        Log.info("Loading some content.");

        //ModStatusEffects.load()
        OctoItems.load();
        //ModBullets.load()
        //ModUnitTypes.load()
        OctoBlocks.load();
        //ModPlanets.load()
        //ModSectorPresets.load()
        //ModTechTree.load()
    }
}