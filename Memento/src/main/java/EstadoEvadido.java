public class EstadoEvadido implements EstadoInterno{

    private static final EstadoEvadido INSTANCE = new EstadoEvadido();

    private EstadoEvadido(){

    }

    public static EstadoEvadido getInstance(){
        return INSTANCE;
    }

    @Override
    public String getDescricao(){
        return "Evadido";
    }


}
