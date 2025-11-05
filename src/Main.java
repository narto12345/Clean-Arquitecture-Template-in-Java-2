import Infrastructure.repository.FictitiousUserRepository;
import application.command.user.CreateUserCommand;
import application.common.Result;
import application.usecase.user.CreateUserUseCase;
import domain.model.User;
import domain.repository.UserRepository;

void main() {
    UserRepository repository = new FictitiousUserRepository();
    CreateUserUseCase useCase = new CreateUserUseCase(repository);

    CreateUserCommand userCommand = new CreateUserCommand(
            "pedro",
            "pedro123@hotmail.com",
            LocalDate.of(1996, 2, 18)
    );

    Result<User> result = useCase.execute(userCommand);

    if (!result.isSuccess()) {
        IO.println(result.getNotification().getErrors());
    }

    if (result.isSuccess()) {
        IO.println("User created: " + result.getValue());
    }
}
