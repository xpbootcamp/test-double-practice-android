package com.thoughtworks.todo_list;

import android.app.Application;

import com.thoughtworks.todo_list.repository.user.RemoteUserDataSource;
import com.thoughtworks.todo_list.repository.user.UserRepositoryImpl;
import com.thoughtworks.todo_list.ui.login.UserRepository;

import static org.mockito.Mockito.mock;

public class MainApplication extends Application {
    private UserRepository userRepository;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public UserRepository userRepository() {
        if (userRepository == null) {
            userRepository = mock(UserRepositoryImpl.class);
        }
        return userRepository;
    }

    /*待完成部分*/
    public RemoteUserDataSource remoteUserDataSource() {
        throw new RuntimeException();
    }
}
