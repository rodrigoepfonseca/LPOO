package Controle;

//imports
import Modelo.ModeloFuncionarios;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.beans.Statement;


/**
 *Quando eu criei esse código só deus e eu sabíamos o que eu estava fazendo... Agora só deus sabe.
 * @author Rodrigo Fonseca (rodrigoepfonseca@gmail.com)
 * Criado para controle de pesquisar cadastrar, etc..
 */
public class ControleFuncionarios 
{
   //instânciar modelo conexão 
    ConexaoBD conexao = new ConexaoBD();
    // Instânciar o modelo do Funcionário
    ModeloFuncionarios modelo = new ModeloFuncionarios();
    
    // Método criado para Salvar.
    public void Salvar(ModeloFuncionarios modelo)
    {
        conexao.conexao();
        try 
        {
            PreparedStatement PassagemValor = conexao.con.prepareStatement("insert into FUNCIONARIOS( NOME_FUNCIONARIOS, IDADE_FUNCIONARIOS , CARGO_FUNCIONARIOS) values(?,?,?) ");
            // Mandar valores;
            PassagemValor.setString(1, modelo.getNome());
            PassagemValor.setInt(2, modelo.getIdade());
            PassagemValor.setString(3, modelo.getCargo());
            // serve para testar
            PassagemValor.execute();
            JOptionPane.showMessageDialog(null,"Dados Salvos!!\n");
        } catch (SQLException ex)
        {
            Logger.getLogger(ControleFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Dados não foram Salvos\n"+ex);
            
        }
        
    }
    
    //método criado para pesquisa
    public ModeloFuncionarios buscaFuncionario(ModeloFuncionarios modelo)
    {
        //instanciar conexão
        conexao.conexao();
        conexao.executaSql("SELECT * FROM FUNCIONARIOS WHERE NOME_FUNCIONARIOS LIKE'%"+modelo.getPesquisa()+"%'");
        try
        {
            conexao.rs.first();
            modelo.setCodigo(conexao.rs.getInt("id_funcionarios"));
            modelo.setNome(conexao.rs.getString("nome_funcionarios"));
            modelo.setCargo(conexao.rs.getString("Cargo_funcionarios"));
            modelo.setIdade(conexao.rs.getInt("idade_funcionarios"));
        } 
        catch (SQLException ex)
        {
            JOptionPane.showConfirmDialog(null,"Erro de banco"+ex);
        }
        conexao.desconecta();
        
        return modelo;
    }

    
    // método para alterar:
    public void Editar(ModeloFuncionarios modelo)
    {
        conexao.conexao();
        try
        {
            PreparedStatement pst= conexao.con.prepareStatement("update  FUNCIONARIOS SET NOME_FUNCIONARIOS=?, IDADE_FUNCIONARIOS=? , CARGO_FUNCIONARIOS=? where id_funcionarios = ?");
            pst.setString(1, modelo.getNome());
            pst.setInt(2, modelo.getIdade());
            pst.setString(3, modelo.getCargo());
            pst.setInt(4,modelo.getCodigo());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Dados Alterados!");
                    
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Erro, dado não adicionado!");
        }
        conexao.desconecta();
    }
    
    // método para exclusão de dados
    public void Excluir(ModeloFuncionarios modelo)
    {
         conexao.conexao();
        try {
            PreparedStatement pst = conexao.con.prepareStatement("DELETE FROM FUNCIONARIOS WHERE id_funcionarios = ?");
            pst.setInt(1,modelo.getCodigo());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Dados excluidos");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao excluir!");
        }
         conexao.desconecta();
    }
}
