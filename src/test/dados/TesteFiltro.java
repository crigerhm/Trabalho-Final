package test.java.diario.dados;

import diario.dados.*;

import java.util.*;

public class TesteFiltro {

    private static List<Entrada> entradas;

    public static void main(String[] args) {
        prepararEntradas();

        testarFiltroTexto();
        testarFiltroData();
        testarFiltroCategoria();
        testarFiltroTextoECategoria();
        testarFiltroTextoEData();
        testarFiltroDataECategoria();
        testarTodosFiltros();

        System.out.println("Todos os testes manuais executados.");
    }

    private static void prepararEntradas() {
        entradas = new ArrayList<>();

        entradas.add(new Entrada("Estudando Java", criarData("01/06/2024"), Arrays.asList("Java", "Estudo")));
        entradas.add(new Entrada("Revisão de padrões", criarData("05/06/2024"), Arrays.asList("Arquitetura")));
        entradas.add(new Entrada("Dia de descanso", criarData("10/06/2024"), Arrays.asList("Pessoal")));
        entradas.add(new Entrada("Codando em Python", criarData("15/06/2024"), Arrays.asList("Python")));
    }

    private static Date criarData(String data) {
        try {
            return new java.text.SimpleDateFormat("dd/MM/yyyy").parse(data);
        } catch (Exception e) {
            return null;
        }
    }

    private static void testarFiltroTexto() {
        Filtro filtro = new FiltroTexto(new FiltroBase(), "java");
        List<Entrada> resultado = filtro.filtrar(entradas);
        assertar(resultado.size() == 1, "Filtro por texto");
        assertar(resultado.get(0).getTexto().contains("Java"), "Conteúdo da entrada com texto 'Java'");
    }

    private static void testarFiltroData() {
        Filtro filtro = new FiltroData(new FiltroBase(), criarData("01/06/2024"), criarData("07/06/2024"));
        List<Entrada> resultado = filtro.filtrar(entradas);
        assertar(resultado.size() == 2, "Filtro por data");
    }

    private static void testarFiltroCategoria() {
        Filtro filtro = new FiltroCategoria(new FiltroBase(), "Python");
        List<Entrada> resultado = filtro.filtrar(entradas);
        assertar(resultado.size() == 1, "Filtro por categoria");
        assertar("Codando em Python".equals(resultado.get(0).getTexto()), "Texto correto da entrada Python");
    }

    private static void testarFiltroTextoECategoria() {
        Filtro filtro = new FiltroCategoria(new FiltroTexto(new FiltroBase(), "python"), "Python");
        List<Entrada> resultado = filtro.filtrar(entradas);
        assertar(resultado.size() == 1, "Filtro texto + categoria");
    }

    private static void testarFiltroTextoEData() {
        Filtro filtro = new FiltroData(new FiltroTexto(new FiltroBase(), "codando"), criarData("01/06/2024"), criarData("30/06/2024"));
        List<Entrada> resultado = filtro.filtrar(entradas);
        assertar(resultado.size() == 1, "Filtro texto + data");
    }

    private static void testarFiltroDataECategoria() {
        Filtro filtro = new FiltroCategoria(new FiltroData(new FiltroBase(), criarData("01/06/2024"), criarData("10/06/2024")), "Pessoal");
        List<Entrada> resultado = filtro.filtrar(entradas);
        assertar(resultado.size() == 1, "Filtro data + categoria");
    }

    private static void testarTodosFiltros() {
        Filtro filtro = new FiltroCategoria(
                new FiltroData(
                        new FiltroTexto(new FiltroBase(), "estudando"),
                        criarData("01/06/2024"), criarData("30/06/2024")),
                "Estudo"
        );
        List<Entrada> resultado = filtro.filtrar(entradas);
        assertar(resultado.size() == 1, "Filtro completo (texto + data + categoria)");
    }

    private static void assertar(boolean condicao, String nomeTeste) {
        if (condicao) {
            System.out.println("[OK] " + nomeTeste);
        } else {
            System.out.println("[FALHA] " + nomeTeste);
        }
    }
}
