package com.luopeng.demo.service;

import com.luopeng.demo.dao.DataDao;
import com.luopeng.demo.entity.Data;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DataServiceImpl {
    @Autowired
    DataDao dataDao;

    private Logger logger = Logger.getLogger(DataServiceImpl.class);
    public void insertData(Data data) {
        dataDao.saveAndFlush(data);//不会保存到缓存中，立即刷新到DB
    }
    public List<Data> readData(){
        List<Data> tem=dataDao.findAll();
        logger.info("进入函数读取数据");
        return tem;
    }
}
