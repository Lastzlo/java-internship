package task1_Singletons;

// Thread safe singleton with lazy initialization.
// Not safe with deserialization.

public class EntityFactory {

    private static volatile Entity instance;

    public static Entity getInstance(String name) {
        Entity localInstance = instance;
        if (localInstance == null) {
            synchronized (Entity.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Entity(name);
                }
            }
        }
        return localInstance;
    }

}
