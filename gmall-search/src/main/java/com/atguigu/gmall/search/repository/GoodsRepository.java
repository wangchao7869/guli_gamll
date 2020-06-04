package com.atguigu.gmall.search.repository;

import com.atguigu.gmall.search.entity.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.Repository;

/**
 * @author chao
 * @create 2020-05-27
 */

public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {

}
