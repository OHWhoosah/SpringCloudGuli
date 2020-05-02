package com.guli.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.teacher.entity.EduTeacher;
import com.guli.teacher.entity.query.TeacherQuery;
import com.guli.teacher.mapper.EduTeacherMapper;
import com.guli.teacher.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author huaan
 * @since 2019-08-30
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void teacherByPage(Page<EduTeacher> teacherPage, TeacherQuery query) {

        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        //1、判断条件是否为空
        if(query == null){
            //1.1 如果没空， 直接用null（没有条件）查询
            baseMapper.selectPage(teacherPage, queryWrapper);
            return ;
        }

        //2、获取query中的条件
        String name = query.getName();
        Integer level = query.getLevel();
        String begin = query.getBegin();
        String end = query.getEnd();

        //3、判断条件属性是否存在
        if(!StringUtils.isEmpty(name)){
            //4、如果存在放在wrapper中
            queryWrapper.like("name", name);
        }
        if(!StringUtils.isEmpty(level)){
            queryWrapper.eq("level", level);
        }
        if(!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create", begin);
        }
        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create", end);
        }
        //5、如果不存在，不放wrapper中

        //6、根据条件查询
        baseMapper.selectPage(teacherPage, queryWrapper);

    }
}
