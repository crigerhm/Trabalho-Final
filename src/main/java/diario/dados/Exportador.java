package diario.dados;

import java.io.IOException;
import java.util.List;

public interface Exportador {
    void exportar(List<Entrada> entradas, String nomeArquivo) throws IOException;
}
