package entity;

import core.DB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAO implements DAO<Product> {

    @Override
    public Optional<Product> get(int id) {
        DB db = DB.getInstance();
        try {
            String sql = "SELECT * FROM HD_PRODUCT WHERE product_id = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            Product product = null;
            if (rs.next()) {
                product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("product_description"),
                        rs.getString("product_color"),
                        rs.getString("product_size"),
                        rs.getDouble("product_price")
                );
            }
            return Optional.ofNullable(product);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return Optional.empty();
        }
    }

    @Override
    public List<Product> getAll() {
        DB db = DB.getInstance();
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM HD_PRODUCT ORDER BY product_id";
            ResultSet rs = db.executeQuery(sql);

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("product_description"),
                        rs.getString("product_color"),
                        rs.getString("product_size"),
                        rs.getDouble("product_price")
                );
                products.add(product);
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return products;
    }

    @Override
    public void insert(Product product) {
        DB db = DB.getInstance();
        if (db == null) {
            System.err.println("ERROR: Database instance is null!");
            return;
        }

        try {
            String sql = "INSERT INTO HD_PRODUCT(product_name, product_description, product_color, product_size, product_price) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);

            // Parameters now start at 1
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getProductDescription());
            stmt.setString(3, product.getProductColor());
            stmt.setString(4, product.getProductSize());
            stmt.setDouble(5, product.getProductPrice());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Product inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println("Insert Error: " + ex.toString());
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE HD_PRODUCT SET product_name=?, product_description=?, product_color=?, product_size=?, product_price=? WHERE product_id=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getProductDescription());
            stmt.setString(3, product.getProductColor());
            stmt.setString(4, product.getProductSize());
            stmt.setDouble(5, product.getProductPrice());
            stmt.setInt(6, product.getProductId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Product updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    @Override
    public void delete(Product product) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM HD_PRODUCT WHERE product_id=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, product.getProductId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Product deleted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    @Override
    public List<String> getColumnNames() {
        DB db = DB.getInstance();
        List<String> headers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM HD_PRODUCT WHERE product_id = -1";
            ResultSet rs = db.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                headers.add(rsmd.getColumnLabel(i));
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return headers;
    }
}