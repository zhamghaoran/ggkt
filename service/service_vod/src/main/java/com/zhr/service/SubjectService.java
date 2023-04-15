package com.zhr.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zhr.model.vod.Subject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 20179
 */
public interface SubjectService extends IService<Subject> {

    List<Subject> selectSubjectList(Long id);

    void exportData(HttpServletResponse httpServletResponse);

    void importData(MultipartFile file);
}
