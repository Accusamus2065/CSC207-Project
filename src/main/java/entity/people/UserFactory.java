package entity.people;

public interface UserFactory {
    User create(String username, String password);
}
