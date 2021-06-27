package cn.pconline.pcloud.base.dao.system;

import cn.pconline.pcloud.base.entity.system.RoleResourceExample;
import cn.pconline.pcloud.base.entity.system.RoleResourceKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleResourceMapper {
    int countByExample(RoleResourceExample example);

    int deleteByExample(RoleResourceExample example);

    int deleteByPrimaryKey(RoleResourceKey key);

    int insert(RoleResourceKey record);

    int insertSelective(RoleResourceKey record);

    List<RoleResourceKey> selectByExample(RoleResourceExample example);

    int updateByExampleSelective(@Param("record") RoleResourceKey record, @Param("example") RoleResourceExample example);

    int updateByExample(@Param("record") RoleResourceKey record, @Param("example") RoleResourceExample example);
}