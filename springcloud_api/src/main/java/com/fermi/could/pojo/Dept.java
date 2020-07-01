package com.fermi.could.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法: dept.setDeptno(12).setDname("ff")
public class Dept implements Serializable {
    private long deptno;
    private String dname    ;

    //该数据存放于哪个数据库. 微服务: 一个服务对应一个数据库, 同一个信息可能存在于不同数据库
    private String db_source;

    public Dept(String dname, String db_source) {
        this.dname = dname;
        this.db_source = db_source;
    }
}
