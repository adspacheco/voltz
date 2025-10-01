package com.voltz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Criptoativo {
    private static List<Criptoativo> criptoativos = new ArrayList<>();
    private static int contadorId = 1;

    private int id;
    private String nome;
    private String simbolo;
    private double cotacao;
    private String descricao;
    private boolean ativo;

    public Criptoativo(String nome, String simbolo, double cotacao, String descricao) {
        this.id = contadorId++;
        this.nome = nome;
        this.simbolo = simbolo;
        this.cotacao = cotacao;
        this.descricao = descricao;
        this.ativo = true;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public double getCotacao() {
        return cotacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setCotacao(double cotacao) {
        this.cotacao = cotacao;
    }

    public static Criptoativo cadastrar() {
        System.out.println("\n=== CADASTRAR CRIPTOATIVO ===");
        
        String[] nomes = {"Bitcoin", "Ethereum", "Cardano", "Polkadot", "Chainlink"};
        String[] simbolos = {"BTC", "ETH", "ADA", "DOT", "LINK"};
        String[] descricoes = {
            "Primeira criptomoeda descentralizada",
            "Plataforma de contratos inteligentes",
            "Blockchain sustentável e escalável",
            "Interoperabilidade entre blockchains",
            "Rede de oráculos descentralizada"
        };
        
        Random random = new Random();
        int index = random.nextInt(nomes.length);
        
        String nome = nomes[index];
        String simbolo = simbolos[index];
        String descricao = descricoes[index];
        double cotacao = 10000 + random.nextDouble() * 90000;
        
        Criptoativo cripto = new Criptoativo(nome, simbolo, cotacao, descricao);
        criptoativos.add(cripto);
        
        System.out.println("[SUCESSO] Criptoativo " + nome + " (" + simbolo + ") cadastrado com ID: " + cripto.getId());
        return cripto;
    }

    public static Criptoativo buscar(String simbolo) {
        System.out.println("\n=== BUSCAR CRIPTOATIVO ===");
        for (Criptoativo cripto : criptoativos) {
            if (cripto.getSimbolo().equalsIgnoreCase(simbolo) && cripto.isAtivo()) {
                System.out.println("[ENCONTRADO] " + cripto.getNome() + " (" + cripto.getSimbolo() + ")");
                return cripto;
            }
        }
        System.out.println("[ERRO] Criptoativo com símbolo " + simbolo + " não encontrado.");
        return null;
    }

    public void atualizar(String novoNome, String novaDescricao) {
        System.out.println("\n=== ATUALIZAR CRIPTOATIVO ===");
        if (novoNome != null && !novoNome.isEmpty()) {
            this.nome = novoNome;
        }
        if (novaDescricao != null && !novaDescricao.isEmpty()) {
            this.descricao = novaDescricao;
        }
        System.out.println("[SUCESSO] Criptoativo " + simbolo + " atualizado.");
    }

    public static void listarTodos() {
        System.out.println("\n=== LISTAR TODOS OS CRIPTOATIVOS ===");
        if (criptoativos.isEmpty()) {
            System.out.println("Nenhum criptoativo cadastrado.");
            return;
        }
        
        for (Criptoativo cripto : criptoativos) {
            if (cripto.isAtivo()) {
                System.out.println("ID: " + cripto.getId() + 
                                 " | " + cripto.getNome() + 
                                 " (" + cripto.getSimbolo() + ")" +
                                 " | R$ " + String.format("%.2f", cripto.getCotacao()));
            }
        }
    }

    public double obterCotacao() {
        System.out.println("\n=== OBTER COTAÇÃO ===");
        Random random = new Random();
        double variacao = (random.nextDouble() - 0.5) * 0.1;
        this.cotacao = this.cotacao * (1 + variacao);
        
        System.out.println("Cotação atual do " + nome + " (" + simbolo + "): R$ " + String.format("%.2f", cotacao));
        return cotacao;
    }

    public void desativar() {
        this.ativo = false;
        System.out.println("[SUCESSO] Criptoativo " + simbolo + " desativado.");
    }

    public static List<Criptoativo> getCriptoativos() {
        return new ArrayList<>(criptoativos);
    }

    @Override
    public String toString() {
        return "Criptoativo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", simbolo='" + simbolo + '\'' +
                ", cotacao=" + cotacao +
                ", descricao='" + descricao + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}
