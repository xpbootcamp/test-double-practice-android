package com.thoughtworks.todo_list.repository.user;


import com.thoughtworks.todo_list.repository.user.entity.User;

import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface LocalUserDataSource {
    Maybe<User> findByName(String userId);
    Completable save(User user);
}
