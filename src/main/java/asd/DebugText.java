package asd;

import java.util.HashMap;

/**
 * @program: demo
 * @description: Debug测试
 * @author: Mr.Liu
 * @create: 2020-05-21 20:23
 **/

public class DebugText {
    public static void main(String[] args) {
        HashMap<String,String> map=new HashMap<>();
        map.put("name","Tom");
        map.put("age","12");
        map.put("school","SMsum");
        map.put("major","computer");

        String age=map.get("age");
        System.out.println("age="+age);

       map.remove("major");
        System.out.println(map);
        map.forEach((key, value) -> {
            System.out.println("Key: " + key + ", Value: " + value);
        });
        map.forEach((key,value)->{
            System.out.println(key+value);
        });
    }
}

























