package com.bwn.todo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bwn.todo.database.connection.DBConnection;
import com.bwn.todo.model.Item;

public class ItemDAO {

    private Connection connection;
    private String sql;

    public ItemDAO() {
        this.connection = DBConnection.getConnection();
    }

    public void salvar(Item item) {
        this.sql = "insert into item(id, descricao, \"dataCriacao\", ativo) values(?, ?, ?, ?)";

        try {

            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setLong(1, item.getId());
            statement.setString(2, item.getDescricao());
            statement.setDate(3, Date.valueOf(item.getDataCriacao()));
            statement.setBoolean(4, item.isAtivo());

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
                                resultSet.getBoolean("ativo"));
            }

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

	public void editar(Item item) {
        this.sql = "update item set descricao = ? where id = " + item.getId();

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, item.getDescricao());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

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

}