package org.example;

import dao.DaoFactory;
import dao.ProdutoDao;
import db.config.DB;
import entities.Product;

import java.sql.Connection;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ProdutoDao produtoDao = DaoFactory.createProductDao();

        System.out.println("SELECT BY ID: \n");
        Product product  = produtoDao.selectById(1);
        System.out.println(product);

        System.out.println("SELECT ALL");
        List<Product> list = produtoDao.selectAll();
        list.forEach(System.out::println);
        System.out.println();

        System.out.println("INSERT OBJETO");
        Product prod = new Product(null, "zerbindi", "tendas", 33.5);
        produtoDao.addProduct(prod);
        System.out.println();

        System.out.println("UPDATE OBJETO");
        prod = produtoDao.selectById(6);
        prod.setName("5 X 5");
        produtoDao.updateProduct(prod);
        System.out.println();

        System.out.println("DELETE OBJETO");
        produtoDao.removerProduto(1);

    }
}