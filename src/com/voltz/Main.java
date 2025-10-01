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
                    menuCriptoativo(scanner);
                    break;

                case 4:
                    menuTransacao(scanner);
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

        while (opcao != 7) {
            out.println("\n======= MÓDULO CONTA =======");
            out.println("1. Criar conta");
            out.println("2. Consultar conta");
            out.println("3. Listar contas");
            out.println("4. Depositar");
            out.println("5. Sacar");
            out.println("6. Encerrar conta");
            out.println("7. Voltar ao menu principal");
            out.println("===================================");
            out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> Conta.criar();
                case 2 -> consultarConta(scanner);
                case 3 -> listarContas();
                case 4 -> depositarConta(scanner);
                case 5 -> sacarConta(scanner);
                case 6 -> encerrarConta(scanner);
                case 7 -> out.println("Voltando ao menu principal...");
                default -> out.println("[ERRO] Opção inválida!");
            }

        }
    }

    private static void consultarConta(Scanner scanner) {
        out.print("Número da conta: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        Conta conta = Conta.buscarPorNumero(numero);
        if (conta != null) {
            conta.consultar();
        } else {
            out.println("[ERRO] Conta não encontrada!");
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
                        " | Número: " + conta.getNumero() +
                        " | Tipo: " + conta.getTipo() +
                        " | Saldo: R$ " + String.format("%.2f", conta.getSaldo()) +
                        " | Status: " + conta.getStatus());
            }
        }
    }

    private static void depositarConta(Scanner scanner) {
        out.print("Número da conta: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        Conta conta = Conta.buscarPorNumero(numero);
        if (conta == null) {
            out.println("[ERRO] Conta não encontrada!");
            return;
        }

        out.print("Valor do depósito: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        conta.depositar(valor);
    }

    private static void sacarConta(Scanner scanner) {
        out.print("Número da conta: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        Conta conta = Conta.buscarPorNumero(numero);
        if (conta == null) {
            out.println("[ERRO] Conta não encontrada!");
            return;
        }

        out.print("Valor do saque: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        conta.sacar(valor);
    }

    private static void encerrarConta(Scanner scanner) {
        out.print("Número da conta: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        Conta conta = Conta.buscarPorNumero(numero);
        if (conta != null) {
            conta.encerrar();
        } else {
            out.println("[ERRO] Conta não encontrada!");
        }
    }

    private static void menuCarteira(Scanner scanner) {
        int opcao = 0;

        while (opcao != 7) {
            out.println("\n======= MÓDULO CARTEIRA =======");
            out.println("1. Criar carteira");
            out.println("2. Consultar carteira");
            out.println("3. Listar carteiras");
            out.println("4. Adicionar cripto");
            out.println("5. Remover cripto");
            out.println("6. Obter saldo total");
            out.println("7. Voltar ao menu principal");
            out.println("===================================");
            out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> Carteira.criar();
                case 2 -> consultarCarteira(scanner);
                case 3 -> listarCarteiras();
                case 4 -> adicionarCriptoCarteira(scanner);
                case 5 -> removerCriptoCarteira(scanner);
                case 6 -> obterSaldoCarteira(scanner);
                case 7 -> out.println("Voltando ao menu principal...");
                default -> out.println("[ERRO] Opção inválida!");
            }
        }
    }

    private static void consultarCarteira(Scanner scanner) {
        out.print("Endereço da carteira: ");
        String endereco = scanner.nextLine();

        Carteira carteira = Carteira.buscarPorEndereco(endereco);
        if (carteira != null) {
            carteira.consultar();
        } else {
            out.println("[ERRO] Carteira não encontrada!");
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
                        " | Endereço: " + carteira.getEndereco() +
                        " | Tipo: " + carteira.getTipo() +
                        " | Ativa: " + (carteira.isAtiva() ? "Sim" : "Não"));
            }
        }
    }

    private static void adicionarCriptoCarteira(Scanner scanner) {
        out.print("Endereço da carteira: ");
        String endereco = scanner.nextLine();

        Carteira carteira = Carteira.buscarPorEndereco(endereco);
        if (carteira == null) {
            out.println("[ERRO] Carteira não encontrada!");
            return;
        }

        out.print("Símbolo do criptoativo (ex: BTC, ETH): ");
        String simbolo = scanner.nextLine();
        
        out.print("Quantidade: ");
        double quantidade = scanner.nextDouble();
        scanner.nextLine();

        carteira.adicionarCripto(simbolo, quantidade);
    }

    private static void removerCriptoCarteira(Scanner scanner) {
        out.print("Endereço da carteira: ");
        String endereco = scanner.nextLine();

        Carteira carteira = Carteira.buscarPorEndereco(endereco);
        if (carteira == null) {
            out.println("[ERRO] Carteira não encontrada!");
            return;
        }

        out.print("Símbolo do criptoativo: ");
        String simbolo = scanner.nextLine();
        
        out.print("Quantidade a remover: ");
        double quantidade = scanner.nextDouble();
        scanner.nextLine();

        carteira.removerCripto(simbolo, quantidade);
    }

    private static void obterSaldoCarteira(Scanner scanner) {
        out.print("Endereço da carteira: ");
        String endereco = scanner.nextLine();

        Carteira carteira = Carteira.buscarPorEndereco(endereco);
        if (carteira != null) {
            carteira.obterSaldo();
        } else {
            out.println("[ERRO] Carteira não encontrada!");
        }
    }

    private static void menuCriptoativo(Scanner scanner) {
        int opcao = 0;

        while (opcao != 6) {
            out.println("\n====== MÓDULO CRIPTOATIVOS ======");
            out.println("1. Cadastrar criptoativo");
            out.println("2. Buscar criptoativo");
            out.println("3. Listar todos os criptoativos");
            out.println("4. Obter cotação");
            out.println("5. Desativar criptoativo");
            out.println("6. Voltar ao menu principal");
            out.println("==================================");
            out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> Criptoativo.cadastrar();
                case 2 -> buscarCriptoativo(scanner);
                case 3 -> Criptoativo.listarTodos();
                case 4 -> obterCotacaoCriptoativo(scanner);
                case 5 -> desativarCriptoativo(scanner);
                case 6 -> out.println("Voltando ao menu principal...");
                default -> out.println("[ERRO] Opção inválida!");
            }
        }
    }

    private static void buscarCriptoativo(Scanner scanner) {
        out.print("Símbolo do criptoativo (ex: BTC, ETH): ");
        String simbolo = scanner.nextLine();
        Criptoativo.buscar(simbolo);
    }

    private static void obterCotacaoCriptoativo(Scanner scanner) {
        out.print("Símbolo do criptoativo: ");
        String simbolo = scanner.nextLine();
        
        Criptoativo cripto = Criptoativo.buscar(simbolo);
        if (cripto != null) {
            cripto.obterCotacao();
        }
    }

    private static void desativarCriptoativo(Scanner scanner) {
        out.print("Símbolo do criptoativo: ");
        String simbolo = scanner.nextLine();
        
        Criptoativo cripto = Criptoativo.buscar(simbolo);
        if (cripto != null) {
            cripto.desativar();
        }
    }

    private static void menuTransacao(Scanner scanner) {
        int opcao = 0;

        while (opcao != 6) {
            out.println("\n====== MÓDULO TRANSAÇÕES ======");
            out.println("1. Comprar criptoativo");
            out.println("2. Vender criptoativo");
            out.println("3. Consultar transação");
            out.println("4. Listar histórico");
            out.println("5. Listar histórico por conta");
            out.println("6. Voltar ao menu principal");
            out.println("================================");
            out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> comprarCriptoativo(scanner);
                case 2 -> venderCriptoativo(scanner);
                case 3 -> consultarTransacao(scanner);
                case 4 -> Transacao.listarHistorico();
                case 5 -> listarHistoricoPorConta(scanner);
                case 6 -> out.println("Voltando ao menu principal...");
                default -> out.println("[ERRO] Opção inválida!");
            }
        }
    }

    private static void comprarCriptoativo(Scanner scanner) {
        out.print("Símbolo do criptoativo: ");
        String simbolo = scanner.nextLine();
        
        out.print("Quantidade: ");
        double quantidade = scanner.nextDouble();
        scanner.nextLine();
        
        out.print("Cotação: ");
        double cotacao = scanner.nextDouble();
        scanner.nextLine();
        
        out.print("ID da conta: ");
        int contaId = scanner.nextInt();
        scanner.nextLine();

        Transacao.comprar(simbolo, quantidade, cotacao, contaId);
    }

    private static void venderCriptoativo(Scanner scanner) {
        out.print("Símbolo do criptoativo: ");
        String simbolo = scanner.nextLine();
        
        out.print("Quantidade: ");
        double quantidade = scanner.nextDouble();
        scanner.nextLine();
        
        out.print("Cotação: ");
        double cotacao = scanner.nextDouble();
        scanner.nextLine();
        
        out.print("ID da conta: ");
        int contaId = scanner.nextInt();
        scanner.nextLine();

        Transacao.vender(simbolo, quantidade, cotacao, contaId);
    }

    private static void consultarTransacao(Scanner scanner) {
        out.print("ID da transação: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Transacao.consultar(id);
    }

    private static void listarHistoricoPorConta(Scanner scanner) {
        out.print("ID da conta: ");
        int contaId = scanner.nextInt();
        scanner.nextLine();

        Transacao.listarHistoricoPorConta(contaId);
    }
}