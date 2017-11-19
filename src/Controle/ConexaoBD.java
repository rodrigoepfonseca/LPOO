package Controle;

/**
 *Quando eu criei esse código só deus e eu sabíamos o que eu estava fazendo... Agora só deus sabe.
 * @author Rodrigo Fonseca (rodrigoepfonseca@gmail.com)
 * Conexação como o Banco Mysql, teste 1
 * Importa bibliotecas de conexão.
 */

// Bibliotecas
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexaoBD 
{
    //Variaveis
    
    // Serve para fazer pesquisa no banco de dados"Statement"
    public Statement stm;
    // Serve para armazenar o resultado da pesquisa
    public ResultSet rs; 
    // responsavel pela conexão
    public Connection con;
    // Deve ser privado para Acesso só em nossa classe.
    // serve para identicar o driver
    private String driver = "com.mysql.jdbc.Driver";
    // informa onde está o nosso banco de dados 
    private String caminho = "jdbc:mysql://localhost:3306/teste";
    // usuário e senha Mysql
    private String usuario = "root";
    private String senha = "welcome1";
    
    
    // método para efetuar a conexão
    public void conexao()
    {
        System.setProperty("jdbc.Drivers",driver);
        try
        {
            con=DriverManager.getConnection(caminho, usuario, senha);
            JOptionPane.showMessageDialog(null,"Conexão Efetuada:\n");
                    
        } catch (SQLException ex) 
        {
            //Obs "+ex" informa qual o erro que deu durante a conexão
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Erro de conexão :\n"+ex.getMessage());
            
        }
        
    }
    // método usado para fazer consulta no banco, passamos o paramento String pois é o código que será executado no banco 
    // o TYPE_SCROLL_INSENSITIVE serve para diferenciar minusclo de maiuscolo.
    // CONCUR_READ_ONLY serve para ir do inicio a fim do banco durante a leitura.
    public void executaSql(String sql)
    {
        try {
            stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
            rs =  stm.executeQuery(sql);
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Erro executa SQL:\n"+ex.getMessage());
            
        }
        
    }
    
    // método para desconctar
    public void desconecta()
    {
        try
        {
            con.close();
            JOptionPane.showMessageDialog(null, "Desconectado!\n");
        } catch (SQLException ex) 
        {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Erro ao fechar conexão\n" +ex.getMessage());
        }
    }

    public void con() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    


}
