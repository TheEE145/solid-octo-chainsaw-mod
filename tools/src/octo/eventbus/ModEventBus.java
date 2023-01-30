package octo.eventbus;

import arc.struct.Seq;

public class ModEventBus implements IModEventBus {
    protected final Seq<IEvent<?>> events = new Seq<>();

    @Override
    public IEvent<?> get(Class<?> tClass) {
        return this.events.find(event -> {
            return event.type().equals(tClass);
        });
    }

    @Override
    public <T> IEvent<T> create(Class<T> tClass) {
        Event<T> event = new Event<>(tClass);
        this.events.add(event);
        return event;
    }

    @Override
    public IEvent<?> remove(Class<?> tClass) {
        IEvent<?> toRemove = this.events.find(event -> {
            return event.type().equals(tClass);
        });

        if(toRemove != null) {
            this.events.remove(toRemove);
        }

        return toRemove;
    }

    @Override
    public Seq<IEvent<?>> events() {
        return this.events;
    }

    @Override
    public void clear() {
        this.events.clear();
    }
}