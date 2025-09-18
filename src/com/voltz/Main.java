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
}