package com.hxd.service.impl;

import com.hxd.dao.PersonDAO;
import com.hxd.entity.Person;
import com.hxd.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAO personDAO;

    public Long savePerson() {
        Person person = new Person();
        person.setUsername("HXD");
        person.setPhone("15594156771");
        person.setAddress("BJ");
        person.setRemark("Test");
        return personDAO.save(person);
    }
}
