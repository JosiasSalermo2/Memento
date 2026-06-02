public class EstadoAcautelado implements EstadoInterno {

    private static final EstadoAcautelado INSTANCE = new EstadoAcautelado();

    private EstadoAcautelado(){

    }

    public static EstadoAcautelado getInstance(){
        return INSTANCE;
    }

    @Override
    public String getDescricao(){
        return "Acautelado";
    }
}
