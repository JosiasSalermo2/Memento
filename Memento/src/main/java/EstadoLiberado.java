public class EstadoLiberado implements EstadoInterno{

    private static final EstadoLiberado INSTANCE = new EstadoLiberado();

    private EstadoLiberado(){}

    public static EstadoLiberado getInstance(){
        return INSTANCE;
    }

    @Override
    public String getDescricao(){
        return "Liberado";
    }
}
