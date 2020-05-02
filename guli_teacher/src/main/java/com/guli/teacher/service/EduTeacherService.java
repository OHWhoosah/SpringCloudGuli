package com.guli.teacher.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.teacher.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.teacher.entity.query.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author huaan
 * @since 2019-08-30
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 根据条件分页查询
     * @param teacherPage
     * @param query
     */
    void teacherByPage(Page<EduTeacher> teacherPage, TeacherQuery query);
}
