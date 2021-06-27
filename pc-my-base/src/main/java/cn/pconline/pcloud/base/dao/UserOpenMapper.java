package cn.pconline.pcloud.base.dao;

import cn.pconline.pcloud.base.entity.UserOpen;
import cn.pconline.pcloud.base.entity.UserOpenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserOpenMapper {
    int countByExample(UserOpenExample example);

    int deleteByExample(UserOpenExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserOpen record);

    int insertSelective(UserOpen record);

    List<UserOpen> selectByExample(UserOpenExample example);

    UserOpen selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserOpen record, @Param("example") UserOpenExample example);

    int updateByExample(@Param("record") UserOpen record, @Param("example") UserOpenExample example);

    int updateByPrimaryKeySelective(UserOpen record);

    int updateByPrimaryKey(UserOpen record);
}