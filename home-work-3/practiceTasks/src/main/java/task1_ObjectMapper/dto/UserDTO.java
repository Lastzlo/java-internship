package task1_ObjectMapper.dto;

import task1_ObjectMapper.pojo.User;

import java.util.Objects;

public class UserDTO {
    private long id;
    private String nick;
    private String pictureUrl;

    public UserDTO() {
    }

    public UserDTO(long id, String nick, String pictureUrl) {
        this.id = id;
        this.nick = nick;
        this.pictureUrl = pictureUrl;
    }

    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getId(), user.getNick(), user.getImageUrl());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id && nick.equals(userDTO.nick) && pictureUrl.equals(userDTO.pictureUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nick, pictureUrl);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }
}
