package iterator;

import java.util.Iterator;
import java.util.List;
import users.User;

public class NotificationIterator implements Iterable<User> {
    private final List<User> users;

    public NotificationIterator(List<User> users) {
        this.users = users;
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }
} 