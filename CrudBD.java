import  java.sql.Connection;
import  java.sql.PreparedStatement;
import  java.sql.ResultSet;
import  java.sql.SQLException;
import  java.util.ArrayList;

public class CrudBD
{   
    //cadastrar pedido
    public void incluirReg(Pedido uD, Cliente uDraft) {
        String sqlBuscarCliente = "SELECT id, nome FROM tb_cliente WHERE nome = ?";
        String sqlInserirPedido = "INSERT INTO tb_pedido(nome, preco, id_cliente) VALUES (?, ?, ?)";
        
        try (Connection conn = ConnFactory.getConn();
            PreparedStatement stmtBuscar = conn.prepareStatement(sqlBuscarCliente);
            PreparedStatement stmtInserir = conn.prepareStatement(sqlInserirPedido)) {
             
            // Busca o cliente pelo nome
            stmtBuscar.setString(1, uDraft.getNome());
            try (ResultSet rs = stmtBuscar.executeQuery()) {
                if (rs.next()) {
                    uDraft.setId(rs.getInt("id"));
                } else {
                    throw new SQLException("Cliente não encontrado no banco de dados!");
                }
            }
            
            // Insere o pedido vinculado ao cliente
            stmtInserir.setString(1, uD.getNome());
            stmtInserir.setDouble(2, uD.getPreco());
            stmtInserir.setInt(3, uDraft.getId());
            stmtInserir.executeUpdate();
            
            System.out.println("Pedido inserido com sucesso para o cliente: " + uDraft.getNome());
            
        } catch (SQLException e) {
            System.err.println("Erro ao processar a inclusão: " + e.getMessage());
        }
    }
    

    //cadastrar cliente
    public void incluirRegCliente(Cliente uD)
    {   String   sqlInsert = "INSERT INTO tb_cliente(nome, telefone, endereco) VALUES(?, ?, ?)";
        Connection  conn = ConnFactory.getConn();
        PreparedStatement stmt = null;
        try
        {   stmt = conn.prepareStatement(sqlInsert);
            stmt.setString(1, uD.getNome());
            stmt.setString(2, uD.getTelefone());
            stmt.setString(3, uD.getEndereco());
            stmt.executeUpdate();
        }
        catch(SQLException e)
        {   try
            {   conn.rollback();
            }
            catch(SQLException ex)
            {   System.out.println("Erro ao incluir os dados do cliente" + ex.toString());
            }
        }
        finally
        {   ConnFactory.closeConn(conn, stmt);
        }
    }   
    
    //consulta pedido
    public void consultarReg(Pedido uD)
    {   String   sqlSelect = "SELECT nome, preco FROM tb_pedido WHERE nome = ?"; 
        Connection  conn = ConnFactory.getConn();
        PreparedStatement stmt = null;
        ResultSet rs;
        try
        {   stmt = conn.prepareStatement(sqlSelect);
            stmt.setString(1, uD.getNome());
            // stmt.executeQuery();
            rs = stmt.executeQuery();
           if(rs.next())
           {   uD.setNome(rs.getString("nome")); 
               uD.setPreco(rs.getDouble("preco"));
           }
        }
        catch(SQLException ex)
        {   System.out.println("Erro ao consultar os dados" + ex.toString());
        }
        finally
        {   ConnFactory.closeConn(conn, stmt);
        }       
    } 

    //consulta cliente
    public void consultarRegCliente(Cliente uD)
    {   String   sqlSelect = "SELECT nome,telefone, endereco FROM tb_cliente WHERE nome = ?"; 
        Connection  conn = ConnFactory.getConn();
        PreparedStatement stmt = null;
        ResultSet rs;
        try
        {   stmt = conn.prepareStatement(sqlSelect);
            stmt.setString(1, uD.getNome());
            // stmt.executeQuery();
            rs = stmt.executeQuery();
           if(rs.next())
           {   uD.setNome(rs.getString("nome"));
                uD.setTelefone(rs.getString("telefone")); 
                uD.setEndereco(rs.getString("endereco")); 
           }
        }
        catch(SQLException ex)
        {   System.out.println("Erro ao consultar os dados" + ex.toString());
        }
        finally
        {   ConnFactory.closeConn(conn, stmt);
        }       
    } 

    //altera pedido
    public void alterarReg(Pedido uD, String nomeAtual)
    {   String   sqlUpdate = "UPDATE tb_pedido SET nome = ?, preco = ? WHERE nome = ?";
        Connection  conn = ConnFactory.getConn();
        PreparedStatement stmt = null;
        try
        {   stmt = conn.prepareStatement(sqlUpdate);
            stmt.setString(1, uD.getNome());
            stmt.setDouble(2, uD.getPreco());
            stmt.setString(3, nomeAtual);
            stmt.executeUpdate();
        }
        catch(SQLException e)
        {   try
            {   conn.rollback();
            }
            catch(SQLException ex)
            {   System.out.println("Erro ao alterar os dados" + ex.toString());
            }
        }
        finally
        {   ConnFactory.closeConn(conn, stmt);
        }       
    }  

    //altera cliente
    public void alterarRegCliente(Cliente uD, String nomeAtual)
    {   String   sqlUpdate = "UPDATE tb_cliente SET nome = ?, telefone = ?, endereco = ? WHERE nome = ?";
        Connection  conn = ConnFactory.getConn();
        PreparedStatement stmt = null;
        try
        {   stmt = conn.prepareStatement(sqlUpdate);
            stmt.setString(1, uD.getNome());
            stmt.setString(2, uD.getTelefone());
            stmt.setString(3, uD.getEndereco());
            stmt.setString(4, nomeAtual);
            stmt.executeUpdate();
        }
        catch(SQLException e)
        {   try
            {   conn.rollback();
            }
            catch(SQLException ex)
            {   System.out.println("Erro ao alterar os dados" + ex.toString());
            }
        }
        finally
        {   ConnFactory.closeConn(conn, stmt);
        }       
    }  
    
    //excluir pedido
    public void excluirReg(Pedido uD)
    {   String   sqlDelete = "DELETE FROM tb_pedido WHERE nome = ?";
        Connection  conn = ConnFactory.getConn();
        PreparedStatement stmt = null;
        try
        {   stmt = conn.prepareStatement(sqlDelete);
            stmt.setString(1, uD.getNome());
            stmt.executeUpdate();
        }
        catch(SQLException e)
        {   try
            {   conn.rollback();
            }
            catch(SQLException ex)
            {   System.out.println("Erro ao excluir os dados" + ex.toString());
            }
        }
        finally
        {   ConnFactory.closeConn(conn, stmt);
        }       
    }

    //excluir cliente
    public void excluirRegCliente(Cliente uD)
    {   String   sqlDelete = "DELETE FROM tb_cliente WHERE nome = ?";
        Connection  conn = ConnFactory.getConn();
        PreparedStatement stmt = null;
        try
        {   stmt = conn.prepareStatement(sqlDelete);
            stmt.setString(1, uD.getNome());
            stmt.executeUpdate();
        }
        catch(SQLException e)
        {   try
            {   conn.rollback();
            }
            catch(SQLException ex)
            {   System.out.println("Erro ao excluir os dados" + ex.toString());
            }
        }
        finally
        {   ConnFactory.closeConn(conn, stmt);
        }       
    }
    
    public  ArrayList<Cliente> buscarTodos()
    {   ArrayList<Cliente> aL = new ArrayList<>();
        String   sqlSelect = "SELECT id, nome, telefone, endereco FROM tb_cliente";
        Connection  conn = ConnFactory.getConn();
        PreparedStatement stmt = null;
        ResultSet rs;
        try
        {   stmt = conn.prepareStatement(sqlSelect);
            rs = stmt.executeQuery();
            while(rs.next())
            {   Cliente    uD = new Cliente();
                uD.setId(rs.getInt("id"));
                uD.setNome(rs.getString("nome"));
                uD.setTelefone(rs.getString("telefone"));
                uD.setEndereco(rs.getString("endereco"));
                aL.add(uD);
            }
        }
        catch(SQLException ex)
        {   System.out.println("Erro ao buscar todos os dados" + ex.toString());
        }
        finally
        {   ConnFactory.closeConn(conn, stmt);
        }   
        return aL;
    }

    public  ArrayList<Pedido> buscarTodosPedidos()
    {   ArrayList<Pedido> aL = new ArrayList<>();
        String   sqlSelect = "SELECT id, nome, preco, id_cliente FROM tb_pedido";
        Connection  conn = ConnFactory.getConn();
        PreparedStatement stmt = null;
        ResultSet rs;
        try
        {   stmt = conn.prepareStatement(sqlSelect);
            rs = stmt.executeQuery();
            while(rs.next())
            {   Pedido    uD = new Pedido();
                uD.setId(rs.getInt("id"));
                uD.setNome(rs.getString("nome"));
                uD.setPreco(rs.getDouble("preco"));
                uD.setIdCliente(rs.getInt("id_cliente"));
                aL.add(uD);
            }
        }
        catch(SQLException ex)
        {   System.out.println("Erro ao buscar todos os dados" + ex.toString());
        }
        finally
        {   ConnFactory.closeConn(conn, stmt);
        }   
        return aL;
    }

}