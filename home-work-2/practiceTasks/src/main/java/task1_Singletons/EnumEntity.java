package task1_Singletons;

// Thread safe.
// Deserialization safe.
// Reflection safe.

public enum EnumEntity {
    INSTANCE;

    private final Entity entity = new Entity("name");

    public Entity getEntity() {
        return entity;
    }

}
