package diario.dados;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEntradas {
    private static RepositorioEntradas instancia;
    private List<Entrada> entradas = new ArrayList<>();

    private RepositorioEntradas() {}

    public static RepositorioEntradas getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioEntradas();
        }
        return instancia;
    }

    public void adicionarEntrada(Entrada entrada) {
        entradas.add(entrada);
    }

    public List<Entrada> getEntradas() {
        return new ArrayList<>(entradas);
    }

    public void limparEntradas() {
        entradas.clear();
    }
}
