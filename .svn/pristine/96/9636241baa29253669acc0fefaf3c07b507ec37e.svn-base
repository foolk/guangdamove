package com.guangdamove.www.mapper;

import com.guangdamove.www.entity.TClient;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TClientMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TClient record);

    int insertSelective(TClient record);

    TClient selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TClient record);

    int updateByPrimaryKey(TClient record);


    /*查询所有用户*/
    List<TClient> tClientList(int id);
    /*查询单个用户*/
    TClient tClientList1(int id);

    /*<!--客户名称-->*/
    List<TClient> tClientListByName(String thisis);
    /*<!--手机号-->*/
     List<TClient> tClientListByPhone(String thisis);
    /*<!--证件号-->*/
    List<TClient> tClientListByIdCard(String thisis);

    /*<!--客户名称-->*/
    List<TClient> tClientListByName2(String thisis);
    /*<!--手机号-->*/
    List<TClient> tClientListByPhone2(String thisis);
    /*<!--证件号-->*/
    List<TClient> tClientListByIdCard2(String thisis);



    /*根据名字查询*/
    TClient tClientListByCname(String thisis);

}