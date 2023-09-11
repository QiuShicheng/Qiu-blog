package com.Qiu.job;

import com.Qiu.domain.entity.Article;
import com.Qiu.service.ArticleService;
import com.Qiu.utils.RedisCache;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
//通过定时任务实现每隔一定时间把redis中的浏览量更新到mysql数据库中
public class UpdateViewCount {

    @Autowired
    //操作redis。RedisCache是我们在Qiu-framework工程写的工具类
    private RedisCache redisCache;

    @Autowired
    //操作数据库。ArticleService是我们在Qiu-framework工程写的接口
    private ArticleService articleService;

    //每隔5秒，把redis的浏览量数据更新到mysql数据库
    @Scheduled(cron = "0/5 * * * * ?")
    public void updateViewCount(){
        //获取redis中的浏览量，注意得到的viewCountMap是HashMap双列集合
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");
        List<Article> articles = viewCountMap.entrySet()
                .stream()
                .map(entry ->
                        new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        for (Article article : articles) {
            LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Article :: getId, article.getId());
            updateWrapper.set(Article :: getViewCount, article.getViewCount());
            articleService.update(updateWrapper);
        }
        //把获取到的浏览量更新到mysql数据库中。updateBatchById是mybatisplus提供的批量操作数据的接口
        articleService.updateBatchById(articles);
    }
}
