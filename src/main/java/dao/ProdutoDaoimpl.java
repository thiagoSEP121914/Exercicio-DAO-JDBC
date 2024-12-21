package dao;


import db.config.DB;
import entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoimpl implements ProdutoDao {

    private static final String  ALERT = "NÃO FOI POSSIVEL CONECTAR COM O BBANCO DE DADOS ";
    private Connection conn;
    public ProdutoDaoimpl (Connection conn) {
        this.conn = conn;
    }

    @Override
    public Product selectById(Integer id) {

        Product prod = new Product();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM produtos "
                    + "WHERE id = ? ";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                prod.setId(rs.getInt("id"));
                prod.setName(rs.getString("name"));
                prod.setDescription(rs.getString("description"));
                prod.setPreco(rs.getDouble("preco"));
            }
            return prod;
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

    @Override
    public List<Product> selectAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM produtos ";

        try {

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Product> list = new ArrayList<>();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription("description");
                product.setPreco(rs.getDouble("preco"));
                list.add(product);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public void addProduct(Product obj) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO produtos (name, description, preco) "
                     + "VALUES "
                     + "(?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getDescription());
            ps.setDouble(3, obj.getPreco());

            int rowsAffect = ps.executeUpdate();

            if (rowsAffect > 0 ) {
                System.out.println("PRODUTO CADASTRADO COM SUCESSO! ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void updateProduct(Product obj) {
        PreparedStatement ps = null;

        String sql = "UPDATE produtos "
                     + "SET name = ?, description = ?, preco = ?  "
                     + "WHERE id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,obj.getName());
            ps.setString(2, obj.getDescription());
            ps.setDouble(3, obj.getPreco());
            ps.setInt(4, obj.getId());

             int rowsAffected = ps.executeUpdate();

             if (rowsAffected > 0) {
                 System.out.println("DADOS ATUALIZADOS COM SUCESSO!!");
             }

        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e);
        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public void removerProduto(Integer id) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM produtos "
                     + "WHERE id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dados excluido com sucesso!!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }
}
