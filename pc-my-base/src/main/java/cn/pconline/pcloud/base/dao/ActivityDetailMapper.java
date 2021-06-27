package cn.pconline.pcloud.base.dao;

import cn.pconline.pcloud.base.entity.ActivityDetail;
import cn.pconline.pcloud.base.entity.ActivityDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityDetailMapper {
    int countByExample(ActivityDetailExample example);

    int deleteByExample(ActivityDetailExample example);

    int deleteByPrimaryKey(Long activityId);

    int insert(ActivityDetail record);

    int insertSelective(ActivityDetail record);

    List<ActivityDetail> selectByExampleWithBLOBs(ActivityDetailExample example);

    List<ActivityDetail> selectByExample(ActivityDetailExample example);

    ActivityDetail selectByPrimaryKey(Long activityId);

    int updateByExampleSelective(@Param("record") ActivityDetail record, @Param("example") ActivityDetailExample example);

    int updateByExampleWithBLOBs(@Param("record") ActivityDetail record, @Param("example") ActivityDetailExample example);

    int updateByExample(@Param("record") ActivityDetail record, @Param("example") ActivityDetailExample example);

    int updateByPrimaryKeySelective(ActivityDetail record);

    int updateByPrimaryKeyWithBLOBs(ActivityDetail record);

    int updateByPrimaryKey(ActivityDetail record);
}