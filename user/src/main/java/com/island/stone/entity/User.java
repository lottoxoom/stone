package com.island.stone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户实体
 * @author: LOTTO
 * @create: 2022-01-14 15:13
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    private String userName;

    private String passWord;
    private String realName;



}
