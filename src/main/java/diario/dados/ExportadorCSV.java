package diario.dados;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportadorCSV implements Exportador {
    @Override
    public void exportar(List<Entrada> entradas, String nomeArquivo) throws IOException {
        FileWriter escritor = new FileWriter(nomeArquivo);
        escritor.write("Data,Texto,Categorias\n");
        for (Entrada entrada : entradas) {
            escritor.write(String.format("%s,\"%s\",\"%s\"\n",
                    entrada.getData(),
                    entrada.getTexto().replace("\"", "\"\""),
                    String.join(";", entrada.getCategorias())));
        }
        escritor.close();
    }
}
