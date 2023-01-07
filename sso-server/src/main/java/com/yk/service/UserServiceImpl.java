package com.yk.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yk.bean.TbUser;
import com.yk.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author jzx
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, TbUser> implements UserService{
    @Override
    public List<TbUser> queryByName(String name) {
        LambdaQueryWrapper<TbUser> lambdaQuery = Wrappers.lambdaQuery(TbUser.class);
        lambdaQuery.eq(StringUtils.hasLength(name),TbUser::getName,name);
        List<TbUser> list = this.list(lambdaQuery);
        return list;
    }
}
