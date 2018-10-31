package com.rifu.springbootdemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${hoster}")
    private String rifu;

    @Value("${get-hoster")
    private String get_hoster;

    @Autowired
    private GirlRespository girlRespository;

    @Autowired
    private SpringbootDemoProperties properties;

    @RequestMapping(name="hello",method = RequestMethod.GET)
    public String say(){
        return properties.getName();
    }

    @PostMapping(name="save")
    public Girl saveGirl(Girl girl){
        return (Girl)girlRespository.save(girl);
    }
}
