package com.hujy.springaopdemo.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户信息实体
 *
 * @author hujy
 * @version 1.0
 * @date 2019-09-29 10:51
 */
@Getter
@Setter
@ToString
public class User {

    private String userCode;

    private String userName;

    private Integer age;
}
