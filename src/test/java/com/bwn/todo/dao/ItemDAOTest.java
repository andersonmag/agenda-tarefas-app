package com.bwn.todo.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;
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
    @DisplayName(value = "Deve buscar e retornar por ID um Item Existente!")
    public void buscarItemPorId() {
        Long id = 1L;
        Item itemExistente = itemDAO.buscarPorId(id);
        
        assertNotNull(itemExistente);
    }

    @Test
    @DisplayName(value = "Deve editar um Item existente!")
    public void editarItem() {
        Long id = 1L;
        Item itemExistente = itemDAO.buscarPorId(id);

        itemExistente.setDescricao("Nova Descrição");

        itemDAO.editar(itemExistente);
    }

    @Test
    @DisplayName(value = "Deve deletar um Item existente!")
    public void deletarItem() {
        Long id = 1L;
        Item itemExistente = itemDAO.buscarPorId(id);

        itemDAO.deletar(itemExistente.getId());
    }

    private Item getItem() {
        return new Item(1L, "Fazer Projeto POO", LocalDate.now(), true);
    }
    
}