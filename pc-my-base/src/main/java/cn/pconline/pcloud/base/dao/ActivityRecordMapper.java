package cn.pconline.pcloud.base.dao;

import cn.pconline.pcloud.base.entity.ActivityRecord;
import cn.pconline.pcloud.base.entity.ActivityRecordExample;
import cn.pconline.pcloud.base.entity.ActivityRecordKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityRecordMapper {
    int countByExample(ActivityRecordExample example);

    int deleteByExample(ActivityRecordExample example);

    int deleteByPrimaryKey(ActivityRecordKey key);

    int insert(ActivityRecord record);

    int insertSelective(ActivityRecord record);

    List<ActivityRecord> selectByExample(ActivityRecordExample example);

    ActivityRecord selectByPrimaryKey(ActivityRecordKey key);

    int updateByExampleSelective(@Param("record") ActivityRecord record, @Param("example") ActivityRecordExample example);

    int updateByExample(@Param("record") ActivityRecord record, @Param("example") ActivityRecordExample example);

    int updateByPrimaryKeySelective(ActivityRecord record);

    int updateByPrimaryKey(ActivityRecord record);
}