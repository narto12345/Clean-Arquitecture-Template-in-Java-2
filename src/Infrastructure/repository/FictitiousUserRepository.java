package Infrastructure.repository;

import domain.model.User;
import domain.repository.UserRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FictitiousUserRepository implements UserRepository {
    private final List<User> users = Arrays.asList(
            new User(UUID.randomUUID().toString(), "nico", "nicosan12@hotmail.com", LocalDate.now()),
            new User(UUID.randomUUID().toString(), "pedro", "pedro@hotmail.com", LocalDate.now()),
            new User(UUID.randomUUID().toString(), "juan", "juan@hotmail.com", LocalDate.now())
    );

    @Override
    public User save(User user) {
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }
}
