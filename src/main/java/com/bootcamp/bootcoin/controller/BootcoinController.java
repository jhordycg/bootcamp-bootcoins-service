package com.bootcamp.bootcoin.controller;

import com.bootcamp.bootcoin.entity.Bootcoin;
import com.bootcamp.bootcoin.service.BootcoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("bootcoins")
@RequiredArgsConstructor
public class BootcoinController {
    private final BootcoinService service;

    @PostMapping
    public Mono<Bootcoin> create(@RequestBody Bootcoin bootcoin) {
        return service.create(bootcoin);
    }

    @GetMapping
    public Flux<Bootcoin> findAll() {
        return service.findAll();
    }

}
