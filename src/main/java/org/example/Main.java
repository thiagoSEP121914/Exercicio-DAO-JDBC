package org.example;

import dao.DaoFactory;
import dao.ProdutoDao;
import db.config.DB;
import entities.Product;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProdutoDao produtoDao = DaoFactory.createProductDao();

            while (true) {
                System.out.println("==========================================================");
                System.out.println("S I S T E M A   D E  C A D A S T R O  D E  P R O D U T O S");
                System.out.println("[1] CADASTRAR NOVO PRODUTO ");
                System.out.println("[2] BUSCAR PRODUTO PELO ID ");
                System.out.println("[3] BUSCAR TODOS OS PRODUTOS ");
                System.out.println("[4] ATUALIZAR PRODUTO ");
                System.out.println("[5] REMOVER PRODUTO");
                System.out.println("[6] PARA SAIR:");
                int num = sc.nextInt();
                sc.nextLine();

                switch (num) {
                    case 1:
                            System.out.print("Digite o nome do produto: ");
                            String nome = sc.nextLine();
                            System.out.print("Descricao: ");
                            String descri = sc.nextLine();
                            sc.nextLine();
                            System.out.print("Preco");
                            double preco = sc.nextDouble();
                            Product product = new Product(null, nome, descri, preco);
                            produtoDao.addProduct(product);
                            System.out.println();
                            break;

                    case 2:
                            System.out.print("DIgite o Id do produto: ");
                            int id = sc.nextInt();
                            System.out.println(produtoDao.selectById(id));
                            System.out.println();
                            break;

                    case 3:
                            List<Product> list = produtoDao.selectAll();
                            list.forEach(System.out::println);
                            break;
                    case 4 :
                        System.out.print("Qual produto deseja atualizar: ");
                        id = sc.nextInt();
                        Product prod = produtoDao.selectById(id);
                        System.out.println(prod);
                        System.out.println();
                        System.out.print("Digite o nome do produto: ");
                        nome = sc.nextLine();
                        System.out.print("Descricao: ");
                        descri = sc.nextLine();
                        sc.nextLine();
                        System.out.print("Preco");
                        preco = sc.nextDouble();
                        prod = new Product(null, nome, descri, preco);
                        produtoDao.updateProduct(prod);
                        System.out.println();

                    case 5:
                        System.out.print("Qual produto EXCLUIR: ");
                        id = sc.nextInt();
                        produtoDao.removerProduto(id);
                        break;

                    case 6:
                        System.out.println("VOLTE SEMPRE!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Opcao invalida. Tente novamente");
                        break;
                }




            }
    }
}