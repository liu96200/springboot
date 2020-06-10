package com.luopeng.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luopeng.demo.GuYuNbiot.GyRecieveThread;
import com.luopeng.demo.GuYuNbiot.Gysendstr;
import com.luopeng.demo.entity.Data;
import com.luopeng.demo.service.DataServiceImpl;
import com.luopeng.demo.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class NbController {
    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    DataServiceImpl dataService;

    @Autowired
    UserController userController;

    private Logger logger = Logger.getLogger(NbController.class);
//    @RequestMapping("/send_K")
//    public String sendK(){
//        new Gysendstr("K").start();
//        logger.info("手动发送开风扇指令");
//       return "redirect:/threshold";
//    }
//    @RequestMapping("/send_G")
//    public String sendG(){
//        new Gysendstr("G").start();
//        logger.info("手动发送关风扇指令");
//       return "redirect:/threshold";
//    }
//    @PostMapping(value ="/SetTemThreshold")
//    public String SetTemThreshold(
//            @RequestParam("temThreshold") String temThreshold,
//            Map<String,Object> map) throws JsonProcessingException {
//        map.put("msg","指令发送成功");
//        String json=userController.ShowInfoIndexServlet();
//        ObjectMapper mapper = new ObjectMapper();
//        List<Data> data=mapper.readValue(json, new TypeReference<List<Data>>() {});
//        if(temThreshold.compareTo(data.get(data.size()-1).getWd())<0) {
//            logger.info("温度太高自动发送指令");
//            map.put("msg","指令发送成功");
//            new Gysendstr("K").start();
//        }
//        return "redirect:/threshold";
//    }
//    @PostMapping(value ="/SetHumThreshold")
//    public String SetHumThreshold(
//            @RequestParam("humThreshold") String humThreshold,
//            Map<String,Object> map) throws JsonProcessingException {
//        map.put("msg","指令发送成功");
//        String json=userController.ShowInfoIndexServlet();
//        ObjectMapper mapper = new ObjectMapper();
//        List<Data> data=mapper.readValue(json, new TypeReference<List<Data>>() {});
//        if(humThreshold.compareTo(data.get(data.size()-1).getSd())<0) {
//            logger.info("湿度太高自动发送指令");
//            map.put("msg","指令发送成功");
//            new Gysendstr("K").start();
//        }
//        return "redirect:/threshold";
//    }
//    @RequestMapping("/getDevice")
//    public String getDevice(Map<String,Object> map){
//        new Gysendstr("ep=7V57U8DBRNKCFM2M&pw=123456").start();
//        map.put("msg", GyRecieveThread.regist());
//        logger.info("发送注册包");
//        return "redirect:/dashboard";
//    }

}
