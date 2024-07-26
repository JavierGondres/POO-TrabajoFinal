package backend.interfaces;

import backend.classes.User;

public interface UserSelectionCallback {
	void onUserSelected(User selectedUser);
	default void onCardPressed(User selectedUser) {};
}
