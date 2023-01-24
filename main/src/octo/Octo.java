package octo;

import arc.*;
import arc.util.*;
import mindustry.ui.dialogs.*;
import mindustry.game.EventType.*;

import octo.annotations.Mod;
import octo.gen.ModSounds;
import octo.util.Regions;

public @Mod class Octo extends mindustry.mod.Mod {
    public Octo() {
        Log.info("Loaded Octo constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.image(Regions.getRegion("frog")).pad(20f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });

        //sounds creation / disposing
        Events.on(FileTreeInitEvent.class, ignored -> ModSounds.load());
        Events.on(DisposeEvent.class, ignored -> ModSounds.dispose());
    }

    @Override
    public void loadContent() {
        Log.info("Loading some content.");
    }
}