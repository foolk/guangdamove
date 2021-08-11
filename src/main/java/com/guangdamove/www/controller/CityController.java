package com.guangdamove.www.controller;

import com.guangdamove.www.entity.TCity;

import com.guangdamove.www.mapper.TCityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
@CrossOrigin
public class CityController {

    @Autowired
    private TCityMapper mapper;

    @RequestMapping(value = "getCity",method = RequestMethod.POST)
    public List<TCity> getCity(TCity city){
        System.out.println(city.getPid());
       return mapper.queryListForPid(city);
    }
}
