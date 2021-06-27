package cn.pconline.pcloud.base.dao;

import cn.pconline.pcloud.base.entity.ActivityPacket;
import cn.pconline.pcloud.base.entity.ActivityPacketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityPacketMapper {
    int countByExample(ActivityPacketExample example);

    int deleteByExample(ActivityPacketExample example);

    int deleteByPrimaryKey(Long activityPacketId);

    int insert(ActivityPacket record);

    int insertSelective(ActivityPacket record);

    List<ActivityPacket> selectByExample(ActivityPacketExample example);

    ActivityPacket selectByPrimaryKey(Long activityPacketId);

    int updateByExampleSelective(@Param("record") ActivityPacket record, @Param("example") ActivityPacketExample example);

    int updateByExample(@Param("record") ActivityPacket record, @Param("example") ActivityPacketExample example);

    int updateByPrimaryKeySelective(ActivityPacket record);

    int updateByPrimaryKey(ActivityPacket record);

    void insertBatch(List<ActivityPacket> paramList);
}