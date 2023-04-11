package com.zhr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhr.mapper.SubjectMapper;
import com.zhr.model.vod.Subject;
import com.zhr.service.SubjectService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 20179
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public List<Subject> selectSubjectList(Long id) {
        LambdaQueryWrapper<Subject> subjectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        subjectLambdaQueryWrapper.eq(Subject::getParentId, id);
        List<Subject> subjects = baseMapper.selectList(subjectLambdaQueryWrapper);
        // 遍历每一个对象，判断是否还有孩子
        subjects.forEach(i -> {
            // 获取这个subject的id值，
            Long subjectId = i.getId();
            // 查询
            boolean isChild = this.isChild(subjectId);
            i.setHasChildren(isChild);
        });
        return subjects;
    }

    // 判断是都有下一层的数据
    private boolean isChild(Long subjectId) {
        LambdaQueryWrapper<Subject> subjectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        subjectLambdaQueryWrapper.eq(Subject::getParentId, subjectId);
        Integer count = baseMapper.selectCount(subjectLambdaQueryWrapper);
        return count > 0;
    }
}
