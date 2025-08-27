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

        while (true) {
            System.out.print("\nDigite o NOME da pessoa (ou 'sair' para encerrar): ");
            String nomeBusca = sc.nextLine();

            if (nomeBusca.equalsIgnoreCase("sair")) break;

            Pessoa encontrada = null;

            for (Pessoa p : cache) {
                if (p.getNome().equalsIgnoreCase(nomeBusca)) {
                    encontrada = p;
                    System.out.println("Pessoa encontrada no cache: " + encontrada);
                    break;
                }
            }

            if (encontrada == null) {
                for (Pessoa p : banco) {
                    if (p.getNome().equalsIgnoreCase(nomeBusca)) {
                        encontrada = p;
                        System.out.println("Pessoa buscada no banco e adicionada ao cache: " + encontrada);

                        if (cache.size() == 10) {
                            cache.remove(0);
                        }
                        cache.add(encontrada);
                        break;
                    }
                }
            }

            if (encontrada == null) {
                System.out.println("Pessoa com nome '" + nomeBusca + "' não encontrada.");
            }
        }

        sc.close();
    }
}