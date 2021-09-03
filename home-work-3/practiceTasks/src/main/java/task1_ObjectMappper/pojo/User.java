package task1_ObjectMappper.pojo;

import java.util.Objects;

public class User {
    private long id;
    private String imageUrl;
    private String name;
    private String nick;
    private String description;
    private String location;
    private int followingCount;
    private int followersCount;

    public User(long id, String imageUrl, String name, String nick, String description, String location, int followingCount, int followersCount) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.nick = nick;
        this.description = description;
        this.location = location;
        this.followingCount = followingCount;
        this.followersCount = followersCount;
    }

    public long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getNick() {
        return nick;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && followingCount == user.followingCount && followersCount == user.followersCount && imageUrl.equals(user.imageUrl) && name.equals(user.name) && nick.equals(user.nick) && description.equals(user.description) && location.equals(user.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageUrl, name, nick, description, location, followingCount, followersCount);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", followingCount=" + followingCount +
                ", followersCount=" + followersCount +
                '}';
    }
}
