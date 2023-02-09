package octo.core.util;

import org.jetbrains.annotations.Contract;

public class Time {
    @Contract(pure = true)
    public static boolean every(int sec) {
        return Math.floor(arc.util.Time.globalTime) % sec == 0;
    }
}