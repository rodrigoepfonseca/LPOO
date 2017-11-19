package Modelo;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * criado para apresnetação dos funcioários na tabela
 *Quando eu criei esse código só deus e eu sabíamos o que eu estava fazendo... Agora só deus sabe.
 * @author Rodrigo Fonseca (rodrigoepfonseca@gmail.com)
 */
public class ModeloTabela  extends AbstractTableModel
{

    private ArrayList linhas = null;
    private String[] colunas = null;
    
    public ModeloTabela(ArrayList  linhas, String[] colunas)
    {
        setLinhas(linhas);
        setColunas(colunas);
    }

    /**
     * @return the linhas
     */
    public ArrayList getLinhas() {
        return linhas;
    }

    /**
     * @param linhas the linhas to set
     */
    public void setLinhas(ArrayList linhas) {
        this.linhas = linhas;
    }

    /**
     * @return the colunas
     */
    public String[] getColunas() {
        return colunas;
    }

    /**
     * @param colunas the colunas to set
     */
    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
    
    public int getColumnCount()
    {
        return colunas.length;
    }
    public int getRowCount()
    {
        return linhas.size();
    }
    public String getColumnName(int numColunas)
    {
        return colunas[numColunas];
    }
    
    public Object getValueAt(int numLinha, int numColuna)
    {
        Object[] linha = (Object[])getLinhas().get(numLinha);
        return linha[numColuna];
        
    }
}
