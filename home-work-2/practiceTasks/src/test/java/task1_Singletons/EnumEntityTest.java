package task1_Singletons;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumEntityTest {

    @Test
    void getEntity() {

        Entity entity = EnumEntity.INSTANCE.getEntity();
        Entity entity2 = EnumEntity.INSTANCE.getEntity();

        Assertions.assertEquals(entity, entity2);
    }
}