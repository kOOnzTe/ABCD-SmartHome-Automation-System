package controlPanel;

import users.User;

public interface SubjectInterface {
	void register(User user);
	void unregister(User user);
	void notifyRegisteredUsers();
}
