package com.guli.teacher.entity.query;

import lombok.Data;

@Data
public class TeacherQuery {

    //根据讲师名称查询的属性
    private String name;
    // 根据讲师等级查询的属性
    private Integer level;
    // 起始时间查询
    private String begin;
    // 结束时间查询
    private String end;

}
