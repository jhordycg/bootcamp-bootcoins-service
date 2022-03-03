package com.bootcamp.bootcoin.service.impl;

import com.bootcamp.bootcoin.entity.Bootcoin;
import com.bootcamp.bootcoin.repository.BootcoinRepository;
import com.bootcamp.bootcoin.service.BootcoinServiceWithCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class BootcoinServiceImpl implements BootcoinServiceWithCache {
    private final KafkaTemplate<String, Bootcoin> kafkaTemplate;
    private final BootcoinRepository repository;
    private static final String TOPIC_NAME = "demo";

    @Override
    public Flux<Bootcoin> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Bootcoin> findOneById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Bootcoin> create(Bootcoin bootcoin) {
        return repository.insert(bootcoin)
                .map(this::sendToKafka);
    }

    @Override
    public Mono<Bootcoin> update(Bootcoin bootcoin) {
        return repository.save(bootcoin);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }

    public Bootcoin sendToKafka(Bootcoin bootcoin) {
        kafkaTemplate.send(TOPIC_NAME, bootcoin.getPhone(), bootcoin)
                .addCallback(result -> {
                    var resultNonNull = Objects.requireNonNull(result);
                    log.info("response: {}", resultNonNull.getProducerRecord());
                }, Throwable::printStackTrace);
        return bootcoin;
    }
}
