package octo.core.util.depedency;

import arc.Core;
import arc.files.Fi;
import mindustry.mod.Mods;
import octo.core.util.Log;
import octo.core.util.ModUtils;
import octo.core.util.FileFinder;
import octo.core.graphics.Regions;

public class AssetsTransformProcessor {
    public static void transform() {
        Mods.LoadedMod mod = ModUtils.getCurrentMod();
        if(mod == null) {
            return;
        }

        Fi root = FileFinder.getSearcher(
                "sprites/transformations/" + mod.name + "/"
        ).log().nextProtocol();

        if(FileFinder.validFile(root)) {
            for(Fi fi : root.list()) {
                if(fi.toString().endsWith(".png")) {
                    String name = fi.nameWithoutExtension();
                    Core.atlas.addRegion(mod.name + "-" + name, Regions.getRegion(name));
                }
            }
        } else {
            Log.fine("no transformation dir");
        }
    }
}