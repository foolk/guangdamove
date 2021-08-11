package com.guangdamove.www.entity;

import java.io.Serializable;
import java.sql.Date;
import lombok.Data;

/**
 * t_client_linkman
 * @author 
 */
@Data
public class TClientLinkman implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户联系人Id与t_client相连
     */
    private Integer cid;

    /**
     * 联系人姓名
     */
    private String lname;

    /**
     * 联系人手机
     */
    private String ltel;

    /**
     * 办公电话
     */
    private String officePhone;

    /**
     * 出生日期
     */
    private Date hiredate;

    /**
     * 部门
     */
    private String dept;

    /**
     * 职位
     */
    private String job;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 联系人状态
     */
    private Integer flag;

    private static final long serialVersionUID = 1L;
}