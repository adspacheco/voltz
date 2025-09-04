package com.voltz;

import java.util.Scanner;

import static java.lang.System.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        int opcao = 0;

        out.println("================================================");
        out.println("                   SISTEMA VOLTZ                ");
        out.println("================================================");
        out.println("Equipe: Anderson, Mateus, Pedro, Rodrigo");
        out.println("------------------------------------------------\n");

        while (opcao != 9) {
            out.println("\n============== MENU PRINCIPAL ===============");
            out.println("1. Usuários");
            out.println("2. Autenticação");
            out.println("3. Criptoativos");
            out.println("4. Transações");
            out.println("5. Contas");
            out.println("6. Carteiras");
            out.println("7. Alertas");
            out.println("8. Relatórios");
            out.println("9. Sair");
            out.println("==============================================");
            out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    out.println("\n>>> Módulo de Usuários <<<");
                    break;

                case 2:
                    out.println("\n>>> Módulo de Autenticação <<<");
                    break;

                case 3:
                    out.println("\n>>> Módulo de Criptoativos <<<");
                    break;

                case 4:
                    out.println("\n>>> Módulo de Transações <<<");
                    break;

                case 5:
                    out.println("\n>>> Módulo de Contas <<<");
                    break;

                case 6:
                    out.println("\n>>> Módulo de Carteiras <<<");
                    break;

                case 7:
                    out.println("\n>>> Módulo de Alertas <<<");
                    break;

                case 8:
                    out.println("\n>>> Módulo de Relatórios <<<");
                    break;

                case 9:
                    out.println("\n==============================================");
                    out.println("Encerrando o sistema VOLTZ...");
                    out.println("Obrigado por usar nossa plataforma!");
                    out.println("==============================================");
                    break;

                default:
                    out.println("\n[ERRO] Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }
}