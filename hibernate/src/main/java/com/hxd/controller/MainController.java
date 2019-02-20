package com.hxd.controller;

import com.hxd.dao.PersonDAO;
import com.hxd.service.PersonService;
import com.hxd.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
    @Autowired
    private TestService testService;
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonDAO personDAO;

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/test1")
    public String test1(){
        return testService.test();
    }

    @RequestMapping("/test2")
    @ResponseBody
    public String savePerson(){
        Long person = personService.savePerson();
        System.out.println("ID："+person);
        return "succuss!";
    }

    @RequestMapping("/test3")
    @ResponseBody
    public String findAll(){
        personDAO.findAll();
        return "succuss--------OK!";
    }
    @RequestMapping("/test4")
    @ResponseBody
    public String update(){
        personDAO.saveOrUpdate(null);
        return "OK";
    }
}
