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
        out.println("Equipe: Anderson, Mateus, Pedro");
        out.println("------------------------------------------------\n");
        
        try {
            out.println("Inicializando sistema com exemplos de uso...");
            
            new Usuario("Admin", "admin@voltz.com", "12345678900", "senha123");
            new Usuario("Teste", "teste@voltz.com", "98765432100", "teste456");
            
            Conta.criar();
            Conta.criar();
            
            Criptoativo.cadastrar();
            
            Carteira.criar();
            Carteira.criar();
            
            TransacaoCompra compraExemplo = new TransacaoCompra("BTC", 0.1, 15000, 1);
            compraExemplo.processar();
            Transacao.getTransacoes().add(compraExemplo);
            
            TransacaoVenda vendaExemplo = new TransacaoVenda("ETH", 1, 8000, 2);
            vendaExemplo.calcularLucro(7500);
            vendaExemplo.processar();
            Transacao.getTransacoes().add(vendaExemplo);
            
            new Alerta("BTC", 140000, "menor");
            
            new Relatorio();
            
            out.println("Sistema inicializado com sucesso!\n");
            out.println("Exemplos de classes com herança e polimorfismo:");
            out.println("- TransacaoCompra e TransacaoVenda herdam de Transacao");
            out.println("- Métodos processar() e calcularTaxa() sobrescritos (override)");
            out.println("- Método calcularLucro() sobrecarregado em TransacaoVenda (overload)\n");
            
        } catch (Exception e) {
            out.println("[AVISO] Erro ao inicializar: " + e.getMessage());
            out.println("Continuando...\n");
        }

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
            scanner.nextLine();

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
                    menuAlerta(scanner);
                    break;

                case 8:
                    menuRelatorio(scanner);
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

        while (opcao != 8) {
            out.println("\n====== MÓDULO TRANSAÇÕES ======");
            out.println("1. Comprar criptoativo (com desconto)");
            out.println("2. Vender criptoativo (com cálculo de lucro)");
            out.println("3. Consultar transação");
            out.println("4. Listar histórico");
            out.println("5. Listar histórico por conta");
            out.println("6. Demonstrar polimorfismo");
            out.println("7. Cancelar transação pendente");
            out.println("8. Voltar ao menu principal");
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
                case 6 -> demonstrarPolimorfismo();
                case 7 -> cancelarTransacao(scanner);
                case 8 -> out.println("Voltando ao menu principal...");
                default -> out.println("[ERRO] Opção inválida!");
            }
        }
    }

    private static void comprarCriptoativo(Scanner scanner) {
        try {
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
            
            out.print("Usar desconto? (s/n): ");
            String usarDesconto = scanner.nextLine();
            
            double valorTotal = quantidade * cotacao;
            TransacaoCompra compra = new TransacaoCompra(simbolo, quantidade, valorTotal, contaId);
            
            if ("s".equalsIgnoreCase(usarDesconto)) {
                out.print("Percentual de desconto (0-1): ");
                double desconto = scanner.nextDouble();
                scanner.nextLine();
                compra.aplicarDesconto(desconto);
            }
            
            compra.processar();
            
            out.println("[SUCESSO] Compra realizada!");
            out.println("Taxa de compra: " + (compra.getTaxaCompra() * 100) + "%");
            out.println("Taxa total: R$ " + String.format("%.2f", compra.calcularTaxa()));
            out.println("Valor final: R$ " + String.format("%.2f", compra.getValor()));
            out.println("ID da transação: " + compra.getId());
            
            Transacao.getTransacoes().add(compra);
            
        } catch (Exception e) {
            out.println("[ERRO] Erro ao processar compra: " + e.getMessage());
        }
    }

    private static void venderCriptoativo(Scanner scanner) {
        try {
            out.print("Símbolo do criptoativo: ");
            String simbolo = scanner.nextLine();
            
            out.print("Quantidade: ");
            double quantidade = scanner.nextDouble();
            scanner.nextLine();
            
            out.print("Cotação atual: ");
            double cotacao = scanner.nextDouble();
            scanner.nextLine();
            
            out.print("ID da conta: ");
            int contaId = scanner.nextInt();
            scanner.nextLine();
            
            double valorTotal = quantidade * cotacao;
            TransacaoVenda venda = new TransacaoVenda(simbolo, quantidade, valorTotal, contaId);
            
            out.print("Informar preço de compra original? (s/n): ");
            String informarPrecoCompra = scanner.nextLine();
            
            if ("s".equalsIgnoreCase(informarPrecoCompra)) {
                out.print("Preço de compra original: ");
                double precoCompra = scanner.nextDouble();
                scanner.nextLine();
                venda.calcularLucro(precoCompra, quantidade);
            }
            
            venda.processar();
            
            out.println("[SUCESSO] Venda realizada!");
            out.println("Taxa de venda: " + (venda.getTaxaVenda() * 100) + "%");
            out.println("Taxa total: R$ " + String.format("%.2f", venda.calcularTaxa()));
            if (venda.getLucro() != 0) {
                if (venda.getLucro() > 0) {
                    out.println("Lucro: R$ " + String.format("%.2f", venda.getLucro()));
                } else {
                    out.println("Prejuízo: R$ " + String.format("%.2f", Math.abs(venda.getLucro())));
                }
            }
            out.println("Valor final: R$ " + String.format("%.2f", venda.getValor()));
            out.println("ID da transação: " + venda.getId());
            
            Transacao.getTransacoes().add(venda);
            
        } catch (Exception e) {
            out.println("[ERRO] Erro ao processar venda: " + e.getMessage());
        }
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

    private static void menuAlerta(Scanner scanner) {
        int opcao = 0;
        Alerta alerta = new Alerta();

        while (opcao != 6) {
            out.println("\n======= MÓDULO ALERTAS =======");
            out.println("1. Criar alerta de preço");
            out.println("2. Verificar alertas");
            out.println("3. Marcar como lido");
            out.println("4. Listar todos os alertas");
            out.println("5. Deletar alerta");
            out.println("6. Voltar ao menu principal");
            out.println("===============================");
            out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> criarAlertaPreco(scanner, alerta);
                case 2 -> verificarAlertas(scanner, alerta);
                case 3 -> marcarAlertaComoLido(scanner, alerta);
                case 4 -> alerta.listar();
                case 5 -> deletarAlerta(scanner, alerta);
                case 6 -> out.println("Voltando ao menu principal...");
                default -> out.println("[ERRO] Opção inválida!");
            }
        }
    }

    private static void criarAlertaPreco(Scanner scanner, Alerta alerta) {
        out.print("Criptoativo (ex: BTC, ETH): ");
        String criptoativo = scanner.nextLine();
        
        out.print("Preço alvo: ");
        double precoAlvo = scanner.nextDouble();
        scanner.nextLine();
        
        out.print("Tipo de comparação (maior/menor): ");
        String tipoComparacao = scanner.nextLine();

        alerta.criarAlertaPreco(criptoativo, precoAlvo, tipoComparacao);
    }

    private static void verificarAlertas(Scanner scanner, Alerta alerta) {
        out.print("Criptoativo: ");
        String criptoativo = scanner.nextLine();
        
        out.print("Preço atual: ");
        double precoAtual = scanner.nextDouble();
        scanner.nextLine();

        alerta.verificarAlertas(criptoativo, precoAtual);
    }

    private static void marcarAlertaComoLido(Scanner scanner, Alerta alerta) {
        out.print("ID do alerta: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        alerta.marcarComoLido(id);
    }

    private static void deletarAlerta(Scanner scanner, Alerta alerta) {
        out.print("ID do alerta: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        alerta.deletar(id);
    }

    private static void menuRelatorio(Scanner scanner) {
        int opcao = 0;
        Relatorio relatorio = new Relatorio();

        while (opcao != 6) {
            out.println("\n====== MÓDULO RELATÓRIOS ======");
            out.println("1. Gerar relatório de saldo");
            out.println("2. Gerar relatório de transações");
            out.println("3. Gerar relatório de lucros");
            out.println("4. Visualizar relatório");
            out.println("5. Listar todos os relatórios");
            out.println("6. Voltar ao menu principal");
            out.println("================================");
            out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> relatorio.relatorioSaldo();
                case 2 -> relatorio.relatorioTransacoes();
                case 3 -> relatorio.relatorioLucros();
                case 4 -> visualizarRelatorio(scanner, relatorio);
                case 5 -> relatorio.listarTodos();
                case 6 -> out.println("Voltando ao menu principal...");
                default -> out.println("[ERRO] Opção inválida!");
            }
        }
    }

    private static void visualizarRelatorio(Scanner scanner, Relatorio relatorio) {
        out.print("ID do relatório: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        relatorio.visualizar(id);
    }
    
    private static void cancelarTransacao(Scanner scanner) {
        try {
            out.print("ID da transação para cancelar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            Transacao transacao = null;
            for (Transacao t : Transacao.getTransacoes()) {
                if (t.getId() == id) {
                    transacao = t;
                    break;
                }
            }
            
            if (transacao != null) {
                if (transacao.cancelar()) {
                    out.println("Transação cancelada com sucesso!");
                }
            } else {
                out.println("[ERRO] Transação não encontrada!");
            }
        } catch (Exception e) {
            out.println("[ERRO] Erro ao cancelar transação: " + e.getMessage());
        }
    }
    
    private static void demonstrarPolimorfismo() {
        out.println("\n=== DEMONSTRAÇÃO DE POLIMORFISMO ===");
        
        try {
            out.println("\n1. Criando transações com polimorfismo:");
            
            Transacao t1 = new TransacaoCompra("BTC", 0.5, 50000, 1);
            Transacao t2 = new TransacaoVenda("ETH", 2, 6000, 1);
            Transacao t3 = new Transacao("TRANSFERENCIA", "USDT", 100, 100, 2);
            
            out.println("\n2. Polimorfismo dinâmico (override) - método processar():");
            t1.processar();
            t2.processar();
            t3.processar();
            
            out.println("\n3. Polimorfismo dinâmico - método calcularTaxa():");
            out.println("Taxa TransacaoCompra: R$ " + String.format("%.2f", t1.calcularTaxa()));
            out.println("Taxa TransacaoVenda: R$ " + String.format("%.2f", t2.calcularTaxa()));
            out.println("Taxa Transacao base: R$ " + String.format("%.2f", t3.calcularTaxa()));
            
            out.println("\n4. Polimorfismo estático (overload) - métodos sobrecarregados:");
            out.println("calcularTaxa() sem parâmetro: R$ " + String.format("%.2f", t1.calcularTaxa()));
            out.println("calcularTaxa(0.05) com parâmetro: R$ " + String.format("%.2f", t1.calcularTaxa(0.05)));
            
            if (t2 instanceof TransacaoVenda venda) {
                out.println("\ncalcularLucro com 1 parâmetro:");
                venda.calcularLucro(5000);
                out.println("Lucro calculado: R$ " + String.format("%.2f", venda.getLucro()));
                
                out.println("\ncalcularLucro com 2 parâmetros:");
                venda.calcularLucro(2800, 2);
                out.println("Lucro recalculado: R$ " + String.format("%.2f", venda.getLucro()));
            }
            
            out.println("\n5. Usando toString() polimórfico:");
            out.println("TransacaoCompra: " + t1.toString());
            out.println("TransacaoVenda: " + t2.toString());
            out.println("Transacao base: " + t3.toString());
            
        } catch (Exception e) {
            out.println("[ERRO] Erro na demonstração: " + e.getMessage());
        }
    }
}