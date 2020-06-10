package com.luopeng.demo.dao;

import com.luopeng.demo.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataDao extends JpaRepository<Data,Integer> {

}
