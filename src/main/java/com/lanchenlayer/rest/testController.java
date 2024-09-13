package com.lanchenlayer.rest;


import com.lanchenlayer.applications.ProdutoApplication;
import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.facade.ProdutoFacade;
import com.lanchenlayer.repositories.ProdutoRepository;
import com.lanchenlayer.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Scanner;


@RestController
public class testController {
    private static ProdutoRepository produtoRepository;
    private static ProdutoService produtoService;
    private static ProdutoApplication produtoApplication;
    private static ProdutoFacade produtoFacade;
    private static Scanner scanner;

    public static void resolverDependencias() {
        produtoRepository = new ProdutoRepository();
        produtoService = new ProdutoService();
        produtoApplication = new ProdutoApplication(produtoRepository, produtoService);
        produtoFacade = new ProdutoFacade(produtoApplication);
        scanner = new Scanner(System.in);
    }

    public static void inicializarProdutos() {
        produtoFacade.adicionar(new Produto(1, "PRODUTO 1", 4.00f, "C:\\Users\\hsaim\\Downloads\\1.jpg"));
        produtoFacade.adicionar(new Produto(2, "PRODUTO 2", 5.00f, "C:\\Users\\hsaim\\Downloads\\2.jpg"));
    }

    @GetMapping("/buscarProduto/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable("id") int id) {
        Produto produto = produtoFacade.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/buscarProdutos")
    public ResponseEntity<ArrayList<Produto>> buscarProdutos() {
        ArrayList<Produto> produtos = produtoFacade.buscarTodos();
        return ResponseEntity.ok(produtos);
    }
    }

