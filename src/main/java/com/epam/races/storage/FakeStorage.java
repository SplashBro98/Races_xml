package com.epam.races.storage;

import com.epam.races.entity.User;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class FakeStorage {
    private static FakeStorage instance;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();

    private Set<User> users = new HashSet<>();

    private FakeStorage(){
        users.add(new User("Marsel","qwerty007"));
        users.add(new User("Steven","istambul2005"));
        users.add(new User("Ivan","java_advanced"));
    }

    public static FakeStorage getInstance(){
        if(!isCreated.get()){
            try {
                lock.lock();
                if(instance == null){
                    instance = new FakeStorage();
                    isCreated.set(true);
                }
            }finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public void addUser(User user){
        users.add(user);
    }

    public boolean isPresent(User user){
        boolean result = false;
        if(users.contains(user)){
            result = true;
        }
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        if (instance != null) {
            throw new CloneNotSupportedException();
        }
        return super.clone();
    }

}
