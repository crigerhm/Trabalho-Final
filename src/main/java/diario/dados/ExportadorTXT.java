package diario.dados;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ExportadorTXT implements Exportador {
    @Override
    public void exportar(List<Entrada> entradas, String nomeArquivo) throws IOException {
        FileWriter escritor = new FileWriter(nomeArquivo);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Entrada entrada : entradas) {
            escritor.write("Data: " + sdf.format(entrada.getData()) + "\n");
            escritor.write("Texto: " + entrada.getTexto() + "\n");
            escritor.write("Categorias: " + String.join(", ", entrada.getCategorias()) + "\n");
            escritor.write("--------------------------------------------------\n");
        }

        escritor.close();
    }
}
