package com.lizavetakeura.dao;

import com.lizavetakeura.api.dao.ICredDao;
import com.lizavetakeura.model.Cred;
import org.springframework.stereotype.Repository;

@Repository
public class CredDao extends AbstractDao<Cred> implements ICredDao {

    private CredDao() {
        super(Cred.class);
    }

    public Cred getByLogin(String login){
        Cred object = (Cred) entityManager.find(Cred.class, login);
        return object;
    }
}
