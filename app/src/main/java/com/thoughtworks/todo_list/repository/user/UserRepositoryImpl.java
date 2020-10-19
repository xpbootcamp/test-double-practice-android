package com.thoughtworks.todo_list.repository.user;

import com.thoughtworks.todo_list.repository.user.entity.User;
import com.thoughtworks.todo_list.ui.login.UserRepository;

import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserRepositoryImpl implements UserRepository {
    private LocalUserDataSource localUserDataSource;
    private RemoteUserDataSource remoteUserDataSource;

    public UserRepositoryImpl(LocalUserDataSource localUserDataSource, RemoteUserDataSource remoteUserDataSource) {
        this.localUserDataSource = localUserDataSource;
        this.remoteUserDataSource = remoteUserDataSource;
    }

    public Maybe<User> findByName(String name) {
        AtomicReference<Disposable> disposable = new AtomicReference<>();
        return localUserDataSource.findByName(name).switchIfEmpty(remoteUserDataSource.findByName(name).doOnSuccess(user -> {
            disposable.set(localUserDataSource.save(user).subscribe());
        }).doOnDispose(() -> {
            if (disposable.get() != null) {
                disposable.getAndSet(null).dispose();
            }
        })).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable save(User user) {
        return localUserDataSource.save(user);
    }

    /*待完成部分*/
    @Override
    public Maybe<User> register(String username, String password) {
        throw new RuntimeException();
    }
}