public class EstadoSuspenso implements EstadoInterno{

    private static final EstadoSuspenso INSTANCE = new EstadoSuspenso();

    private EstadoSuspenso(){}

    public static EstadoSuspenso getInstance(){
        return INSTANCE;
    }

    @Override
    public String getDescricao(){
        return "Suspenso";
    }
}
