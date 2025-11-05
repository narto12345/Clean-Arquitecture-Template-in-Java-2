package application.common;

import domain.validation.Notification;

public class Result<T> {
    private final T value;
    private final Notification notification;
    private final boolean success;

    private Result(T value, Notification notification, boolean success) {
        this.value = value;
        this.notification = notification;
        this.success = success;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, null, true);
    }

    public static <T> Result<T> failure(Notification notification) {
        return new Result<>(null, notification, false);
    }

    public boolean isSuccess() { return success; }
    public T getValue() { return value; }
    public Notification getNotification() { return notification; }
}
