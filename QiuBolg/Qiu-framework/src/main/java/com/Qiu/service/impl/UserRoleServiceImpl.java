package com.Qiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Qiu.domain.entity.UserRole;
import com.Qiu.mapper.UserRoleMapper;
import com.Qiu.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
