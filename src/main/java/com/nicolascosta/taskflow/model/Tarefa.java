package com.nicolascosta.taskflow.model;

import java.time.LocalDate;

public class Tarefa {
    private final long id;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private LocalDate prazo;
    private boolean concluida;

    public Tarefa(long id, String titulo, String descricao, Prioridade prioridade, LocalDate prazo) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.prazo = prazo;
    }

    public long getId() { return id; }
    public boolean isConcluida() { return concluida; }
    public void concluir() { this.concluida = true; }

    @Override
    public String toString() {
        return String.format("#%d [%s] %s | %s | prazo: %s",
                id, concluida ? "OK" : "PENDENTE", titulo, prioridade, prazo);
    }
}
