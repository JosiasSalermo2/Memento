import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorHistorico {

    private final List<EstadoInterno> historico = new ArrayList<>();

    public void adicionar(EstadoInterno estado){
        if(estado == null){
            throw new IllegalArgumentException("Estado não pode ser nulo");
        }
        historico.add(estado);
    }

    public EstadoInterno obter(int indice){
        if(indice < 0 || indice >= historico.size()){
            throw new IllegalArgumentException("Indice de histórico inválido");
        }
        return historico.get(indice);
    }

    public int getQuantidadeEstados(){
        return historico.size();
    }

    public List<EstadoInterno> getHistoricoCompleto(){
        return Collections.unmodifiableList(historico);
    }
}
