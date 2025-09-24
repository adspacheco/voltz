import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

package com.voltz;

public class Carteira {
    private static List<Carteira> carteiras = new ArrayList<>();
    private static int contadorId = 1;

    private int id;
    private String dono;
    private double saldo;

    public Carteira(String dono) {
        this.id = contadorId++;
        this.dono = dono;
        this.saldo = 0.0;
    }

    public int getId() {
        return id;
    }

    public String getDono() {
        return dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public void adicionarSaldo(double valor) {
        saldo += valor;
    }

    public boolean removerSaldo(double valor) {
        if (valor > saldo) {
            return false;
        }
        saldo -= valor;
        return true;
    }

    public static Carteira criarCarteira(Scanner scanner) {
        out.println("\n=== CRIAR CARTEIRA ===");

        out.print("Nome do dono: ");
        String dono = scanner.nextLine();

        Carteira carteira = new Carteira(dono);
        carteiras.add(carteira);

        out.println("[SUCESSO] Carteira criada para " + dono + " com ID " + carteira.getId());
        return carteira;
    }

    public static Carteira buscarPorDono(String dono) {
        for (Carteira carteira : carteiras) {
            if (carteira.getDono().equalsIgnoreCase(dono)) {
                return carteira;
            }
        }
        return null;
    }

    public static List<Carteira> listarCarteiras() {
        return new ArrayList<>(carteiras);
    }

    public static boolean removerCarteira(String dono) {
        Carteira carteira = buscarPorDono(dono);
        if (carteira != null) {
            carteiras.remove(carteira);
            out.println("[SUCESSO] Carteira do dono " + dono + " removida!");
            return true;
        }
        out.println("[ERRO] Carteira não encontrada!");
        return false;
    }
}
