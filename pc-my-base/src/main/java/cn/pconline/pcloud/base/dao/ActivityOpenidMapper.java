package cn.pconline.pcloud.base.dao;

import cn.pconline.pcloud.base.entity.ActivityOpenidExample;
import cn.pconline.pcloud.base.entity.ActivityOpenidKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityOpenidMapper {
    int countByExample(ActivityOpenidExample example);

    int deleteByExample(ActivityOpenidExample example);

    int deleteByPrimaryKey(ActivityOpenidKey key);

    int insert(ActivityOpenidKey record);

    int insertSelective(ActivityOpenidKey record);

    List<ActivityOpenidKey> selectByExample(ActivityOpenidExample example);

    int updateByExampleSelective(@Param("record") ActivityOpenidKey record, @Param("example") ActivityOpenidExample example);

    int updateByExample(@Param("record") ActivityOpenidKey record, @Param("example") ActivityOpenidExample example);
}