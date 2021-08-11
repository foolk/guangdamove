package com.guangdamove.www.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * t_client
 * @author 
 */
@Data
public class TClient implements Serializable {
    /**
     * 客户Id
     */
    private Integer id;

    /**
     * 客户名称
     */
    private String cname;

    /**
     * 投保意向
     */
    private String intention;

    /**
     * 证件类型
     */
    private String codeType;

    /**
     * 行业类型
     */
    private String industryType;

    /**
     * 单位性质
     */
    private String unitType;

    /**
     * 证件号码
     */
    private String codeId;

    /**
     * 单位总人数
     */
    private Integer unitCount;

    /**
     * 预计投保人数
     */
    private Integer insureCount;

    /**
     * 单位地址
     */
    private String address;

    /**
     * 星级
     */
    private String starLevel;

    /*
    * 联系人列表
    * */
    private List<TClientLinkman> tclientLinkmen;

    private static final long serialVersionUID = 1L;
}