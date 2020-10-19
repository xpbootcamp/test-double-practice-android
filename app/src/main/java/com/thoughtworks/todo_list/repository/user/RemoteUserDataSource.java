package com.thoughtworks.todo_list.repository.user;


import com.thoughtworks.todo_list.repository.user.entity.User;
import com.thoughtworks.todo_list.utils.HttpUtils;
import com.thoughtworks.todo_list.utils.JsonUtils;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

public class RemoteUserDataSource {

    public Maybe<User> findByName(String name) {
        return Flowable.fromFuture(HttpUtils.getString("https://twc-android-bootcamp.github.io/fake-data/data/user.json"))
                .map(response -> JsonUtils.from(response, User.class)).filter(user -> user.getName().equals(name)).firstElement();
    }
}
