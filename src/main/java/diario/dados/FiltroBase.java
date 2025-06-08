package diario.dados;

import java.util.List;

public class FiltroBase implements Filtro {
    @Override
    public List<Entrada> filtrar(List<Entrada> entradas) {
        return entradas;
    }
}
