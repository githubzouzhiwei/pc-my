package cn.pconline.pcloud.base.dao;

import cn.pconline.pcloud.base.entity.ActivityRecordExample;
import cn.pconline.pcloud.base.entity.ActivityRecordKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityRecordMapper {
    int countByExample(ActivityRecordExample example);

    int deleteByExample(ActivityRecordExample example);

    int deleteByPrimaryKey(ActivityRecordKey key);

    int insert(ActivityRecordKey record);

    int insertSelective(ActivityRecordKey record);

    List<ActivityRecordKey> selectByExample(ActivityRecordExample example);

    int updateByExampleSelective(@Param("record") ActivityRecordKey record, @Param("example") ActivityRecordExample example);

    int updateByExample(@Param("record") ActivityRecordKey record, @Param("example") ActivityRecordExample example);
}