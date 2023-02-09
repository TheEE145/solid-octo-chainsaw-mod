package octo.core.events;

import arc.func.Cons;
import arc.struct.Seq;

import java.util.Objects;
import java.util.function.Supplier;

public interface IEvent<T> {
    Class<T> type();

    void post(T type);
    boolean dispose();
    void unsafePost(Object obj) throws ClassCastException;

    Seq<Cons<T>> getEvents();
    IEvent<T> addEventListener(Runnable runnable);

    IEvent<T> addEventListener(Cons<T> cons);
    boolean removeEventListener(Cons<T> cons);

    default void canceled(boolean canceled) {
        //...
    }

    default boolean canceled() {
        return false;
    }

    default void canceled(Supplier<Boolean> canceled) {
        Objects.requireNonNull(canceled);
        this.canceled(canceled.get());
    }

    default boolean cancelable() {
        return this.getClass().isAnnotationPresent(Cancelable.class);
    }
}