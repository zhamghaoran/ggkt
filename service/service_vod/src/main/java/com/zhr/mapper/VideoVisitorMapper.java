package com.zhr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhr.model.vod.VideoVisitor;
import com.zhr.vo.vod.VideoVisitorCountVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 Mapper 接口
 * </p>
 *
 * @author jjking
 * @since 2023-04-21
 */
@Mapper
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {

    List<VideoVisitorCountVo> findCount(@Param("courseId") Long courseId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
