package com.yk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yk.bean.TbUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jzx
 */
@Mapper
public interface UserMapper extends BaseMapper<TbUser> {
}
