package co.edu.javeriana.ctai.tgsecurity.patterns.builder;

import co.edu.javeriana.ctai.tgsecurity.patterns.model.User;

public class UserBuilder implements IBuilder<User>{

    private String username;
    private String password;

    public UserBuilder() {
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }
    @Override
    public User build() {
        return new User(username, password);
    }
}