package com.nicolascosta.taskflow.service;

import com.nicolascosta.taskflow.model.Prioridade;
import com.nicolascosta.taskflow.model.Tarefa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GerenciadorTarefas {
    private final List<Tarefa> tarefas = new ArrayList<>();
    private long proximoId = 1;

    public Tarefa criar(String titulo, String descricao, Prioridade prioridade, LocalDate prazo) {
        Tarefa tarefa = new Tarefa(proximoId++, titulo, descricao, prioridade, prazo);
        tarefas.add(tarefa);
        return tarefa;
    }

    public List<Tarefa> listar() {
        return Collections.unmodifiableList(tarefas);
    }

    public boolean concluir(long id) {
        return tarefas.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .map(t -> { t.concluir(); return true; })
                .orElse(false);
    }

    public boolean remover(long id) {
        return tarefas.removeIf(t -> t.getId() == id);
    }

    public long total() { return tarefas.size(); }
    public long concluidas() { return tarefas.stream().filter(Tarefa::isConcluida).count(); }
    public long pendentes() { return total() - concluidas(); }
}
