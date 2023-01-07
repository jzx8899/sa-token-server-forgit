package com.yk.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author jzx
 */
@Data
@TableName("tb_user")
public class TbUser {

    private Integer id;
    private String name;
    private String password;

}
