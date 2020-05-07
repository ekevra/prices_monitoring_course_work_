package com.lizavetakeura.api.dao;

import com.lizavetakeura.model.Cred;

public interface ICredDao extends GenericDao<Cred> {

    public Cred getByLogin(String login);
}
