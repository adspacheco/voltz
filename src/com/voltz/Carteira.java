package com.voltz;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Carteira {
    private static List<Carteira> carteiras = new ArrayList<>();
    private static int contadorId = 1;

    private int id;
    private String endereco;
    private String tipo;
    private double saldo;
    private boolean ativa;
    private Map<String, Double> criptoativos;

    public Carteira(String endereco, String tipo) {
        this.id = contadorId++;
        this.endereco = endereco;
        this.tipo = tipo;
        this.saldo = 0.0;
        this.ativa = true;
        this.criptoativos = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public Map<String, Double> getCriptoativos() {
        return new HashMap<>(criptoativos);
    }

    public void menuCarteira() {
        System.out.println("\n=== MENU CARTEIRA ===");
        System.out.println("Carteira ID: " + id);
        System.out.println("Endereço: " + endereco);
        System.out.println("Tipo: " + tipo);
        System.out.println("Saldo: R$ " + String.format("%.2f", saldo));
        System.out.println("Ativa: " + (ativa ? "Sim" : "Não"));
        System.out.println("Criptoativos: " + criptoativos.size());
    }

    public static Carteira criar() {
        System.out.println("\n=== CRIAR CARTEIRA ===");
        String endereco = "0x" + System.currentTimeMillis();
        String tipo = "DIGITAL";
        
        Carteira carteira = new Carteira(endereco, tipo);
        carteiras.add(carteira);
        
        System.out.println("[SUCESSO] Carteira criada com ID: " + carteira.getId());
        return carteira;
    }

    public void consultar() {
        menuCarteira();
        if (!criptoativos.isEmpty()) {
            System.out.println("\n--- CRIPTOATIVOS ---");
            for (Map.Entry<String, Double> entry : criptoativos.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    public void adicionarCripto(String simbolo, double quantidade) {
        if (simbolo != null && quantidade > 0 && ativa) {
            criptoativos.put(simbolo, criptoativos.getOrDefault(simbolo, 0.0) + quantidade);
            System.out.println("[SUCESSO] Adicionado " + quantidade + " " + simbolo + " à carteira.");
        } else {
            System.out.println("[ERRO] Não foi possível adicionar o criptoativo.");
        }
    }

    public boolean removerCripto(String simbolo, double quantidade) {
        if (simbolo != null && quantidade > 0 && ativa && criptoativos.containsKey(simbolo)) {
            double saldoAtual = criptoativos.get(simbolo);
            if (saldoAtual >= quantidade) {
                if (saldoAtual == quantidade) {
                    criptoativos.remove(simbolo);
                } else {
                    criptoativos.put(simbolo, saldoAtual - quantidade);
                }
                System.out.println("[SUCESSO] Removido " + quantidade + " " + simbolo + " da carteira.");
                return true;
            }
        }
        System.out.println("[ERRO] Não foi possível remover o criptoativo.");
        return false;
    }

    public double obterSaldo() {
        double saldoTotal = 0.0;
        for (Map.Entry<String, Double> entry : criptoativos.entrySet()) {
            saldoTotal += entry.getValue();
        }
        System.out.println("Saldo total em criptoativos: " + saldoTotal);
        return saldoTotal;
    }

    public static Carteira buscarPorEndereco(String endereco) {
        for (Carteira carteira : carteiras) {
            if (carteira.getEndereco().equals(endereco) && carteira.isAtiva()) {
                return carteira;
            }
        }
        return null;
    }

    public static List<Carteira> listarCarteiras() {
        return new ArrayList<>(carteiras);
    }

    public void desativar() {
        this.ativa = false;
        System.out.println("[SUCESSO] Carteira desativada.");
    }

    @Override
    public String toString() {
        return "Carteira{" +
                "id=" + id +
                ", endereco='" + endereco + '\'' +
                ", tipo='" + tipo + '\'' +
                ", saldo=" + saldo +
                ", ativa=" + ativa +
                ", criptoativos=" + criptoativos +
                '}';
    }
}
