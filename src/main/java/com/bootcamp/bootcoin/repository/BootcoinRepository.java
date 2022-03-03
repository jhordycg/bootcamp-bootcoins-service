package com.bootcamp.bootcoin.repository;

import com.bootcamp.bootcoin.entity.Bootcoin;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BootcoinRepository extends ReactiveMongoRepository<Bootcoin, String> {
}
