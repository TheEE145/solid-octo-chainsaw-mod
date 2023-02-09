package octo.content;

import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.ctype.UnlockableContent;
import mindustry.gen.Icon;
import mindustry.ui.dialogs.BaseDialog;

import octo.core.cmd.CommandDialog;
import octo.core.graphics.Regions;
import octo.ui.OctoSettingCategory;
import octo.core.graphics.ModIcons;

import arc.util.Time;

import static mindustry.Vars.*;

public class OctoUI {
    public static Seq<String> contributors = new Seq<>();

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
        ui.settings.getCategories().add(new OctoSettingCategory("@octogen", OctoIcons.mod, table -> {
            table.button("@alltech", Icon.list, () -> {
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
            }).width(300).pad(3).row();

            table.button("@exit", Icon.left, table::exitFromSettings)
                    .width(300f).pad(3).row();

            table.resetButton().row();
            table.exitButton().row();

            table.image().color(Color.gray).growX().height(4).row();

            table.settings(settings -> {
                settings.checkPref("animitems", true);
                settings.checkPref("nocoreteams", false);
            }).padTop(3);
        }));
    }
}