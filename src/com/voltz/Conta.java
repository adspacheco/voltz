package com.voltz;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Conta {
    private static List<Conta> contas = new ArrayList<>();
    private static int contadorId = 1;

    private int id;
    private int numero;
    private String tipo;
    private double saldo;
    private String dataAbertura;
    private String status;

    public Conta(int numero, String tipo, double saldoInicial) {
        this.id = contadorId++;
        this.numero = numero;
        this.tipo = tipo;
        this.saldo = saldoInicial;
        this.dataAbertura = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.status = "ATIVA";
    }

    public int getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public String getStatus() {
        return status;
    }

    public boolean isAtiva() {
        return "ATIVA".equals(status);
    }

    public void menuConta() {
        System.out.println("\n=== MENU CONTA ===");
        System.out.println("Conta ID: " + id);
        System.out.println("Número: " + numero);
        System.out.println("Tipo: " + tipo);
        System.out.println("Saldo: R$ " + String.format("%.2f", saldo));
        System.out.println("Data Abertura: " + dataAbertura);
        System.out.println("Status: " + status);
    }

    public static Conta criar() {
        System.out.println("\n=== CRIAR CONTA ===");
        int numero = contadorId * 1000;
        String tipo = "CORRENTE";
        double saldoInicial = 0.0;
        
        Conta conta = new Conta(numero, tipo, saldoInicial);
        contas.add(conta);
        
        System.out.println("[SUCESSO] Conta criada com ID: " + conta.getId());
        return conta;
    }

    public void consultar() {
        menuConta();
    }
    
    public void consultar(boolean detalhado) {
        if (detalhado) {
            System.out.println("\n=== CONSULTA DETALHADA ====");
            menuConta();
            System.out.println("Limite disponível: R$ 5000.00");
            System.out.println("Taxa de manutenção: R$ 15.00");
        } else {
            System.out.println("Conta " + numero + " - Saldo: R$ " + String.format("%.2f", saldo));
        }
    }

    public void depositar(double valor) {
        if (valor > 0 && isAtiva()) {
            this.saldo += valor;
            System.out.println("[SUCESSO] Depósito de R$ " + String.format("%.2f", valor) + " realizado.");
        } else {
            System.out.println("[ERRO] Não foi possível realizar o depósito.");
        }
    }
    
    public void depositar(double valor, String descricao) {
        if (valor > 0 && isAtiva()) {
            this.saldo += valor;
            System.out.println("[SUCESSO] Depósito de R$ " + String.format("%.2f", valor) + " realizado.");
            System.out.println("Descrição: " + descricao);
        } else {
            System.out.println("[ERRO] Não foi possível realizar o depósito.");
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo && isAtiva()) {
            this.saldo -= valor;
            System.out.println("[SUCESSO] Saque de R$ " + String.format("%.2f", valor) + " realizado.");
            return true;
        }
        System.out.println("[ERRO] Não foi possível realizar o saque.");
        return false;
    }
    
    public boolean sacar(double valor, String motivo) {
        if (valor > 0 && valor <= saldo && isAtiva()) {
            this.saldo -= valor;
            System.out.println("[SUCESSO] Saque de R$ " + String.format("%.2f", valor) + " realizado.");
            System.out.println("Motivo: " + motivo);
            return true;
        }
        System.out.println("[ERRO] Não foi possível realizar o saque.");
        return false;
    }

    public void encerrar() {
        this.status = "ENCERRADA";
        System.out.println("[SUCESSO] Conta encerrada.");
    }

    public static Conta buscarPorNumero(int numero) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numero && conta.isAtiva()) {
                return conta;
            }
        }
        return null;
    }

    public static List<Conta> listarContas() {
        return new ArrayList<>(contas);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", numero=" + numero +
                ", tipo='" + tipo + '\'' +
                ", saldo=" + saldo +
                ", dataAbertura='" + dataAbertura + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
