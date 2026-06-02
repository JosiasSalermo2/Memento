import java.util.List;

public class Interno {

    private final String nome;
    private EstadoInterno estadoAtual;
    private final GestorHistorico gestorHistorico;

    public Interno(String nome){
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome do interno é obrigatório");
        }
        this.nome = nome;
        this.gestorHistorico = new GestorHistorico();
    }

    public String getNome(){
        return nome;
    }

    public EstadoInterno getEstadoAtual(){
        return estadoAtual;
    }

    public void setEstado(EstadoInterno novoEstado){
        if(novoEstado == null){
            throw new IllegalArgumentException("Estado inválido");
        }
        this.estadoAtual = novoEstado;
        this.gestorHistorico.adicionar(novoEstado);
    }

    public void restaurarEstado(int indice){
        this.estadoAtual = this.gestorHistorico.obter(indice);
    }

    public List<EstadoInterno> getHistoricoEstados(){
        return this.gestorHistorico.getHistoricoCompleto();
    }
}
