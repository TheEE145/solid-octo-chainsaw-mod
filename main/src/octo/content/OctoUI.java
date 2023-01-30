package octo.content;

import mindustry.ctype.UnlockableContent;
import mindustry.gen.Icon;
import mindustry.ui.dialogs.BaseDialog;

import octo.cmd.CommandDialog;
import octo.util.ModIcons;
import octo.util.Regions;

import arc.util.Time;

import static mindustry.Vars.*;

public class OctoUI {
    public static void load() {
        Time.runTask(10f, () -> {
            BaseDialog dialog = new BaseDialog("@octo.beta");

            dialog.cont.add("@octo.beta.text").row();
            dialog.cont.image(Regions.getRegion("frog")).pad(20f).row();
            dialog.cont.button("@confirm", dialog::hide).size(200f, 50f);
            dialog.show();
        });

        ui.menufrag.addButton("shell", Icon.home, new CommandDialog(OctoCommands.entry)::show);
    }

    public static void loadSettings() {
        ui.settings.addCategory("@octogen", ModIcons.mod, table -> {
            table.button("@alltech", () -> {
                BaseDialog baseDialog = new BaseDialog("@warning");
                baseDialog.cont.add("@warning.text").row();

                baseDialog.buttons.clearChildren();
                baseDialog.buttons.button("@confirm", () -> {
                    content.each(content -> {
                        if(content instanceof UnlockableContent) {
                            ((UnlockableContent) content).quietUnlock();
                        }
                    });

                    baseDialog.hide();
                }).width(200);

                baseDialog.buttons.button("@cancel", baseDialog::hide).width(200);
                baseDialog.show();
            }).width(300);
        });
    }
}