package com.Qiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Qiu.domain.entity.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    //查询普通用户的角色权限
    List<String> selectRoleKeyByOtherUserId(Long userId);
    //修改用户-①根据id查询用户信息
    List<Long> selectRoleIdByUserId(Long userId);
}
