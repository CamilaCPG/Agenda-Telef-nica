import java.io.*;
import java.util.*;

public class AgendaTelefonica {
    private static final String NOME_FILE = "agenda.txt";
    private final Scanner scanner;

    public AgendaTelefonica(Scanner scanner) {
        this.scanner = scanner;
    }

    private void cadastrarContato() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_FILE, true))) {
            System.out.println("\nDigite o nome: ");
            String nome = scanner.nextLine();

            System.out.println("\nDigite o telefone: ");
            String telefone = scanner.nextLine();

            writer.write(nome + "\n");
            writer.write(telefone + "\n");

            System.out.println("\nContato cadastrado com sucesso!");
        } catch (IOException e) {
            System.out.println("\nErro ao cadastrar contato. Tente novamente!");
        }
    }

    private void consultarContato() {
        System.out.println("\nDigite o nome para consultar: ");
        String nomeBusca = scanner.nextLine();
        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_FILE))) {
            String nome;

            while ((nome = reader.readLine()) != null) {
                String telefone = reader.readLine();

                if (nome.equalsIgnoreCase(nomeBusca)) {
                    System.out.println("\nNome: " + nome);
                    System.out.println("\nTelefone: " + telefone);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("\nContato não encontrado.");
            }
        } catch (IOException e) {
            System.out.println("\nErro ao consultar contato. Tente novamente!");
        }
    }

    private void alterarContato() {
        System.out.println("\nDigite o nome do contato a ser alterado: ");
        String nomeBusca = scanner.nextLine();
        List<String> contatos = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_FILE))) {
            String nome;

            while ((nome = reader.readLine()) != null) {
                String telefone = reader.readLine();

                if (nome.equalsIgnoreCase(nomeBusca)) {
                    System.out.println("\nNovo nome: ");
                    nome = scanner.nextLine();

                    System.out.println("\nNovo telefone: ");
                    telefone = scanner.nextLine();

                    encontrado = true;
                }
                contatos.add(nome);
                contatos.add(telefone);
            }
        } catch (IOException e) {
            System.out.println("\nErro ao alterar contato. Tente novamente!");
        }

        if (encontrado) {
            try (FileWriter writer = new FileWriter(NOME_FILE, false)) {
                for (String linha : contatos) {
                    writer.write(linha + "\n");
                }
                System.out.println("\nContato alterado com sucesso!");
            } catch (IOException e) {
                System.out.println("\nErro ao salvar alterações. Tente novamente!");
            }
        } else {
            System.out.println("\nContato não encontrado.");
        }
    }

    private void excluirContato() {
        System.out.println("\nDigite o nome do contato a ser excluído: ");
        String nomeBusca = scanner.nextLine();
        List<String> contatos = new ArrayList<>();

        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_FILE))) {
            String nome;

            while ((nome = reader.readLine()) != null) {
                String telefone = reader.readLine();

                if (!nome.equalsIgnoreCase(nomeBusca)) {
                    contatos.add(nome);
                    contatos.add(telefone);
                } else {
                    encontrado = true;
                }
            }
        } catch (IOException e) {
            System.out.println("\nErro ao excluir contato. Tente novamente!");
        }

        if (encontrado) {
            try (FileWriter writer = new FileWriter(NOME_FILE, false)) {
                for (String linha : contatos) {
                    writer.write(linha + "\n");
                }
                System.out.println("\nContato excluído com sucesso!");
            } catch (IOException e) {
                System.out.println("\nErro ao fazer a exclusão. Tente novamente!");
            }
        } else {
            System.out.println("\nContato não encontrado.");
        }
    }

    public void Menu() {
        try (scanner) {
            int opcao;

            do {
                System.out.println("\nBem-vindo(a)! Escolha uma opção:");
                System.out.println(
                        "1. Cadastrar contato\n2. Consultar contato\n3. Substituir contato\n4. Excluir contato\n5. Sair");
                System.out.println("\nSua opção: ");
                opcao = Integer.parseInt(scanner.nextLine().trim());

                switch (opcao) {
                    case 1:
                        cadastrarContato();
                        break;

                    case 2:
                        consultarContato();
                        break;

                    case 3:
                        alterarContato();
                        break;

                    case 4:
                        excluirContato();
                        break;

                    case 5:
                        System.out.println("\nVolte sempre!");
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente!");
                        break;
                }
            } while (opcao != 5);
        } catch (InputMismatchException e) {
            System.out.println("\nHouve algum erro. Tente novamente!\n");
        }
    }
}