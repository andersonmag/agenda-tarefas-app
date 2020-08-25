package com.bwn.todo.model;

import java.time.LocalDate;

public class Item {

    private Long id;
    private String descricao;
    private LocalDate dataCriacao;
    private boolean ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Item() {}

    public Item(Long id, String descricao, LocalDate dataCriacao, boolean ativo) {
        this.id = id;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.ativo = ativo;
    }
}