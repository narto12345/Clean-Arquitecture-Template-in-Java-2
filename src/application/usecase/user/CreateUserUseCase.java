package application.usecase.user;

import application.command.user.CreateUserCommand;
import application.common.Result;
import domain.model.User;
import domain.repository.UserRepository;
import domain.validation.Notification;
import domain.validation.UserValidator;

import java.util.Optional;
import java.util.UUID;

public class CreateUserUseCase {
    private final UserRepository userRepository;
    private final UserValidator validator;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.validator = new UserValidator();
    }

    public Result<User> execute(CreateUserCommand command) {
        User user = new User(UUID.randomUUID().toString(),
                command.getName(),
                command.getEmail(),
                command.getBirthDate());

        Notification notification = validator.validate(user);

        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        Optional<User> existing = userRepository.findByEmail(command.getEmail());
        if (existing.isPresent()) {
            notification.addError("Email already registered");
            return Result.failure(notification);
        }

        User saved = userRepository.save(user);
        return Result.success(saved);
    }
}