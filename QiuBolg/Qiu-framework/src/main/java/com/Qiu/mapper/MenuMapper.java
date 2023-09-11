package com.Qiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Qiu.domain.entity.Menu;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MenuMapper extends BaseMapper<Menu> {
    //查询普通用户的权限信息
    List<String> selectPermsByOtherUserId(Long userId);
    //查询超级管理员的路由信息(权限菜单)
    List<Menu> selectAllRouterMenu();
    //查询普通用户的路由信息(权限菜单)
    List<Menu> selectOtherRouterMenuTreeByUserId(Long userId);

    List<Long> selectMenuListByRoleId(Long roleId);
}
