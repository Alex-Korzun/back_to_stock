package example.model;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final List<User> highPriorityUsers = new ArrayList<>();
    private final List<User> mediumPriorityUsers = new ArrayList<>();
    private final List<User> lowPriorityUsers = new ArrayList<>();

    public List<User> getHighPriorityUsers() {
        return highPriorityUsers;
    }

    public List<User> getMediumPriorityUsers() {
        return mediumPriorityUsers;
    }

    public List<User> getLowPriorityUsers() {
        return lowPriorityUsers;
    }
}
