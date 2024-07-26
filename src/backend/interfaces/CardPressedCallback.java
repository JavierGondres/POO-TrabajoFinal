package backend.interfaces;

import backend.classes.User;

public interface CardPressedCallback {
    void onCardPressed(User selectedUser);
}
