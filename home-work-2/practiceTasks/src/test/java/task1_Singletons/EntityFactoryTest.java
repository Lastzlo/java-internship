package task1_Singletons;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityFactoryTest {

    private static final String NAME = "any name";

    @Test
    void getInstance() {
        Entity entity = EntityFactory.getInstance(NAME);
        Entity entity2 = EntityFactory.getInstance(NAME);

        Assertions.assertEquals(entity, entity2);
    }
}