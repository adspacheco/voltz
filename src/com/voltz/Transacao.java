package com.voltz;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
    private static List<Transacao> transacoes = new ArrayList<>();
    private static int contadorId = 1;

    private int id;
    private String tipo;
    private String simboloCripto;
    private double quantidade;
    private double valor;
    private LocalDateTime dataHora;
    private String status;
    private int contaId;

    public Transacao(String tipo, String simboloCripto, double quantidade, double valor, int contaId) {
        this.id = contadorId++;
        this.tipo = tipo;
        this.simboloCripto = simboloCripto;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
        this.status = "PENDENTE";
        this.contaId = contaId;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getSimboloCripto() {
        return simboloCripto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getStatus() {
        return status;
    }

    public int getContaId() {
        return contaId;
    }

    public static Transacao comprar(String simboloCripto, double quantidade, double cotacao, int contaId) {
        System.out.println("\n=== COMPRAR CRIPTOATIVO ===");
        
        if (quantidade <= 0) {
            System.out.println("[ERRO] Quantidade deve ser maior que zero.");
            return null;
        }
        
        double valorTotal = quantidade * cotacao;
        Transacao transacao = new Transacao("COMPRA", simboloCripto, quantidade, valorTotal, contaId);
        transacao.status = "CONCLUIDA";
        transacoes.add(transacao);
        
        System.out.println("[SUCESSO] Compra realizada!");
        System.out.println("Quantidade: " + quantidade + " " + simboloCripto);
        System.out.println("Valor total: R$ " + String.format("%.2f", valorTotal));
        System.out.println("ID da transação: " + transacao.getId());
        
        return transacao;
    }

    public static Transacao vender(String simboloCripto, double quantidade, double cotacao, int contaId) {
        System.out.println("\n=== VENDER CRIPTOATIVO ===");
        
        if (quantidade <= 0) {
            System.out.println("[ERRO] Quantidade deve ser maior que zero.");
            return null;
        }
        
        double valorTotal = quantidade * cotacao;
        Transacao transacao = new Transacao("VENDA", simboloCripto, quantidade, valorTotal, contaId);
        transacao.status = "CONCLUIDA";
        transacoes.add(transacao);
        
        System.out.println("[SUCESSO] Venda realizada!");
        System.out.println("Quantidade: " + quantidade + " " + simboloCripto);
        System.out.println("Valor total: R$ " + String.format("%.2f", valorTotal));
        System.out.println("ID da transação: " + transacao.getId());
        
        return transacao;
    }

    public static Transacao consultar(int id) {
        System.out.println("\n=== CONSULTAR TRANSAÇÃO ===");
        for (Transacao transacao : transacoes) {
            if (transacao.getId() == id) {
                System.out.println("ID: " + transacao.getId());
                System.out.println("Tipo: " + transacao.getTipo());
                System.out.println("Criptoativo: " + transacao.getSimboloCripto());
                System.out.println("Quantidade: " + transacao.getQuantidade());
                System.out.println("Valor: R$ " + String.format("%.2f", transacao.getValor()));
                System.out.println("Data/Hora: " + transacao.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                System.out.println("Status: " + transacao.getStatus());
                return transacao;
            }
        }
        System.out.println("[ERRO] Transação com ID " + id + " não encontrada.");
        return null;
    }

    public static void listarHistorico() {
        System.out.println("\n=== HISTÓRICO DE TRANSAÇÕES ===");
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação encontrada.");
            return;
        }
        
        for (Transacao transacao : transacoes) {
            System.out.println("ID: " + transacao.getId() + 
                             " | " + transacao.getTipo() + 
                             " | " + transacao.getSimboloCripto() + 
                             " | Qtd: " + transacao.getQuantidade() +
                             " | Valor: R$ " + String.format("%.2f", transacao.getValor()) +
                             " | " + transacao.getStatus());
        }
    }

    public static void listarHistoricoPorConta(int contaId) {
        System.out.println("\n=== HISTÓRICO DE TRANSAÇÕES - CONTA " + contaId + " ===");
        boolean encontrou = false;
        
        for (Transacao transacao : transacoes) {
            if (transacao.getContaId() == contaId) {
                System.out.println("ID: " + transacao.getId() + 
                                 " | " + transacao.getTipo() + 
                                 " | " + transacao.getSimboloCripto() + 
                                 " | Qtd: " + transacao.getQuantidade() +
                                 " | Valor: R$ " + String.format("%.2f", transacao.getValor()) +
                                 " | " + transacao.getStatus());
                encontrou = true;
            }
        }
        
        if (!encontrou) {
            System.out.println("Nenhuma transação encontrada para esta conta.");
        }
    }

    public boolean cancelar() {
        System.out.println("\n=== CANCELAR TRANSAÇÃO ===");
        if ("PENDENTE".equals(this.status)) {
            this.status = "CANCELADA";
            System.out.println("[SUCESSO] Transação " + this.id + " cancelada.");
            return true;
        }
        System.out.println("[ERRO] Não é possível cancelar uma transação " + this.status.toLowerCase() + ".");
        return false;
    }

    public static List<Transacao> getTransacoes() {
        return new ArrayList<>(transacoes);
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", simboloCripto='" + simboloCripto + '\'' +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                ", dataHora=" + dataHora +
                ", status='" + status + '\'' +
                ", contaId=" + contaId +
                '}';
    }
}
