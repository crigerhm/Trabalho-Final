package diario.dados;

import java.util.ArrayList;
import java.util.List;

public class FiltroCategoria implements Filtro {
    private final Filtro outro;
    private final String categoria;

    public FiltroCategoria(Filtro outro, String categoria) {
        this.outro = outro;
        this.categoria = categoria.toLowerCase();
    }

    @Override
    public List<Entrada> filtrar(List<Entrada> entradas) {
        List<Entrada> filtradas = new ArrayList<>();
        for (Entrada e : outro.filtrar(entradas)) {
            for (String cat : e.getCategorias()) {
                if (cat.toLowerCase().equals(categoria)) {
                    filtradas.add(e);
                    break;
                }
            }
        }
        return filtradas;
    }
}
