package com.thoughtworks.todo_list;

import android.app.Application;

import androidx.room.Room;

import com.thoughtworks.todo_list.repository.AppDatabase;
import com.thoughtworks.todo_list.repository.user.LocalUserDataSource;
import com.thoughtworks.todo_list.repository.user.RemoteUserDataSource;
import com.thoughtworks.todo_list.repository.user.UserRepositoryImpl;
import com.thoughtworks.todo_list.ui.login.UserRepository;

public class MainApplication extends Application {
    private UserRepository userRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        userRepository = new UserRepositoryImpl(localUserDataSource(), remoteUserDataSource());
    }


    public LocalUserDataSource localUserDataSource() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, this.getClass().getSimpleName()).build();
        return db.userDBDataSource();
    }

    public RemoteUserDataSource remoteUserDataSource() {
        return new RemoteUserDataSource();
    }

    public UserRepository userRepository() {
        return userRepository;
    }
}
