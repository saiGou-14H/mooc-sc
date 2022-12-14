package com.org.service;

import com.org.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表
 服务类
 * </p>
 *
 * @author B.M
 * @since 2022-10-23
 */
public interface IUserService extends IService<User> {

    public List<User> shUser(User user);
}
