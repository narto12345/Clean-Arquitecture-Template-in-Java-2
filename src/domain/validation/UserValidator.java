package domain.validation;

import domain.model.User;

public class UserValidator {
    public Notification validate(User user) {
        Notification notification = new Notification();

        if (user.getName() == null || user.getName().isBlank()) {
            notification.addError("Name cannot be empty");
        }

        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            notification.addError("Invalid email format");
        }

        if (user.isUnderage()) {
            notification.addError("User must be at least 18 years old");
        }

        return notification;
    }
}