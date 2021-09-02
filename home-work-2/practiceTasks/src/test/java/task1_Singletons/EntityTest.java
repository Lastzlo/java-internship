package task1_Singletons;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    @Test
    void testEquals() {
        Entity entity = new Entity("name");
        Entity entity2 = new Entity("name");
        Entity entity3 = entity;

        Assertions.assertNotEquals(entity.hashCode(), entity2.hashCode());
        Assertions.assertNotEquals(entity, entity2);

        Assertions.assertEquals(entity.hashCode(), entity3.hashCode());
        Assertions.assertEquals(entity, entity3);

    }
}