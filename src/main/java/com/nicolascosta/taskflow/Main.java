package com.nicolascosta.taskflow;

import com.nicolascosta.taskflow.model.Tarefa;
import com.nicolascosta.taskflow.model.Prioridade;
import com.nicolascosta.taskflow.service.GerenciadorTarefas;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GerenciadorTarefas gerenciador = new GerenciadorTarefas();

    public static void main(String[] args) {
        carregarDemo();
        int opcao;

        do {
            menu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> listar();
                case 2 -> criar();
                case 3 -> concluir();
                case 4 -> remover();
                case 5 -> resumo();
                case 0 -> System.out.println("\nAté mais! 👋");
                default -> System.out.println("\nOpção inválida.");
            }
        } while (opcao != 0);
    }

    private static void menu() {
        System.out.println("""

                =========================================
                  TASKFLOW // GERENCIADOR DE TAREFAS
                =========================================
                1. Listar tarefas
                2. Criar tarefa
                3. Concluir tarefa
                4. Remover tarefa
                5. Ver resumo
                0. Sair
                """);
    }

    private static void listar() {
        System.out.println("\n--- TAREFAS ---");
        gerenciador.listar().forEach(System.out::println);
    }

    private static void criar() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Prioridade (BAIXA, MEDIA, ALTA): ");
        Prioridade prioridade = Prioridade.valueOf(scanner.nextLine().trim().toUpperCase());
        System.out.print("Prazo (AAAA-MM-DD): ");
        LocalDate prazo = LocalDate.parse(scanner.nextLine().trim());

        Tarefa tarefa = gerenciador.criar(titulo, descricao, prioridade, prazo);
        System.out.println("Criada: " + tarefa);
    }

    private static void concluir() {
        long id = lerInteiro("ID da tarefa: ");
        System.out.println(gerenciador.concluir(id) ? "Tarefa concluída." : "ID não encontrado.");
    }

    private static void remover() {
        long id = lerInteiro("ID da tarefa: ");
        System.out.println(gerenciador.remover(id) ? "Tarefa removida." : "ID não encontrado.");
    }

    private static void resumo() {
        System.out.printf("%nTotal: %d | Pendentes: %d | Concluídas: %d%n",
                gerenciador.total(), gerenciador.pendentes(), gerenciador.concluidas());
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    private static void carregarDemo() {
        gerenciador.criar("Estudar Java", "Revisar classes, objetos e encapsulamento.",
                Prioridade.ALTA, LocalDate.now().plusDays(3));
        gerenciador.criar("Documentar projeto", "Atualizar o README para publicação no GitHub.",
                Prioridade.MEDIA, LocalDate.now().plusDays(5));
    }
}
