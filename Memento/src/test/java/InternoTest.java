import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InternoTest {

    @Test
    @DisplayName("Deve inicializar o interno corretamente e com histórico vazio")
    void deveInicializarCorretamente(){
        String nomeEsperado = "Carlos Silva";

        Interno interno = new Interno(nomeEsperado);

        assertEquals(nomeEsperado, interno.getNome(), "Nome do internodeve ser 'Carlos Silva'");
        assertNull(interno.getEstadoAtual(), "Estado atual deve ser nulo na inicialização");
        assertTrue(interno.getHistoricoEstados().isEmpty(), "Histórico deve estar vazio na inicialização");
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar interno com nome nulo ou vazio")
    void deveLancarExcecaoNomeInvalido(){
        String nomeNulo = null;
        String nomeVazio = "" ;
        String nomeEmBranco = "   ";

    assertThrows(IllegalArgumentException.class, () -> new Interno(nomeNulo), "Deve lançar exceção ao criar interno com nome nulo");
    assertThrows(IllegalArgumentException.class, () -> new Interno(nomeVazio), "Deve lançar exceção ao criar interno com nome vazio");
    assertThrows(IllegalArgumentException.class, () -> new Interno(nomeEmBranco), "Deve lancar exceção ao criar interno com nome em branco");


    }

    @Test
    @DisplayName("Deve armazenar os estados sequencialmente no histórico")
    void deveArmazenarEstadosNoHistorico(){
        Interno interno = new Interno("Carlos Silva");
        EstadoInterno estadoAcautelado = EstadoAcautelado.getInstance();
        EstadoInterno estadoSuspenso = EstadoSuspenso.getInstance();
        EstadoInterno estadoLiberado = EstadoLiberado.getInstance();

        int quantidadeEsperada = 3;
        EstadoInterno primeirosEstadosEsperado = estadoAcautelado;
        EstadoInterno segundoEstadoEsperado = estadoSuspenso;
        EstadoInterno terceiroEstadoEsperado = estadoLiberado;

        interno.setEstado(estadoAcautelado);
        interno.setEstado(estadoSuspenso);
        interno.setEstado(estadoLiberado);

        List<EstadoInterno> historico = interno.getHistoricoEstados();
        assertEquals(quantidadeEsperada, historico.size(), "Histórico deve conter exatamente 3 estados");
        assertEquals(primeirosEstadosEsperado, historico.get(0), "Primeiro estado deve ser Acautelado");
        assertEquals(segundoEstadoEsperado, historico.get(1), "Segundo estado deve ser Suspenso");
        assertEquals(terceiroEstadoEsperado, historico.get(2), "Terceiro estado deve ser Liberado");
        assertEquals(terceiroEstadoEsperado, interno.getEstadoAtual(), "Estado atual deve ser o último estado adicionado (Liberado)");

    }

    @Test
    @DisplayName("Deve restaurar o estado inicial corretamente")
    void deveRestaurarEstadoInicial(){
        Interno interno = new Interno("Carlos Silva");
        EstadoInterno estadoInicial = EstadoAcautelado.getInstance();
        EstadoInterno estadoFinal = EstadoEvadido.getInstance();

        int indiceArestaurar = 0;

        EstadoInterno estadoEsperado = estadoInicial;

        interno.setEstado(estadoInicial);
        interno.setEstado (estadoFinal);

        interno.restaurarEstado(indiceArestaurar);

        assertEquals(estadoEsperado, interno.getEstadoAtual(), "Estado atual deve ser restaurado para Acautelado (índice 0)");


    }

    @Test
    @DisplayName("Deve restaurar um estado intermediario")
    void deveRestaurarEstadoIntermediario(){
        Interno interno = new Interno("Carlos Silva");
        EstadoInterno estado0 = EstadoAcautelado.getInstance();
        EstadoInterno estado1 = EstadoSuspenso.getInstance();
        EstadoInterno estado2 = EstadoAcautelado.getInstance();
        EstadoInterno estado3 = EstadoLiberado.getInstance();

        int indiceARestaurar = 1;

        EstadoInterno estadoEsperado = estado1;

        interno.setEstado(estado0);
        interno.setEstado (estado1);
        interno.setEstado (estado2);
        interno.setEstado (estado3);

        interno.restaurarEstado(indiceARestaurar);

        assertEquals(estadoEsperado, interno.getEstadoAtual(), "Estado atual deve ser restaurado para Suspenso (índice 1)");
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar definir estado nulo")
    void deveLancarExcecaoEstadoNulo(){
        Interno interno = new Interno("Carlos Silva");
        EstadoInterno estadoNulo = null;

        assertThrows(IllegalArgumentException.class, () -> interno.setEstado(estadoNulo), "Deve lançar exceção ao tentar definir estado nulo");
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar restaurar índice inválido")
    void deveLancarExcecaoIndiceInvalido(){
        Interno interno = new Interno("Carlos Silva");

        int indiceNegativo = -1;
        int indiceForaDoIntervalo1 = 1;
        int indiceForaDoIntervalo2 = 99;

        interno.setEstado(EstadoAcautelado.getInstance());

        assertThrows(IllegalArgumentException.class, () -> interno.restaurarEstado(indiceNegativo), "Deve lançar exceção ao restaurar com índice negativo");
        assertThrows(IllegalArgumentException.class, () -> interno.restaurarEstado(indiceForaDoIntervalo1), "Deve lançar exceção ao restaurar com índice fora do intervalo (1)");
        assertThrows(IllegalArgumentException.class, () -> interno.restaurarEstado(indiceForaDoIntervalo2),"Deve lançar exceção ao restaurar com índice fora do intervalo (99)");
    }

    @Test
    @DisplayName("Não deve permitir modificação direta da lista de histórico")
    void naoDevePermitirModificacaoDiretaDoHistorico(){
        Interno interno = new Interno("Carlos Silva");
        EstadoInterno estadoAcautelado = EstadoAcautelado.getInstance();
        EstadoInterno estadoLiberado = EstadoLiberado.getInstance();

        interno.setEstado(estadoAcautelado);
        List<EstadoInterno> historico = interno.getHistoricoEstados();

        assertThrows(UnsupportedOperationException.class, () -> historico.add(estadoLiberado), "Não deve permitir adicionar elementos á lista de histórico");
    }

    @Test
    @DisplayName("Deve testar as descrições textuais dos estados")
    void deveTestarDescricoesDosEstados(){
        EstadoInterno estadoAcautelado = EstadoAcautelado.getInstance();
        EstadoInterno estadoLiberado = EstadoLiberado.getInstance();
        EstadoInterno estadoEvadido = EstadoEvadido.getInstance();
        EstadoInterno estadoSuspenso = EstadoSuspenso.getInstance();

        String descricaoAcauteladoEsperada = "Acautelado";
        String descricaoLiberadoEsperada = "Liberado";
        String descricaoEvadidoEsperada = "Evadido";
        String descricaoSuspensoEsperada = "Suspenso";

        String descricaoAcautelado = estadoAcautelado.getDescricao();
        String descricaoLiberado = estadoLiberado.getDescricao();
        String descricaoEvadido = estadoEvadido.getDescricao();
        String descricaoSuspenso = estadoSuspenso.getDescricao();

        assertEquals(descricaoAcauteladoEsperada, descricaoAcautelado,
                "Descrição de Acautelado deve ser 'Acautelado'");

        assertEquals(descricaoLiberadoEsperada, descricaoLiberado,
                "Descrição de Liberado deve ser 'Liberado'");

        assertEquals(descricaoEvadidoEsperada, descricaoEvadido,
                "Descrição de Evadido deve ser 'Evadido'");

        assertEquals(descricaoSuspensoEsperada, descricaoSuspenso,
                "Descrição de Suspenso deve ser 'Suspenso'");
    }

}
