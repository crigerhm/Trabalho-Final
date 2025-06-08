package diario.dados;


import java.util.ArrayList;
import java.util.List;

public class FiltroTexto implements Filtro {
    private final Filtro outro;
    private final String texto;

    public FiltroTexto(Filtro outro, String texto) {
        this.outro = outro;
        this.texto = texto.toLowerCase();
    }

    @Override
    public List<Entrada> filtrar(List<Entrada> entradas) {
        List<Entrada> filtradas = new ArrayList<>();
        for (Entrada e : outro.filtrar(entradas)) {
            if (e.getTexto().toLowerCase().contains(texto)) {
                filtradas.add(e);
            }
        }
        return filtradas;
    }
}
