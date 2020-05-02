package com.guli.teacher.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.exception.GuliException;
import com.guli.common.result.R;
import com.guli.teacher.entity.EduTeacher;
import com.guli.teacher.entity.query.TeacherQuery;
import com.guli.teacher.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author huaan
 * @since 2019-08-30
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    /**
     * 讲师列表
     */
    @ApiOperation(value = "讲师列表查询")
    @GetMapping("list")
    public R list(){
        int i = 1/0;
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    /**
     * {} 占位符可以接收参数
     * @PathVariable 是通过占位符中的形参名来映射到方法的形参中；
     * 什么情况需要@PathVariable(value) ： 当占位符中的形参名和方法中的形参名不一致的情况需要执行名称映射；
     * 什么情况下不需要写value值；当占位符中的形参名和方法中的形参名一致的情况不需要写；
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name="id", value = "讲师ID", required = true)
            @PathVariable("id")  String id){
        try {
            teacherService.removeById(id);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }

    }

    @ApiOperation("分页查询")
    @GetMapping("{page}/{limit}")
    public R getTeacherByPage(
            @ApiParam(name="page", value = "当前页码", required = true)
            @PathVariable("page") Integer page,
            @ApiParam(name="limit", value = "每页显示记录数" ,required = true)
            @PathVariable("limit") Integer limit){

        //创建的时候两个参数：1、当前页码数；2、每页显示记录数
        Page<EduTeacher> teacherPage = new Page<>(page, limit);
        //两个条件：1、Page对象；2、条件查询对象Wrapper
        teacherService.page(teacherPage, null);
        long total = teacherPage.getTotal();
        List<EduTeacher> records = teacherPage.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }

    /**
     * 根据什么条件查询
     * 根据讲师名称查询， 讲师等级， 创建时间的先后
     * 需要创建一个查询对象： teacherName， teacherLevel， createTime
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation("条件分页查询")
    @PostMapping("{page}/{limit}")
    public R getTeacherListByPage(
            @ApiParam(name="page", value = "当前页码", required = true)
            @PathVariable("page") Integer page,
            @ApiParam(name="limit", value = "每页显示记录数" ,required = true)
            @PathVariable("limit") Integer limit,
            @RequestBody TeacherQuery query){
        Page<EduTeacher> teacherPage = new Page<>(page, limit);
        teacherService.teacherByPage(teacherPage, query);

        return R.ok()
                .data("total", teacherPage.getTotal())
                .data("rows", teacherPage.getRecords());
    }

    @ApiOperation("讲师新增")
    @PostMapping("save")
    public R save(@RequestBody EduTeacher teacher){
        boolean save = teacherService.save(teacher);
        return R.ok();
    }

    @ApiOperation("根据讲师ID查询讲师信息")
    @GetMapping("{id}")
    public R getTeacherById(@PathVariable("id") String id){
        EduTeacher teacher = teacherService.getById(id);
        if(teacher == null){
            throw new GuliException(20002, "此讲师不存在");
        }
        return R.ok().data("item", teacher);
    }

    @ApiOperation("讲师修改")
    @PutMapping("update")
    public R update(@RequestBody EduTeacher teacher){
        boolean b = teacherService.updateById(teacher);
        return R.ok();
    }

}
