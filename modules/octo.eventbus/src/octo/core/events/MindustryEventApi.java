package octo.core.events;

import arc.Events;
import mindustry.game.EventType;

public class MindustryEventApi extends ModEventBus {
    public static final MindustryEventApi bus = new MindustryEventApi();

    static {
        for(Class<?> event : EventType.class.getClasses()) {
            IEvent<?> iEvent = bus.create(event);
            Events.on(event, iEvent::unsafePost);
        }
    }
}