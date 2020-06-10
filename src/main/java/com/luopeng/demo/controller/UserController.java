package com.luopeng.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forte.util.Mock;
import com.forte.util.mockbean.MockObject;
import com.luopeng.demo.GuYuNbiot.GyRecieveThread;
import com.luopeng.demo.GuYuNbiot.Gysendstr;
import com.luopeng.demo.entity.Data;
import com.luopeng.demo.entity.User;
import com.luopeng.demo.service.DataServiceImpl;
import com.luopeng.demo.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    DataServiceImpl dataService;






    private List<Data> dataList;



    private Logger logger = Logger.getLogger(UserController.class);
    @PostMapping(value = "/login")//登录操作响应
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        User user1 =userServiceImpl.getUser(password,username);
        if(user1 == null){
            //登陆失败
            map.put("msg","用户名密码错误");
            return  "index";
        }else{ //登陆成功，防止表单重复提交，可以重定向到主页
            session.setAttribute("username",username);
            new Gysendstr("").start();
//            ep=7V57U8DBRNKCFM2M&pw=123456
            map.put("msg",GyRecieveThread.regist());
            return "redirect:/dashboard";
        }

    }
    @RequestMapping("/index")//返回首页未登录状态响应
    public String index(HttpSession session){
            session.invalidate();//清空session并返回主页，即退出
            return  "index";
    }

    @RequestMapping("/regist")//返回注册界面响应
    public String regist(){
        return "regist";
    }

    @RequestMapping("/list")//返回员工列表响应
    public String list(Model model){
        List<User> userList=userServiceImpl.getAll();
        model.addAttribute("userList",userList);
        return "list";
    }
    @RequestMapping("/dashboard")//返回主页响应
    public String dashboard(Map<String,Object> map){
        map.put("msg",GyRecieveThread.regist());
        return "dashboard";
    }
    @RequestMapping("/offer")
    public String Offer(Map<String,Object> map, HttpSession session){
        session.setAttribute("username","offer");
        new Gysendstr("").start();
//        ep=7V57U8DBRNKCFM2M&pw=123456
        map.put("msg",GyRecieveThread.regist());
        Map<String, Object> template = new HashMap<>();// 准备mock模板载体
        //wd 是一个20-25之间的随机数。
        template.put("wd|25-30", 0);
        //sd 是一个50-55之间的随机数。
        template.put("sd|60-65", 0);
        Mock.reset(Data.class,template);
        return "redirect:/dashboard";
    }

    @PostMapping("/ShowInfoIndexServlet")//html页面的ajax数据的查询，返回json数据
    @ResponseBody
    public String ShowInfoIndexServlet() throws JsonProcessingException{

        MockObject<Data> mockData = Mock.get(Data.class);//mock生成数据准备
        Data data = mockData.getOne();//得到一个mock数据
        dataService.insertData(data);//数据库插入
        System.out.println("随机生成MOCK温湿度数据"+data.toString());
        System.out.println("读取温湿度数据");
        dataList=dataService.readData();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dataList);
        return json;
    }
    @RequestMapping("/humidity")//返回湿度查看页面响应
    public String humidity(Map<String,Object> map){
        map.put("msg",GyRecieveThread.regist());
        return "humidity";
    }
//    @RequestMapping("/threshold")//返回阈值设置页面
//    public String threshold(Map<String,Object> map){
//        dataList=dataService.readData();
//        map.put("temMsg",dataList.get(dataList.size()-1).getWd());
//        map.put("humMsg",dataList.get(dataList.size()-1).getSd());
//        return "threshold";
//    }

    @PostMapping(value = "/doRegist")//注册操作响应
    public String doRegist(User user, Map<String,Object> map){
        String usernamePattern = "^[a-zA-Z0-9_]{4,16}$";
        String passwordPattern = "^[a-zA-Z0-9]{2,}$";
       // String emailPattern="^\\\\s*\\\\w+(?:\\\\.{0,1}[\\\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\\\.[a-zA-Z]+\\\\s*$";
        String telPattern="^1(3|4|5|7|8|9)\\d{9}$";
        if (userServiceImpl.UserJudge(user)){
            map.put("msg","用户名已经存在");
            return "regist";
        }
        else if (user.getUsername().matches(usernamePattern)==false){
            map.put("msg","注册用户名4到16位，支持字母大小写，数字或下划线");
            return "regist";
        }
        else if (user.getPassword().matches(passwordPattern)==false){
            map.put("msg","密码最少为2位，支持字母大小写，数字");

            return "regist";
        }
//        else if (user.getEmail().matches(emailPattern)==false){
//            map.put("msg","邮箱格式错误");
//            System.out.println(user.getEmail());
//            return "regist";
//        }
        else if (user.getTel().matches(telPattern)==false){
            map.put("msg","需为11位的真实号码");
            return "regist";
        }
        else {
            userServiceImpl.insertUser(user);
            map.put("msg", "注册成功");
            return "index";
        }
    }
    @RequestMapping("/demo")//测试页
    public String myDemo(){
        return "test";
    }

}