package diario.dados;

import java.util.Date;
import java.util.List;

public class Entrada {
    private String texto;
    private Date data;
    private List<String> categorias;

    public Entrada(String texto, Date data, List<String> categorias) {
        this.texto = texto;
        this.data = data;
        this.categorias = categorias;
    }

    public String getTexto() {
        return texto;
    }

    public Date getData() {
        return data;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    @Override
    public String toString() {
        return "Data: " + data + "\nTexto: " + texto + "\nCategorias: " + categorias + "\n";
    }
}
