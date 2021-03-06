package com.bwn.todo.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import com.bwn.todo.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ItemDAOTest {

    private ItemDAO itemDAO;

    @BeforeEach
    private void init() {
        itemDAO = new ItemDAO();
    }

    @Test
    @DisplayName(value = "Deve salvar um novo Item")
    public void salvarItem() {
        Item item = getItem();

        itemDAO.salvar(item);
    }

    @Test
    @DisplayName(value = "Deve buscar e retornar, por ID, um Item Existente!")
    public void buscarItemPorId() {
        Long id = 1L;
        Item itemExistente = itemDAO.buscarPorId(id);
        
        assertNotNull(itemExistente);
    }

    @Test
    @DisplayName(value = "Deve retornar null ao tentar buscar Item Inexistente!")
    public void buscarItemInexistente() {
        Long id = 50L;
        Item itemInexistente = itemDAO.buscarPorId(id);
        
        assertNull(itemInexistente.getId());
    }
    
    @Test
    @DisplayName(value = "Deve buscar e retornar, pela Descrição, um Item Existente")
    public void buscarItemPorDescricao() {
        String descricao = "Estudar JS";
        Item itemExistente = itemDAO.buscarPorDescricao(descricao);
        
        assertNotNull(itemExistente.getId());
    }

    @Test
    @DisplayName(value = "Deve retornar null ao tentar buscar Item, por Descrição Inexistente!")
    public void buscarItemPorDescricaoInexistente() {
        String descricao = "Descrição inexistente!";
        Item itemInexistente = itemDAO.buscarPorDescricao(descricao);
        
        assertNull(itemInexistente.getDescricao());
    }

    @Test
    @DisplayName(value = "Deve editar e retornar um Item existente!")
    public void editarItem() {
        Long id =  1L;
        Item itemExistente = itemDAO.buscarPorId(id);

        itemExistente.setDescricao("Nova Descrição");
        Item itemAlterado = itemDAO.editar(itemExistente);

        assertNotNull(itemAlterado.getId());
        assertEquals(itemExistente.getDescricao(), itemAlterado.getDescricao());
    }

    @Test
    @DisplayName(value = "Deve retornar null ao tentar editar um Item com ID inexistente!")
    public void editarItemInexistente() {
        Long id =  15L;
        Item itemInexistente = itemDAO.buscarPorId(id);
        
        itemInexistente.setDescricao("Nova Descrição");
        Item itemAlterado = itemDAO.editar(itemInexistente);

        assertNull(itemAlterado.getId());
    }

    @Test
    @DisplayName(value = "Deve deletar um Item existente!")
    public void deletarItem() {
        Long id = 1L;
        Item itemExistente = itemDAO.buscarPorId(id);

        itemDAO.deletar(itemExistente.getId());
    }

    @Test
    @DisplayName(value = "Deve alterar status de realizado")
    public void alterarStatusRealizado() {
        Long id = 1L;
        boolean realizado = true;
        boolean ativo = false;
        Item itemExistente = itemDAO.buscarPorId(id);
        itemExistente.setRealizado(realizado);
        itemExistente.setAtivo(ativo);
        
        itemDAO.alterarStatusRealizado(itemExistente);
    }

    @Test
    @DisplayName(value = "Deve retornar uma Lista, não vazia, com todos os itens realizados!")
    public void buscarTodosItensRealizados() {
        List<Item> itensRealizados = itemDAO.buscarTodosItensRealizados();

        assertFalse(itensRealizados.isEmpty());
    }

    @Test
    @DisplayName(value = "Deve retornar uma Lista vazia ao não encontrar itens realizados!")
    public void retornarListaVaziaAoBuscarItensRealizados() {
        List<Item> itensRealizados = itemDAO.buscarTodosItensRealizados();

        assertTrue(itensRealizados.isEmpty());
    }

    @Test
    @DisplayName(value = "Deve retornar uma Lista, não vazia, com todos os itens ativos!")
    public void buscarTodosItensAtivos() {
        List<Item> itensRealizados = itemDAO.buscarTodosItensRealizados();

        assertFalse(itensRealizados.isEmpty());
    }

    @Test
    @DisplayName(value = "Deve retornar uma Lista vazia ao não encontrar itens ativos!")
    public void retornarListaVaziaAoBuscarItensAtivos() {
        List<Item> itensRealizados = itemDAO.buscarTodosItensRealizados();

        assertTrue(itensRealizados.isEmpty());
    }

    private Item getItem() {
        return new Item("Estudar JS", LocalDate.now(), true, false);
    }
}