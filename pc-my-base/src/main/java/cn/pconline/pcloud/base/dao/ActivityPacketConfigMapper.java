package cn.pconline.pcloud.base.dao;

import cn.pconline.pcloud.base.entity.ActivityPacketConfig;
import cn.pconline.pcloud.base.entity.ActivityPacketConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityPacketConfigMapper {
    int countByExample(ActivityPacketConfigExample example);

    int deleteByExample(ActivityPacketConfigExample example);

    int deleteByPrimaryKey(Long activityPacketConfigId);

    int insert(ActivityPacketConfig record);

    int insertSelective(ActivityPacketConfig record);

    List<ActivityPacketConfig> selectByExample(ActivityPacketConfigExample example);

    ActivityPacketConfig selectByPrimaryKey(Long activityPacketConfigId);

    int updateByExampleSelective(@Param("record") ActivityPacketConfig record, @Param("example") ActivityPacketConfigExample example);

    int updateByExample(@Param("record") ActivityPacketConfig record, @Param("example") ActivityPacketConfigExample example);

    int updateByPrimaryKeySelective(ActivityPacketConfig record);

    int updateByPrimaryKey(ActivityPacketConfig record);
}