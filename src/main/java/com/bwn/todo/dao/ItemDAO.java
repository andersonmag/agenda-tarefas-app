package com.bwn.todo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bwn.todo.database.connection.DBConnection;
import com.bwn.todo.model.Item;

public class ItemDAO {

    private Connection connection;
    private String sql;

    public ItemDAO() {
        this.connection = DBConnection.getConnection();
    }

    public void salvar(Item item) {
        this.sql = "insert into item(descricao, \"dataCriacao\", ativo, realizado) values(?, ?, ?, ?)";

        try {

            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, item.getDescricao());
            statement.setDate(2, Date.valueOf(item.getDataCriacao()));
            statement.setBoolean(3, item.isAtivo());
            statement.setBoolean(4, item.isRealizado());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Item buscarPorId(Long id) {
        this.sql = "select * from item where id = " + id;
        Item item = new Item();

        try {

            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                item = new Item(resultSet.getLong("id"),
                                resultSet.getString("descricao"),
                                resultSet.getDate("dataCriacao").toLocalDate(),
                                resultSet.getBoolean("ativo"),
                                resultSet.getBoolean("realizado"));
            }

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

	public Item buscarPorDescricao(String descricao) {
        this.sql = "select * from item where descricao = ?";
        Item item = new Item();

        try {

            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, descricao);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                item = new Item(resultSet.getLong("id"),
                                resultSet.getString("descricao"),
                                resultSet.getDate("dataCriacao").toLocalDate(),
                                resultSet.getBoolean("ativo"),
                                resultSet.getBoolean("realizado"));
            }

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
	}
    
	public void alterarStatusRealizado(Item item) {
        this.sql = "update item set realizado = ?, ativo = ? where id = " + item.getId();

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setBoolean(1, item.isRealizado());
            statement.setBoolean(2, item.isAtivo());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public Item editar(Item item) {
        this.sql = "update item set descricao = ? where id = " + item.getId();
        Item itemAlterado = new Item();

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, item.getDescricao());

            statement.execute();

            itemAlterado = buscarPorId(item.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemAlterado;

	}

	public void deletar(Long id) {
        this.sql = "delete from item where id = " + id;

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public List<Item> buscarTodosItensRealizados() {
        this.sql = "select * from item where realizado = true";
        List<Item> itensRealizados = new ArrayList<>();

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                itensRealizados.add(new Item(resultSet.getLong("id"),
                                             resultSet.getString("descricao"),
                                             resultSet.getDate("dataCriacao").toLocalDate(),
                                             resultSet.getBoolean("ativo"),
                                             resultSet.getBoolean("realizado")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itensRealizados;
    }
    
    public List<Item> buscarTodosItensAtivos() {
        this.sql = "select * from item where ativo = true";
        List<Item> itensRealizados = new ArrayList<>();

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                itensRealizados.add(new Item(resultSet.getLong("id"),
                                             resultSet.getString("descricao"),
                                             resultSet.getDate("dataCriacao").toLocalDate(),
                                             resultSet.getBoolean("ativo"),
                                             resultSet.getBoolean("realizado")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itensRealizados;
	}

}