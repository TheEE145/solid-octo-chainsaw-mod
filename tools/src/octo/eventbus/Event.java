package octo.eventbus;

import arc.func.Cons;
import arc.struct.Seq;

import octo.annotations.Cancelable;
import org.jetbrains.annotations.Contract;

@SuppressWarnings("unchecked")
public @Cancelable class Event<T> implements IEvent<T> {
    protected boolean canceled = false;

    private final Seq<Cons<T>> events = new Seq<>();
    public final Class<T> type;

    @Contract(pure = true)
    public Event(Class<T> type) {
        this.type = type;
    }

    @Override
    public Class<T> type() {
        return this.type;
    }

    @Override
    public void post(T type) {
        if(!this.canceled() && this.events.any()) {
            this.events.forEach(elem -> {
                elem.get(type);
            });
        }
    }

    @Override
    public boolean dispose() {
        if(this.events.isEmpty()) {
            return false;
        }

        this.events.clear();
        return true;
    }

    @Override
    public void unsafePost(Object obj) throws ClassCastException {
        if(!this.canceled() && this.events.any()) {
            this.events.forEach(elem -> {
                elem.get((T) obj);
            });
        }
    }

    @Override
    public Seq<Cons<T>> getEvents() {
        return this.events;
    }

    @Override
    public IEvent<T> addEventListener(Runnable runnable) {
        return this.addEventListener(ignored -> {
            if(runnable != null) {
                runnable.run();
            }
        });
    }

    @Override
    public IEvent<T> addEventListener(Cons<T> cons) {
        if(cons != null) {
            this.events.add(cons);
        }

        return this;
    }

    @Override
    public boolean removeEventListener(Cons<T> cons) {
        if(cons == null || this.events.isEmpty()) {
            return false;
        }

        this.events.remove(cons);
        return true;
    }

    @Override
    public void canceled(boolean canceled) {
        if(this.cancelable()) {
            this.canceled = canceled;
        } else {
            throw new RuntimeException(
                    new IllegalAccessException()
            );
        }
    }

    @Override
    public boolean canceled() {
        return this.canceled;
    }
}