package com.guangdamove.www.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Intian
 * @create 2021-08-05 15:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    /*
    * 状态码
    * */
    private int code;

    /*
    * 信息
    * */
    private String message;

    /*
    * 数据
    * */
    private T data;
}
