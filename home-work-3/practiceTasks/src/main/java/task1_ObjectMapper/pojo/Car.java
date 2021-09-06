package task1_ObjectMapper.pojo;

import task1_ObjectMapper.util.Ignore;
import task1_ObjectMapper.util.NewName;

import java.util.Objects;

public class Car {
    @NewName(name = "owner")
    private User carOwner;
    @NewName(name = "model")
    private String carModel;
    @NewName(name = "number")
    private Integer carNumber;

    private double fuelTank;
    private String info;

    public Car(User carOwner, String carModel, Integer carNumber, double fuelTank, String info) {
        this.carOwner = carOwner;
        this.carModel = carModel;
        this.carNumber = carNumber;
        this.fuelTank = fuelTank;
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.fuelTank, fuelTank) == 0 && carOwner.equals(car.carOwner) && carModel.equals(car.carModel) && carNumber.equals(car.carNumber) && info.equals(car.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carOwner, carModel, carNumber, fuelTank, info);
    }
}
