package com.lizavetakeura.api.service;

public interface IUserService<T> {

    public void createUser(T credDTO);

    public void updateUser(T credDTO);
}
