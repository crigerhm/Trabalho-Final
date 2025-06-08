package diario.apresentacao;

import diario.dados.Entrada;
import diario.negocio.ServicoDiario;
import diario.negocio.Conversor;

import java.util.*;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final ServicoDiario servico = new ServicoDiario();

    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1 -> novaEntrada();
                case 2 -> filtrarEntradas();

                case 3 -> exportarEntradas();
                case 4 -> System.out.println("Saindo do programa...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 4);
    }

    private void exibirMenu() {
        System.out.println("\nBem-vindo ao Diário em Linha de Comando.");
        System.out.println("Selecione uma opção:");
        System.out.println("1. Nova entrada");
        System.out.println("2. Filtrar entradas");

        System.out.println("3. Exportar entradas");
        System.out.println("4. Sair");
        System.out.print("Opção: ");
    }

    private void novaEntrada() {
        try {
            System.out.print("Digite o texto da entrada: ");
            String texto = scanner.nextLine();

            System.out.print("Digite a data (dd/MM/yyyy): ");
            String dataStr = scanner.nextLine();
            Date data = Conversor.stringParaData(dataStr);

            System.out.print("Digite as categorias separadas por vírgula: ");
            String categoriasStr = scanner.nextLine();
            List<String> categorias = Arrays.asList(categoriasStr.split(","));

            servico.adicionarEntrada(texto, data, categorias);
            System.out.println("Entrada adicionada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar entrada: " + e.getMessage());
        }
    }

    private void listarEntradas() {
        var entradas = servico.listarEntradas();
        if (entradas.isEmpty()) {
            System.out.println("Nenhuma entrada encontrada.");
        } else {
            for (var entrada : entradas) {
                System.out.println(entrada);
            }
        }
    }

    private void exportarEntradas() {
        try {
            System.out.print("Digite o tipo de exportação (csv/txt): ");
            String tipo = scanner.nextLine();

            System.out.print("Digite o nome do arquivo (ex: diario.csv): ");
            String nome = scanner.nextLine();

            servico.exportarEntradas(tipo, nome);
            System.out.println("Exportação realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro na exportação: " + e.getMessage());
        }
    }

    private void filtrarEntradas() {
        try {
            System.out.print("Digite texto para buscar (ou pressione Enter para ignorar): ");
            String texto = scanner.nextLine();
            if (texto.isBlank()) texto = null;

            System.out.print("Digite data inicial (dd/MM/yyyy) ou Enter: ");
            String dataInicioStr = scanner.nextLine();
            Date dataInicio = dataInicioStr.isBlank() ? null : Conversor.stringParaData(dataInicioStr);

            System.out.print("Digite data final (dd/MM/yyyy) ou Enter: ");
            String dataFimStr = scanner.nextLine();
            Date dataFim = dataFimStr.isBlank() ? null : Conversor.stringParaData(dataFimStr);

            System.out.print("Digite categoria (ou Enter para ignorar): ");
            String categoria = scanner.nextLine();
            if (categoria.isBlank()) categoria = null;

            List<Entrada> resultados = servico.filtrarEntradas(texto, dataInicio, dataFim, categoria);
            if (resultados.isEmpty()) {
                System.out.println("Nenhuma entrada encontrada com os critérios informados.");
            } else {
                System.out.println("Entradas encontradas:");
                for (Entrada e : resultados) {
                    System.out.println(e);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao aplicar filtros: " + e.getMessage());
        }
    }

}
