import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

package com.voltz;

public class Conta {
    private static List<Conta> contas = new ArrayList<>();
    private static int contadorId = 1;

    private int id;
    private String numeroConta;
    private double saldo;
    private boolean ativa;

    public Conta(String numeroConta, double saldoInicial) {
        this.id = contadorId++;
        this.numeroConta = numeroConta;
        this.saldo = saldoInicial;
        this.ativa = true;
    }

    public int getId() {
        return id;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public boolean sacar(double valor) {
        if (valor > saldo) {
            return false;
        }
        this.saldo -= valor;
        return true;
    }

    public void desativar() {
        this.ativa = false;
    }

    public static Conta criarConta(Scanner scanner) {
        out.println("\n=== CRIAR CONTA ===");

        out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();

        out.print("Saldo inicial: ");
        double saldoInicial = Double.parseDouble(scanner.nextLine());

        Conta conta = new Conta(numeroConta, saldoInicial);
        contas.add(conta);

        out.println("[SUCESSO] Conta criada com ID: " + conta.getId());
        return conta;
    }

    public static Conta buscarPorNumero(String numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumeroConta().equals(numeroConta) && conta.isAtiva()) {
                return conta;
            }
        }
        return null;
    }

    public static List<Conta> listarContas() {
        return new ArrayList<>(contas);
    }

    public static boolean removerConta(String numeroConta) {
        Conta conta = buscarPorNumero(numeroConta);
        if (conta != null) {
            conta.desativar();
            out.println("[SUCESSO] Conta " + numeroConta + " desativada!");
            return true;
        }
        out.println("[ERRO] Conta não encontrada!");
        return false;
    }
}
