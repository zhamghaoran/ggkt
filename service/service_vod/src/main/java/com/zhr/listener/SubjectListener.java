package com.zhr.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zhr.mapper.SubjectMapper;
import com.zhr.model.vod.Subject;
import com.zhr.vo.vod.SubjectEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectListener extends AnalysisEventListener<SubjectEeVo> {
    @Autowired
    private SubjectMapper subjectMapper;

    // 一行一行进行读取
    @Override
    public void invoke(SubjectEeVo data, AnalysisContext context) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(data,subject);
        subjectMapper.insert(subject);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }



}
