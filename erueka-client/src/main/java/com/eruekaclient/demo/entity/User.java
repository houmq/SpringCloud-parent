package com.eruekaclient.demo.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * Created by  Hmq
 * @date on 2018/9/3.
 */
@ApiModel(value = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{


    private static final long serialVersionUID = -5073362720698760474L;
    private Integer id;

    private String name;

    private Integer age;

}
