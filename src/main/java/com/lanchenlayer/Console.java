package com.lanchenlayer;

import com.lanchenlayer.applications.ProdutoApplication;
import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.facade.ProdutoFacade;
import com.lanchenlayer.repositories.ProdutoRepository;
import com.lanchenlayer.services.ProdutoService;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 */
public class Console {
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

    public static void listarProdutos() {
        System.out.println("Id    |    Descrição   |   Valor");

        ArrayList<Produto> produtos = produtoFacade.buscarTodos();

        produtos.forEach(c -> {
            System.out.println(c.getId()+"   |     " +c.getDescricao() + "   |     " + c.getValor());
        });
    }

    public static void cadastrarProduto() {
        System.out.println("ID do produto: ");
        int id = scanner.nextInt();

        System.out.println("Descricao: ");
        String descricao = scanner.next();

        System.out.println("Valor: ");
        float valor = scanner.nextFloat();

        System.out.println("Fonte: ");
        String imagem = scanner.next();

        Produto produto = new Produto(id, descricao, valor, imagem);
        produtoFacade.adicionar(produto);
    }

    public static void exibirMenu() {
        System.out.println("\n1 - Novo produto");
        System.out.println("2 - Atualizaa produto");
        System.out.println("3 - Listar produtos");
        System.out.println("4 - Vender");
        System.out.println("5 - Remover produto");
        System.out.println("6 - Sair");
    }

    public static int solicitarInputUsuario() {
        System.out.println("Digite a opção do menu desejado: ");
        return scanner.nextInt();
    }

    public static void venderProduto() {
        System.out.println("Qual o produtoo desejada: ");
        int id = scanner.nextInt();

        System.out.println("Qtd desejada: ");
        int quantidade = scanner.nextInt();

        System.out.println("Soma: " + produtoFacade.vender(id, quantidade));
    }

    private static void atualizarProduto() {
        System.out.println("ID para atualizar o produto: ");
        int id = scanner.nextInt();

        System.out.println("Nova descrição do produto: ");
        String descricao = scanner.next();

        System.out.println("Novo valor do produto: ");
        float valor = scanner.nextFloat();

        System.out.println("Nova fonte da img: ");
        String imagem = scanner.next();

        Produto produto = new Produto(id, descricao, valor, imagem);
        produtoFacade.atualizarProduto(id, produto);
    }

    public static void removerProduto() {
        System.out.println("ID do produto a ser removido: ");
        int id = scanner.nextInt();

        produtoFacade.remover(id);
    }

    public static void rodar() {
        int opcaoMenu = 0;

        do {
            exibirMenu();
            opcaoMenu = solicitarInputUsuario();
            switch (opcaoMenu) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    atualizarProduto();
                    break;
                case 3:
                    listarProdutos();
                    break;
                case 4:
                    venderProduto();
                    break;
                case 5:
                    removerProduto();
                    break;
            }

        } while (opcaoMenu != 6);
    }

    public static void main(String[] args) {
        resolverDependencias();
        inicializarProdutos();
        rodar();
    }
}
