package octo.ui;

import arc.Core;
import arc.func.Cons;
import arc.graphics.Color;
import arc.scene.Element;
import arc.scene.style.Drawable;
import arc.scene.ui.TextButton;
import arc.scene.ui.layout.Cell;

import mindustry.gen.Icon;
import octo.util.ModIcons;

import mindustry.ui.dialogs.SettingsMenuDialog.SettingsCategory;
import mindustry.ui.dialogs.SettingsMenuDialog.SettingsTable;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static arc.Core.*;
import static mindustry.Vars.*;

public class OctoSettingCategory extends SettingsCategory {
    public Cons<OctoSettingsTable> builder2;
    public OctoSettingsTable cont;

    public OctoSettingCategory(String name, Drawable icon, @NotNull Cons<OctoSettingsTable> builder) {
        super(name, icon, (ignored) -> {});
        this.builder2 = builder;

        (cont = new OctoSettingsTable()).build();
    }

    public void rebuild() {
        table.clearChildren();

        table.setWidth(scene.getWidth());
        table.setHeight(scene.getHeight());

        table.table(tab -> {
            tab.image(ModIcons.mod).center().top();
            tab.image().color(Color.gray).width(4).pad(4).growY().expandY();
        }).growY().expandY();

        cont = new OctoSettingsTable();
        builder2.get(cont);

        table.add(cont).update(ignored -> {
            ui.settings.buttons.clearChildren();
        }).growY().expandY();
    }

    public class OctoSettingsTable extends SettingsTable {
        public void exitFromSettings() {
            ui.settings.hide();
            ui.settings.buttons.clearChildren();
            ui.settings.addCloseButton();
        }

        public Cell<TextButton> exitButton() {
            return button("@back", Icon.left, () -> {
                this.exitFromSettings();
                ui.settings.show();
            }).width(300).pad(3);
        }

        public void build() {
            OctoSettingCategory.this.rebuild();
        }

        public void resetSettingsOf(@NotNull OctoSettingsTable table) {
            for(Setting setting : table.list) {
                if(setting.name == null || setting.title == null) {
                    continue;
                }

                settings.remove(setting.name);
            }
        }

        public Cell<TextButton> resetButton() {
            return button(bundle.get("settings.reset", "Reset to Defaults"), Icon.refresh, () -> {
                resetSettingsOf(this);

                for(Element element : this.children) {
                    if(element == this) {
                        return;
                    }

                    if(element instanceof OctoSettingsTable table) {
                        this.resetSettingsOf(table);
                    }
                }

                build();
            }).width(300).pad(3);
        }

        public Cell<OctoSettingsTable> settings(Consumer<OctoSettingsTable> tableConsumer) {
            if(tableConsumer == null) {
                return this.add(new OctoSettingsTable());
            }

            OctoSettingsTable table1 = new OctoSettingsTable();
            tableConsumer.accept(table1);
            return this.add(table1);
        }

        @Override
        public void rebuild() {
            clearChildren();

            for(Setting setting : list) {
                setting.add(this);
            }
        }
    }
}