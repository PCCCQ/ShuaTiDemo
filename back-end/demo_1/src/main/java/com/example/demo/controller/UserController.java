package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import lombok.SneakyThrows;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

//    @RequestMapping(method = RequestMethod.GET,path = "/login")
//    public String login(String phone,String password){
//        User user = userMapper.selectByUserPhoneAndPassword(phone, password);
//        if(user==null){
//            return "err";
//        }else {
//            System.out.print(user);
//            return "success";
//        }
//
//    }

//    @GetMapping("/testGetJson")
//    public Map<String,Object> testGetJson(){
//        Map<String,Object> result = new HashMap<String,Object>();
//        result.put("errorCode",0);
//        result.put("errorMeg","成功");
//        return result;
//
//    }


    //    登录接口
    @SneakyThrows
    @PostMapping("/api/login")
    public String login(@RequestBody User userdata) {
        User user = userMapper.selectByUserPhoneAndPassword(userdata.getPhone(), userdata.getPassword());
        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        if (user != null) {
            result.put("code", 200);
            result.put("data", user);
        } else {
            result.put("code", 300);
        }
        System.out.print(result);
        return result.toJSONString();

    }

    //    注册接口
    @SneakyThrows
    @PostMapping("/api/register")
    public String register(@RequestBody User userdata) {
        try {
            int isSuccess = userMapper.insertNewUser(userdata.getUuid(), userdata.getPhone(), userdata.getPassword());
            JSONObject result = new JSONObject();
            if (isSuccess == 1) {
                result.put("code", 200);
                result.put("data", "成功");
            } else {
                result.put("code", 300);
                result.put("data", "失败");
            }
            System.out.print(result);
            return result.toJSONString();
        } catch (Exception e) {
//            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("code", 400);
            result.put("data", "失败");
            return result.toJSONString();
        }


    }


}
