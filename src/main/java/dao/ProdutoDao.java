package dao;

import entities.Product;

import java.util.List;

public interface ProdutoDao {

    Product selectById (Integer id);
    List<Product> selectAll ();
    void addProduct (Product obj);

    void updateProduct (Product obj);
    void removerProduto (Integer id);


}
