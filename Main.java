import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Pessoa> banco = new ArrayList<>();
        List<Pessoa> cache = new ArrayList<>();

        banco.add(new Pessoa(1, "Larissa", 17));
        banco.add(new Pessoa(2, "Gabriel", 24));
        banco.add(new Pessoa(3, "Claudio", 19));
        banco.add(new Pessoa(4, "Melissa", 19));
        banco.add(new Pessoa(5, "Nycollas", 17));

        Scanner sc = new Scanner(System.in);
        int nextId = 6; // próximo ID disponível

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Buscar pessoa");
            System.out.println("2 - Adicionar nova pessoa");
            System.out.println("3 - Listar banco de dados");
            System.out.println("4 - Listar cache");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = sc.nextLine();

            if (opcao.equals("0")) {
                System.out.println("Encerrando o programa...");
                break;
            }

            switch (opcao) {
                case "1":
                    System.out.print("Digite o NOME da pessoa para buscar: ");
                    String nomeBusca = sc.nextLine();

                    Pessoa encontrada = null;

                    // 🔍 PASSO 1: Buscar no cache
                    for (Pessoa p : cache) {
                        if (p.getNome().equalsIgnoreCase(nomeBusca)) {
                            encontrada = p;
                            System.out.println("Pessoa encontrada no cache: " + encontrada);
                            break;
                        }
                    }

                    // 🔍 PASSO 2: Se não achou no cache, buscar no banco
                    if (encontrada == null) {
                        for (Pessoa p : banco) {
                            if (p.getNome().equalsIgnoreCase(nomeBusca)) {
                                encontrada = p;

                                // ⚡ Regras do cache
                                if (cache.size() == 10) {
                                    System.out.println("⚠ Cache cheio! Removendo a pessoa mais antiga: " + cache.get(0));
                                    cache.remove(0); // remove a mais antiga
                                }

                                cache.add(encontrada);
                                System.out.println("Pessoa buscada no banco e adicionada ao cache: " + encontrada);
                                break;
                            }
                        }
                    }

                    // 🔍 PASSO 3: Se não achou em lugar nenhum
                    if (encontrada == null) {
                        System.out.println("Pessoa com nome '" + nomeBusca + "' não encontrada.");
                    }
                    break;

                case "2":
                    System.out.print("Digite o nome da nova pessoa: ");
                    String novoNome = sc.nextLine();
                    System.out.print("Digite a idade da nova pessoa: ");
                    int novaIdade = Integer.parseInt(sc.nextLine());

                    Pessoa novaPessoa = new Pessoa(nextId++, novoNome, novaIdade);
                    banco.add(novaPessoa);
                    System.out.println("Pessoa adicionada com sucesso: " + novaPessoa);
                    break;

                case "3":
                    System.out.println("\n--- Banco de Dados ---");
                    for (Pessoa p : banco) {
                        System.out.println(p);
                    }
                    break;

                case "4":
                    System.out.println("\n--- Cache (máx 10 pessoas) ---");
                    if (cache.isEmpty()) {
                        System.out.println("Cache vazio.");
                    } else {
                        for (Pessoa p : cache) {
                            System.out.println(p);
                        }
                    }
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}