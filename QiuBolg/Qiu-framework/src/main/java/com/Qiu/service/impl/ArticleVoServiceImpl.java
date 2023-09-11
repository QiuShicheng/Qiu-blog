package com.Qiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Qiu.mapper.ArticleVoMapper;
import com.Qiu.service.ArticleVoService;
import com.Qiu.domain.vo.ArticleVo;
import org.springframework.stereotype.Service;

@Service
public class ArticleVoServiceImpl extends ServiceImpl<ArticleVoMapper, ArticleVo> implements ArticleVoService {

}