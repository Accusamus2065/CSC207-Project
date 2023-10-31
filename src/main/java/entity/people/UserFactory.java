package entity.people;

public interface UserFactory {
    User create(String name, String password);
}
