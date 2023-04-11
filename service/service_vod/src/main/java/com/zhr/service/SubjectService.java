package com.zhr.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zhr.model.vod.Subject;

import java.util.List;

/**
 * @author 20179
 */
public interface SubjectService extends IService<Subject> {

    List<Subject> selectSubjectList(Long id);
}
