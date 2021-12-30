package Core;//package com.company;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserNotificationManager <T> {

    // t is the thing the person will be notified about
    private Map<T, List<User>> listeners = new HashMap<>();

    public UserNotificationManager(T... types) {
        for (T type : types) {
            this.listeners.put(type, new ArrayList<>());
        }
    }

    public  void setListeners(T type)
    {
        this.listeners.put(type, new ArrayList<>());
    }

    public List<User> getListeners(T type)
    {
        return this.listeners.get(type);
    }
    public void subscribe(T type, User user) {
        List<User> users = listeners.get(type);
        users.add(user);
    }

    public void unsubscribe(T type, User user) {
        List<User> users = listeners.get(type);
        users.remove(user);
    }

    public void notify(T type, T data) {
        List<User> users = listeners.get(type);
        for (User user : users) {
            user.update(type, data);
        }
    }
}





