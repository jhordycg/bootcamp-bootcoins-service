package com.bootcamp.bootcoin.service;

import com.bootcamp.bootcoin.entity.Bootcoin;
import com.hanqunfeng.reactive.redis.cache.aop.ReactiveRedisCacheEvict;
import com.hanqunfeng.reactive.redis.cache.aop.ReactiveRedisCachePut;
import com.hanqunfeng.reactive.redis.cache.aop.ReactiveRedisCacheable;
import com.hanqunfeng.reactive.redis.cache.aop.ReactiveRedisCaching;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootcoinServiceWithCache extends BootcoinService {
    static String CACHE_NAME = "bootcoins";

    @ReactiveRedisCacheable(cacheName = CACHE_NAME, key = "'findAll'")
    Flux<Bootcoin> findAll();

    @ReactiveRedisCacheable(cacheName = CACHE_NAME, key = "'findOneById_' + #id")
    Mono<Bootcoin> findOneById(String id);

    @ReactiveRedisCacheEvict(cacheName = CACHE_NAME, allEntries = true)
    Mono<Bootcoin> create(Bootcoin bootcoin);

    @ReactiveRedisCaching(
            evict = {@ReactiveRedisCacheEvict(cacheName = CACHE_NAME, key = "findAll"),},
            put = {
                    @ReactiveRedisCachePut(cacheName = CACHE_NAME, key = "'findOneById_' + #bootcoin.id"),
            }
    )
    Mono<Bootcoin> update(Bootcoin bootcoin);

    @ReactiveRedisCacheEvict(cacheName = CACHE_NAME, key = "'findById_' + #id")
    Mono<Void> deleteById(String id);
}
