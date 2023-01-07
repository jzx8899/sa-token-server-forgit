package com.yk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yk.bean.TbUser;

import java.util.List;

/**
 * 用户服务
 *
 * @author jzx
 * @date 2023/01/07
 */
public interface UserService extends IService<TbUser> {
    /**
     * 按名称查询
     *
     * @param name 名字
     * @return {@link List}<{@link TbUser}>
     */
    List<TbUser> queryByName(String name);
}
