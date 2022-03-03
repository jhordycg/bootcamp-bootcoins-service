package com.bootcamp.bootcoin.service;

import com.bootcamp.bootcoin.entity.Bootcoin;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootcoinService {
    Flux<Bootcoin> findAll();

    Mono<Bootcoin> findOneById(String id);

    Mono<Bootcoin> create(Bootcoin bootcoin);

    Mono<Bootcoin> update(Bootcoin bootcoin);

    Mono<Void> deleteById(String id);
}
