package com.lizavetakeura.service;

import com.lizavetakeura.api.dao.ICredDao;
import com.lizavetakeura.api.dao.IUserDao;
import com.lizavetakeura.api.service.IUserService;
import com.lizavetakeura.model.Cred;
import com.lizavetakeura.model.User;
import com.lizavetakeura.service.dto.CredDTO;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements IUserService<CredDTO> {

    private static final Logger logger = LogManager.getLogger(CategoryService.class);
    private IUserDao userDao;
    private ICredDao credDao;

    public UserService(){

    }

    @Autowired
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setCredDao(ICredDao credDao) {
        this.credDao = credDao;
    }

    @Transactional
    public void createUser(CredDTO credDTO){
        ModelMapper modelMapper = new ModelMapper();
        Cred cred = modelMapper.map(credDTO, Cred.class);
        try{
            userDao.create(cred.getUser());
            credDao.create(cred);
        }
        catch (HibernateException e){
            logger.log(Level.ERROR, "Creating failed." + e.getMessage());
            throw new ServiceException("Creating failed.", e.getCause());
        }
    }

    @Transactional
    public void updateUser(CredDTO credDTO){
        ModelMapper modelMapper = new ModelMapper();
        Cred newCred = modelMapper.map(credDTO, Cred.class);
        Cred oldCred = credDao.getByLogin(newCred.getLogin());
        User newUser = modelMapper.map(credDTO.getUser(), User.class);
        User oldUser = userDao.getById(newUser.getId());
        if(oldUser != null){
            oldUser = newUser;
            try{
                userDao.update(oldUser);
            }
            catch (HibernateException e){
                logger.log(Level.ERROR, "Updating failed." + e.getMessage());
                throw new ServiceException("Updating failed.", e.getCause());
            }
        }
        if(oldCred != null){
            oldCred = newCred;
            try{
                credDao.update(oldCred);
            }
            catch (HibernateException e){
                logger.log(Level.ERROR, "Updating failed." + e.getMessage());
                throw new ServiceException("Updating failed.", e.getCause());
            }
        }

    }
}
