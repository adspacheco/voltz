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
                    menuUsuarios(scanner);
                    break;

                case 2:
                    menuAutenticacao(scanner);
                    break;

                case 3:
                    out.println("\n>>> Módulo de Criptoativos <<<");
                    break;

                case 4:
                    out.println("\n>>> Módulo de Transações <<<");
                    break;

                case 5:
                    menuConta(scanner);
                    break;

                case 6:
                    menuCarteira(scanner);
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

    private static void menuUsuarios(Scanner scanner) {
        int opcao = 0;
        
        while (opcao != 6) {
            out.println("\n========== MÓDULO USUÁRIOS ==========");
            out.println("1. Listar usuários");
            out.println("2. Ver perfil (precisa estar logado)");
            out.println("3. Atualizar dados (precisa estar logado)");
            out.println("4. Alterar senha (precisa estar logado)");
            out.println("5. Desativar conta (precisa estar logado)");
            out.println("6. Voltar ao menu principal");
            out.println("=====================================");
            out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    listarUsuarios();
                    break;
                    
                case 2:
                    verPerfil();
                    break;
                    
                case 3:
                    Autenticacao.atualizarUsuario(scanner);
                    break;
                    
                case 4:
                    Autenticacao.alterarSenha(scanner);
                    break;
                    
                case 5:
                    Autenticacao.deletarUsuario();
                    break;
                    
                case 6:
                    out.println("Voltando ao menu principal...");
                    break;
                    
                default:
                    out.println("[ERRO] Opção inválida!");
            }
        }
    }

    private static void menuAutenticacao(Scanner scanner) {
        int opcao = 0;
        
        while (opcao != 4) {
            out.println("\n======= MÓDULO AUTENTICAÇÃO =======");
            out.println("1. Cadastrar usuário");
            out.println("2. Fazer login");
            out.println("3. Fazer logout");
            out.println("4. Voltar ao menu principal");
            out.println("===================================");
            out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    Autenticacao.registrarUsuario(scanner);
                    break;
                    
                case 2:
                    Autenticacao.fazerLogin(scanner);
                    break;
                    
                case 3:
                    Autenticacao.fazerLogout();
                    break;
                    
                case 4:
                    out.println("Voltando ao menu principal...");
                    break;
                    
                default:
                    out.println("[ERRO] Opção inválida!");
            }
        }
    }

    private static void listarUsuarios() {
        out.println("\n=== LISTA DE USUÁRIOS ===");
        var usuarios = Autenticacao.listarUsuarios();
        
        if (usuarios.isEmpty()) {
            out.println("Nenhum usuário cadastrado.");
        } else {
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario usuario = usuarios.get(i);
                out.println((i + 1) + ". " + usuario.getNome() + " (" + usuario.getEmail() + ")");
            }
        }
    }

    private static void verPerfil() {
        if (Autenticacao.estaLogado()) {
            Usuario usuario = Autenticacao.getUsuarioLogado();
            out.println("\n=== MEU PERFIL ===");
            out.println("Nome: " + usuario.getNome());
            out.println("Email: " + usuario.getEmail());
            out.println("CPF: " + usuario.getCpf());
            out.println("Status: " + (usuario.isAtivo() ? "Ativo" : "Inativo"));
        } else {
            out.println("[ERRO] Você precisa estar logado para ver o perfil!");
        }
    }

    private static void menuConta(Scanner scanner) {
        int opcao = 0;

        while (opcao != 5) {
            out.println("\n======= MÓDULO CONTA =======");
            out.println("1. Criar conta");
            out.println("2. Listar contas");
            out.println("3. Depositar");
            out.println("4. Sacar");
            out.println("5. Voltar ao menu principal");
            out.println("===================================");
            out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> Conta.criarConta(scanner);
                case 2 -> listarContas();
                case 3 -> depositarConta(scanner);
                case 4 -> sacarConta(scanner);
                case 5 -> out.println("Voltando ao menu principal...");
                default -> out.println("[ERRO] Opção inválida!");
            }
        }
    }

    private static void listarContas() {
        out.println("\n=== LISTA DE CONTAS ===");
        var contas = Conta.listarContas();

        if (contas.isEmpty()) {
            out.println("Nenhuma conta cadastrada.");
        } else {
            for (Conta conta : contas) {
                out.println("ID: " + conta.getId() +
                        " | Número: " + conta.getNumeroConta() +
                        " | Saldo: R$ " + conta.getSaldo() +
                        " | Status: " + (conta.isAtiva() ? "Ativa" : "Inativa"));
            }
        }
    }

    private static void depositarConta(Scanner scanner) {
        out.print("Número da conta: ");
        String numero = scanner.nextLine();

        Conta conta = Conta.buscarPorNumero(numero);
        if (conta == null) {
            out.println("[ERRO] Conta não encontrada!");
            return;
        }

        out.print("Valor do depósito: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        conta.depositar(valor);
        out.println("[SUCESSO] Depósito realizado. Novo saldo: R$ " + conta.getSaldo());
    }

    private static void sacarConta(Scanner scanner) {
        out.print("Número da conta: ");
        String numero = scanner.nextLine();

        Conta conta = Conta.buscarPorNumero(numero);
        if (conta == null) {
            out.println("[ERRO] Conta não encontrada!");
            return;
        }

        out.print("Valor do saque: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        if (conta.sacar(valor)) {
            out.println("[SUCESSO] Saque realizado. Novo saldo: R$ " + conta.getSaldo());
        } else {
            out.println("[ERRO] Saldo insuficiente!");
        }
    }

    private static void menuCarteira(Scanner scanner) {
        int opcao = 0;

        while (opcao != 5) {
            out.println("\n======= MÓDULO CARTEIRA =======");
            out.println("1. Criar carteira");
            out.println("2. Listar carteiras");
            out.println("3. Adicionar saldo");
            out.println("4. Remover saldo");
            out.println("5. Voltar ao menu principal");
            out.println("===================================");
            out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> Carteira.criarCarteira(scanner);
                case 2 -> listarCarteiras();
                case 3 -> adicionarSaldoCarteira(scanner);
                case 4 -> removerSaldoCarteira(scanner);
                case 5 -> out.println("Voltando ao menu principal...");
                default -> out.println("[ERRO] Opção inválida!");
            }
        }
    }

    private static void listarCarteiras() {
        out.println("\n=== LISTA DE CARTEIRAS ===");
        var carteiras = Carteira.listarCarteiras();

        if (carteiras.isEmpty()) {
            out.println("Nenhuma carteira cadastrada.");
        } else {
            for (Carteira carteira : carteiras) {
                out.println("ID: " + carteira.getId() +
                        " | Dono: " + carteira.getDono() +
                        " | Saldo: R$ " + carteira.getSaldo());
            }
        }
    }

    private static void adicionarSaldoCarteira(Scanner scanner) {
        out.print("Nome do dono: ");
        String dono = scanner.nextLine();

        Carteira carteira = Carteira.buscarPorDono(dono);
        if (carteira == null) {
            out.println("[ERRO] Carteira não encontrada!");
            return;
        }

        out.print("Valor a adicionar: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        carteira.adicionarSaldo(valor);
        out.println("[SUCESSO] Saldo atualizado. Novo saldo: R$ " + carteira.getSaldo());
    }

    private static void removerSaldoCarteira(Scanner scanner) {
        out.print("Nome do dono: ");
        String dono = scanner.nextLine();

        Carteira carteira = Carteira.buscarPorDono(dono);
        if (carteira == null) {
            out.println("[ERRO] Carteira não encontrada!");
            return;
        }

        out.print("Valor a remover: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        if (carteira.removerSaldo(valor)) {
            out.println("[SUCESSO] Saldo removido. Novo saldo: R$ " + carteira.getSaldo());
        } else {
            out.println("[ERRO] Saldo insuficiente!");
        }
    }
}