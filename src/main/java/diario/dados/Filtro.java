package diario.dados;

import java.util.List;

public interface Filtro {
    List<Entrada> filtrar(List<Entrada> entradas);
}
