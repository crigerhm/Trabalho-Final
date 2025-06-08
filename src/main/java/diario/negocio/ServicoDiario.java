package diario.negocio;

import diario.dados.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ServicoDiario {
    private final RepositorioEntradas repositorio = RepositorioEntradas.getInstancia();

    public void adicionarEntrada(String texto, Date data, List<String> categorias) {
        Entrada nova = new Entrada(texto, data, categorias);
        repositorio.adicionarEntrada(nova);
    }

    public List<Entrada> listarEntradas() {
        return repositorio.getEntradas();
    }

    public void exportarEntradas(String tipo, String nomeArquivo) throws IOException {
        Exportador exportador;
        if (tipo.equalsIgnoreCase("csv")) {
            exportador = new ExportadorCSV();
        } else {
            exportador = new ExportadorTXT();
        }
        exportador.exportar(repositorio.getEntradas(), nomeArquivo);
    }

    public List<Entrada> filtrarEntradas(String texto, Date inicio, Date fim, String categoria) {
        List<Entrada> todas = repositorio.getEntradas();
        Filtro filtro = new FiltroBase();

        if (texto != null && !texto.isBlank()) {
            filtro = new FiltroTexto(filtro, texto);
        }
        if (inicio != null && fim != null) {
            filtro = new FiltroData(filtro, inicio, fim);
        }
        if (categoria != null && !categoria.isBlank()) {
            filtro = new FiltroCategoria(filtro, categoria);
        }

        return filtro.filtrar(todas);
    }






}
