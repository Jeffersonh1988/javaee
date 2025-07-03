package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
    // ** MÓDULO DE CONEXÃO **/
    // PARÂMETROS DE CONEXÃO
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/rscobertor?useTimezone=true&serverTimezone=UTC";
    private String user = "root";
    private String password = "#Ifpr@2025";
    
    // MÉTODO DE CONEXÃO 
    private Connection conectar() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    // INSERIR PRODUTO
    public void inserirProduto(JavaBeans produto) {
        String create = "INSERT INTO produto (nome, marca, tamanho, preco) VALUES (?, ?, ?, ?)";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(create);
            pst.setString(1, produto.getNome());
            pst.setString(2, produto.getMarca());
            pst.setString(3, produto.getTamanho());
            pst.setString(4, produto.getPreco());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // LISTAR PRODUTOS
    public ArrayList<JavaBeans> listarProdutos() {
        ArrayList<JavaBeans> produtos = new ArrayList<>();
        String read = "SELECT * FROM produto ORDER BY nome";
        
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(read);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String id = rs.getString(1);
                String nome = rs.getString(2);
                String marca = rs.getString(3);
                String tamanho = rs.getString(4);
                String preco = rs.getString(5);
                produtos.add(new JavaBeans(id, nome, marca, tamanho, preco));
            }
            
            con.close();
            return produtos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    /** CRUD UPDATE **/ 
    // SELECIONAR O CONTATO
 // Selecionar produto para edição
 	public void selecionarProduto(JavaBeans produto) {
 		String read2 = "SELECT * FROM produto WHERE id = ?";
 		try {
 			Connection con = conectar();
 			PreparedStatement pst = con.prepareStatement(read2);
 			pst.setString(1, produto.getId());
 			ResultSet rs = pst.executeQuery();
 			if (rs.next()) {
 				produto.setNome(rs.getString(2));
 				produto.setMarca(rs.getString(3));
 				produto.setTamanho(rs.getString(4));
 				produto.setPreco(rs.getString(5));
 			}
 			con.close();
 		} catch (Exception e) {
 			System.out.println("Erro ao selecionar produto: " + e);
 		}
 	}
 	// Editar o produto
 	public void alterarProduto (JavaBeans produto) {
 		String create = "update produto set nome=?, marca=?, tamanho=?, preco=? where id=?";
 		try {
			Connection con = conectar();
			PreparedStatement pst= con.prepareStatement(create);
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getMarca());
			pst.setString(3, produto.getTamanho());
			pst.setString(4, produto.getPreco());
			pst.setString(5, produto.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
 	}
 	//** CRUD DELETE */ 
 	public void deletarProduto(JavaBeans produto) {
 		String delete = "delete from produto where id=?";
 		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, produto.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.print(e);
			
		}
 	}
    
    }

    // TESTE DE CONEXÃO
    /*
    public void testeConexao() {
        try {
            Connection con = conectar();
            System.out.println(con);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    */

