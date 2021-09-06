package task1_ObjectMapper.dto;

import task1_ObjectMapper.pojo.User;

import java.util.Objects;

public class CarDTO {

    private User owner;
    private String model;
    private Integer number;

    public CarDTO() {
    }

    public CarDTO(User owner, String model, Integer number) {
        this.owner = owner;
        this.model = model;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return owner.equals(carDTO.owner) && model.equals(carDTO.model) && number.equals(carDTO.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, model, number);
    }
}
