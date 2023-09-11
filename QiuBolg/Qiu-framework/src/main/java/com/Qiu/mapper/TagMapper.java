package com.Qiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Qiu.domain.entity.Tag;

public interface TagMapper extends BaseMapper<Tag> {

    void myUpdateById(Long id, int flag);
}
