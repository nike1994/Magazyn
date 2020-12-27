package pl.edu.wszib.magazyn.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.magazyn.model.User;

@Component
@SessionScope
public class SessionObj {
    private User loggedUser = null;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean isLogged() {
        return this.loggedUser != null;
    }

}
