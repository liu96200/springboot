package asd;

import com.forte.util.Mock;
import com.forte.util.mockbean.MockObject;
import com.luopeng.demo.entity.Data;

import java.util.*;

/**
 * @program: demo
 * @description: 测试线程
 * @author: Mr.Liu
 * @create: 2020-05-21 19:29
 **/

public class Main {
    public static void main(String[] args) throws InterruptedException {

        while (true) {
            Map<String, Object> template = new HashMap<>();// 准备mock模板载体
            //wd 是一个20-25之间的随机数。
            template.put("wd|25-30", 0);
            //sd 是一个50-55之间的随机数。
            template.put("sd|60-65", 0);
            Mock.reset(Data.class, template);
            MockObject<Data> mockData = Mock.get(Data.class);//mock生成数据准备
            Data data = mockData.getOne();//得到一个mock数据
            System.out.println("随机生成MOCK温湿度数据" + data.toString());
            Thread.currentThread();
            Thread.sleep(2333);        //延迟2333毫秒
        }
    }
}

