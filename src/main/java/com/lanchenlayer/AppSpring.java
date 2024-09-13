package com.lanchenlayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.lanchenlayer.rest.testController.inicializarProdutos;
import static com.lanchenlayer.rest.testController.resolverDependencias;

@SpringBootApplication
public class AppSpring {

    public static void main(String[] args) {
        SpringApplication.run(AppSpring.class, args);
        resolverDependencias();
        inicializarProdutos();

    }
}
