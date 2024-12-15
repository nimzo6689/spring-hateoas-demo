package demo.hateoas.presentation.model;

import org.springframework.hateoas.RepresentationModel;

import demo.hateoas.domain.User;

public class UserModel extends RepresentationModel<UserModel> {

    private final Long id;
    private final String name;
    private final String email;

    public UserModel(User user) {
        this.id = user.id();
        this.name = user.name();
        this.email = user.email();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
