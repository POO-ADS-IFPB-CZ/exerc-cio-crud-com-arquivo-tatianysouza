package view;

import model.Pessoa;
import dao.PessoaDao;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        PessoaDao pessoaDao = new PessoaDao();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("------ Gerenciamento de Pessoas ------");
            System.out.println("1 - Cadastro");
            System.out.println("2 - Deletar");
            System.out.println("3 - Buscar");
            System.out.println("4 - Listar");
            System.out.println("5 - Sair");
            System.out.printf("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.printf("Digite o nome: ");
                String nome = scanner.nextLine();
                System.out.printf("Digite o email: ");
                String email = scanner.nextLine();

                Pessoa pessoa = new Pessoa(nome, email);
                if (pessoaDao.salvar(pessoa)) {
                    System.out.println("Salvo com sucesso!");
                }
                else {
                    System.out.println("Falha ao salvar");
                }
            }
            else if (opcao == 2) {
                System.out.printf("Digite o email: ");
                String email = scanner.nextLine();
                Pessoa pessoa = pessoaDao.buscarPorEmail(email);

                if (pessoa != null) {
                    if (pessoaDao.deletar(pessoa)) {
                        System.out.println("Deletado com sucesso!");
                    }
                    else {
                        System.out.println("Falha ao deletar");
                    }
                }
                else {
                    System.out.println("Pessoa " + email + " não encontrada.");
                }
            }
            else if (opcao == 3) {
                System.out.printf("Digite o email: ");
                String email = scanner.nextLine();
                Pessoa pessoa = pessoaDao.buscarPorEmail(email);
                if (pessoa != null) {
                    System.out.println("Pessoa encontrada: " + pessoa);
                }
                else {
                    System.out.println("Pessoa não encontrada.");
                }
            }
            else if (opcao == 4) {
                Set<Pessoa> pessoas = pessoaDao.getPessoas();
                if (pessoas.isEmpty()) {
                    System.out.println("Nenhuma pessoa salva no arquivo.");
                }
                else {
                    System.out.println("Pessoas salvas no arquivo: ");
                    for (Pessoa p : pessoas) {
                        System.out.println(p);
                    }
                }
            }
            else if (opcao == 5) {
                System.out.println("\nEncerrado");
                break;
            }
            else {
                System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }
}
