package com.bwn.todo;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import com.bwn.todo.dao.ItemDAO;
import com.bwn.todo.model.Item;

public final class App {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        ItemDAO itemDAO = new ItemDAO();

        boolean ativoDefault = true;
        boolean realizadoDefault = false;
        int opcao = 0;

        do {

            System.out.println();
            System.out.println("\t\t\tAdicionar um novo Item (Opção 1)");
            System.out.println("\t\t\tEditar um Item existente (Opção 2)");
            System.out.println("\t\t\tRemover um Item existente (Opção 3)");
            System.out.println("\t\t\tBuscar um Item existente (Opção 4)");
            System.out.println("\t\t\tDefinir um Item como realizado (Opção 5)");
            System.out.println();
            System.out.println("\t\t\tVer todos os itens realizados (Opção 6)");
            System.out.println("\t\t\tVer todos os itens ativos (Opção 7)");
            System.out.println("\t\t\tFinalizar o Programa! (Opção 0)");
            opcao = scanner.nextInt();

            System.out.println();
            scanner.nextLine();

            switch (opcao) {

                case 1:

                    Item item = new Item();
                    System.out.println("\t\t\t\tDigite a Descrição: ");
                    item.setDescricao(scanner.nextLine());
                    item.setAtivo(ativoDefault);
                    item.setRealizado(realizadoDefault);
                    item.setDataCriacao(LocalDate.now());

                    itemDAO.salvar(item);

                    System.out.println("Cadastrado com sucesso!!");
                    Thread.sleep(2000);

                    break;

                case 2:

                    System.out.println("\t\t\t\tDigite o ID do Item: ");
                    Long id = scanner.nextLong();

                    Item itemEncontrado = itemDAO.buscarPorId(id);
                    scanner.nextLine();

                    if (verificarItemValido(itemEncontrado)) {
                        System.out.println("\t\t\t\tDigite a nova Descrição: ");
                        String novaDescricao = scanner.nextLine();

                        itemEncontrado.setDescricao(novaDescricao);
                        Item itemAlterado = itemDAO.editar(itemEncontrado);

                        System.out.println("Item alterado com sucesso!!");
                        System.out.println(itemAlterado.toString());

                        Thread.sleep(2000);
                    } else {
                        System.err.println("Item não encontrado para esse ID.");
                        Thread.sleep(2000);
                    }

                    break;

                case 3:

                    System.out.println("\t\t\t\tDigite o ID do Item: ");
                    id = scanner.nextLong();

                    itemEncontrado = itemDAO.buscarPorId(id);

                    if (verificarItemValido(itemEncontrado)) {
                        itemDAO.deletar(itemEncontrado.getId());

                        System.out.println("Item deletado com sucesso!");
                        Thread.sleep(2000);
                    } else {
                        System.err.println("Item não encontrado para esse ID.");
                        Thread.sleep(2000);
                    }

                    break;

                case 4:

                    System.out.println("\t\t\t\tDigite o ID do Item: ");
                    id = scanner.nextLong();

                    itemEncontrado = itemDAO.buscarPorId(id);

                    if (verificarItemValido(itemEncontrado)) {

                        System.out.println(itemEncontrado.toString());
                        Thread.sleep(4000);
                    } else {
                        System.err.println("Item não encontrado para esse ID.");
                        Thread.sleep(2000);
                    }

                    break;

                case 5:

                    System.out.println("\t\t\t\tDigite o ID do Item: ");
                    id = scanner.nextLong();

                    itemEncontrado = itemDAO.buscarPorId(id);

                    if (verificarItemValido(itemEncontrado)) {
                        if (itemEncontrado.isRealizado()) {
                            System.err.println("\tItem já foi realizado!");
                            break;
                        }

                        itemEncontrado.setAtivo(false);
                        itemEncontrado.setRealizado(true);
                        itemDAO.alterarStatusRealizado(itemEncontrado);

                        System.out.println("\tItem definido como realizado!");

                        Thread.sleep(2000);

                    } else {
                        System.err.println("Item não encontrado para esse ID.");
                        Thread.sleep(2000);
                    }

                    break;

                case 6:

                    List<Item> itensRealizados = itemDAO.buscarTodosItensRealizados();
                    itensRealizados.stream().forEach(itemIt -> System.out.println(itemIt.toString()));

                    if (itensRealizados.isEmpty()) {
                        System.err.println("\tNão há Itens realizados!!");
                    }

                    break;

                case 7:

                    List<Item> itensAtivos = itemDAO.buscarTodosItensAtivos();
                    itensAtivos.stream().forEach(itemIt -> System.out.println(itemIt.toString()));

                    if (itensAtivos.isEmpty()) {
                        System.err.println("\tNão há Itens ativos!!");
                    }

                    break;

                case 0:
                    break;

                default:
                    System.err.println("\tOpção Invalida!!");
                    break;
            }

        } while (opcao != 0);

        System.out.println();
        System.out.println("Finalizando ..");
        scanner.close();
    }

    private static boolean verificarItemValido(Item itemEncontrado) {
        return itemEncontrado.getId() != null;
    }
}
