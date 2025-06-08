package diario.dados;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FiltroData implements Filtro {
    private final Filtro outro;
    private final Date inicio;
    private final Date fim;

    public FiltroData(Filtro outro, Date inicio, Date fim) {
        this.outro = outro;
        this.inicio = inicio;
        this.fim = fim;
    }

    @Override
    public List<Entrada> filtrar(List<Entrada> entradas) {
        List<Entrada> filtradas = new ArrayList<>();
        for (Entrada e : outro.filtrar(entradas)) {
            if (!e.getData().before(inicio) && !e.getData().after(fim)) {
                filtradas.add(e);
            }
        }
        return filtradas;
    }
}
