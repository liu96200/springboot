package com.luopeng.demo.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "data") //@Table来指定和哪个数据表对应;如果省略默认表名就是user；
public class Data {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;
    @Column
    private String sd;
    @Column //省略默认列名就是属性名
    private String wd;
    public Data(String wd,String sd ) {

            this.wd=wd;
            this.sd=sd;
    }
    public Data(){}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", sd='" + sd + '\'' +
                ", wd='" + wd + '\'' +
                '}';
    }
}
