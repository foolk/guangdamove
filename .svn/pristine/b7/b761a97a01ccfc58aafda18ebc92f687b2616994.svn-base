package com.guangdamove.www.mapper;

import com.guangdamove.www.entity.TClientLinkman;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TClientLinkmanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TClientLinkman record);

    int insertSelective(TClientLinkman record);

    TClientLinkman selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TClientLinkman record);

    int updateByPrimaryKey(TClientLinkman record);

    /*删除客户电话*/
    void deleteByCid(Integer cid);
}