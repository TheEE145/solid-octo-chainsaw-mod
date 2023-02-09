package octo.ui;

import arc.Core;
import arc.Events;
import arc.graphics.Color;
import arc.scene.Element;
import arc.scene.ui.Dialog;
import arc.scene.ui.Label;
import arc.scene.ui.ScrollPane;
import arc.scene.ui.layout.Scl;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.Time;
import mindustry.ctype.Content;
import mindustry.ctype.UnlockableContent;
import mindustry.game.EventType;
import mindustry.gen.Call;
import mindustry.gen.Icon;
import mindustry.ui.Styles;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.ui.dialogs.ModsDialog;
import octo.Octo;
import octo.core.graphics.Regions;
import octo.core.util.ThrowableUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import static mindustry.Vars.*;

/**
 * from Omaloon by zelaux
 * @author zelaux
 */
public class ModDialog extends BaseDialog {
    public static Dialog currentDialog = null;

    public static final String repo = "https://github.com/TheEE145/solid-octo-chainsaw-mod";
    private static final Runnable installer;

    static {
        try {
            Method mod = ModsDialog.class.getDeclaredMethod(
                    "githubImportMod", String.class, boolean.class, String.class
            );

            mod.setAccessible(true);
            installer = () -> {
                try {
                    mod.invoke(ui.mods, repo, true, null);
                } catch(IllegalAccessException | InvocationTargetException e) {
                    ThrowableUtils.showDialog(e);
                }
            };
        } catch(NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static void load() {
        Events.run(EventType.Trigger.update, () -> {
            String description = Octo.mod.meta.description;
//                modInfo.meta.description = descriptionBuilder.toString();
            if(Core.scene != null && !headless){
                if(currentDialog != null && !currentDialog.isShown()){
                    currentDialog = null;
                }
                Dialog dialog = Core.scene.getDialog();
                if(dialog instanceof BaseDialog &&
                        Objects.equals(dialog.title.getText().toString(), Octo.mod.meta.displayName()))
                {
                    if(dialog == currentDialog){
                        return;
                    }
                    if(currentDialog != null && Core.scene.root.getChildren().contains(currentDialog)){
                        return;
                    }
                    dialog.cont.getChildren().each(e -> {
                        if(e instanceof ScrollPane scrollPane){
                            Element widget = scrollPane.getWidget();
                            if(widget instanceof Table table){
                                for(Element child : table.getChildren()) {
                                    if(child instanceof Label label &&
                                            label.getText().toString().equals(description))
                                    {
                                        if(!(dialog instanceof ModDialog)) {
                                            dialog.hide(null);
                                            new ModDialog().show();
                                        }

                                        currentDialog = dialog;
                                    }
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    public ModDialog() {
        super(Octo.mod.meta.displayName);

        this.titleTable.getChildren().each(ch -> ch.visible(() -> false));
        this.title.visible(() -> false);

        Seq<UnlockableContent> all = Seq.with(content.getContentMap()).<Content>flatten()
                .select(c -> c.minfo.mod == Octo.mod && c instanceof UnlockableContent).as();

        this.cont.table(md -> {
            md.image(Regions.getRegion("logo")).top().height(Core.scene.getHeight() * 0.2f).growX();

            md.row();
            md.pane(desc -> {
                desc.center();
                desc.defaults().padTop(10).left();

                desc.add("@editor.author").padRight(10f).color(Color.gray).row();
                desc.add(Octo.mod.meta.author).growX().wrap().padTop(0).row();
                desc.row();

                desc.add("@editor.description").padRight(10).color(Color.gray).top().row();
                desc.add(Octo.mod.meta.description).growX().wrap().padTop(0).row();
            }).width(400f).growY().expandY().row();
        }).grow().expand();

        this.buttons.defaults().size(210f, 64f).row();

        this.buttons.button("@back", Icon.left, this::hide);
        this.buttons.button("@mods.github.open", Icon.link, () -> {
            Core.app.openURI(repo);
        });

        this.buttons.button("@mods.viewcontent", Icon.book, () -> {
            if(all.isEmpty()) {
                Call.infoMessage("@empty");
            } else {
                BaseDialog dialog = new BaseDialog(Octo.mod.meta.displayName);

                dialog.cont.pane(cs -> {
                    int i = 0;

                    for(UnlockableContent content : all) {
                        cs.button(Regions.toDrawable(content.uiIcon), Styles.cleari, iconMed, () -> {
                            try {
                                ui.content.show(content);
                            } catch(Throwable throwable) {
                                ThrowableUtils.showDialog(throwable);
                            }
                        }).size(50f).with(im -> {
                            var click = im.getClickListener();

                            im.update(() -> {
                                im.getImage().color.lerp(
                                        !click.isOver() ? Color.lightGray : Color.white,
                                        0.4f * Time.delta
                                );
                            });
                        }).tooltip(content.localizedName);

                        if(++i % (int) Math.min(
                                Core.graphics.getWidth() / Scl.scl(110),
                                14
                        ) == 0)
                        {
                            cs.row();
                        }
                    }
                }).grow();

                dialog.buttons.defaults().size(210f, 64f).row();
                dialog.buttons.button("@back", Icon.left, dialog::hide);
                dialog.show();

                this.resized(dialog::hide);
            }
        });

        if(!Octo.mod.hasSteamID()) {
            this.buttons.button("@mods.browser.reinstall", Icon.download, installer);
        }

        if(!mobile) {
            this.buttons.button("@mods.openfolder", Icon.folder, () -> {
                Core.app.openFolder(Octo.mod.root.absolutePath());
            });
        }
    }
}