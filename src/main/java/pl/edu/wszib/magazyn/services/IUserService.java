package pl.edu.wszib.magazyn.services;

import pl.edu.wszib.magazyn.model.User;

public interface IUserService {
    void authenticate(User user);
    void logout();
}
