package octo.util;

import mindustry.gen.Icon;
import mindustry.ui.dialogs.BaseDialog;
import org.jetbrains.annotations.NotNull;

public class ThrowableUtils {
    public static @NotNull String toString(@NotNull Throwable throwable) {
        StringBuilder builder = new StringBuilder(throwable.getClass().getSimpleName());
        builder.append(": ").append(throwable.getMessage()).append("\n");

        for(StackTraceElement element : throwable.getStackTrace()) {
            builder.append("        at ").append(element.toString()).append("\n");
        }

        if(throwable.getCause() != null) {
            builder.append("Caused by ").append(toString(throwable.getCause()));
        }

        return builder.toString();
    }

    public static void showDialog(@NotNull Throwable throwable) {
        BaseDialog baseDialog = new BaseDialog(throwable.getMessage());
        baseDialog.cont.pane(err -> err.add(toString(throwable)).grow()).grow();
        baseDialog.buttons.button("@back", Icon.left, baseDialog::hide).size(200, 50);
        baseDialog.show();
    }
}