package task1_Singletons;

import java.util.Objects;

//Entity than would be return Singleton
public class Entity {

    private String name;
    private int rndValue;   //random value 0...1000

    public Entity(String name) {
        this.name = name;
        this.rndValue = ((int) (Math.random() * 1000)); //random value 0...1000
    }

    public String getName() {
        return name;
    }

    public int getRndValue() {
        return rndValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return rndValue == entity.rndValue && Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rndValue);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", rndValue=" + rndValue +
                '}';
    }
}
