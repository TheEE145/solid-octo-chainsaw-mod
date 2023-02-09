package octo.core.events;

import arc.struct.Seq;

public interface IModEventBus {
    <T> IEvent<T> create(Class<T> tClass);
    IEvent<?> remove(Class<?> tClass);
    IEvent<?> get(Class<?> tClass);

    Seq<IEvent<?>> events();
    void clear();
}