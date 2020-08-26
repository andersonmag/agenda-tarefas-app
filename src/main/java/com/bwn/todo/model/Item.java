package com.bwn.todo.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Item {

    private Long id;

    private String descricao;
    private LocalDate dataCriacao;
    private boolean ativo;
    private boolean realizado;

    public Long getId() {
        return id;
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

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public Item() {}

    public Item(Long id, String descricao, LocalDate dataCriacao, boolean ativo, boolean realizado) {
        this.id = id;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.ativo = ativo;
        this.realizado = realizado;
    }

    public Item(String descricao, LocalDate dataCriacao, boolean ativo, boolean realizado) {
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.ativo = ativo;
        this.realizado = realizado;
    }

    @Override
    public String toString() {
        return "\n\t\t\tID: " + getId() +
               "\n\t\t\tDescrição: " + getDescricao() +
               "\n\t\t\tRealizado: " + isRealizado() +
               "\n\t\t\tData de criação: " + getDataCriacao()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + 
               "\n\t\t\tAtivo: " + isAtivo() + 
               "\n\t\t\t    ------------";
    }

}