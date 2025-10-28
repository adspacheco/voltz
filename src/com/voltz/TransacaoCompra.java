package com.voltz;

public class TransacaoCompra extends Transacao {
    private double taxaCompra;
    
    public TransacaoCompra(String simboloCripto, double quantidade, double valor, int contaId) {
        super("COMPRA", simboloCripto, quantidade, valor, contaId);
        this.taxaCompra = 0.02;
    }
    
    @Override
    public void processar() {
        this.status = "CONCLUIDA";
        System.out.println("Compra processada com sucesso!");
        System.out.println("Taxa aplicada: " + (taxaCompra * 100) + "%");
    }
    
    @Override
    public double calcularTaxa() {
        return valor * taxaCompra;
    }
    
    public double getTaxaCompra() {
        return taxaCompra;
    }
    
    public void setTaxaCompra(double taxaCompra) {
        this.taxaCompra = taxaCompra;
    }
    
    public void aplicarDesconto(double percentual) {
        if (percentual > 0 && percentual < 1) {
            this.valor = this.valor * (1 - percentual);
            System.out.println("Desconto de " + (percentual * 100) + "% aplicado!");
        }
    }
    
    @Override
    public String toString() {
        return "TransacaoCompra{" +
                "id=" + id +
                ", simboloCripto='" + simboloCripto + '\'' +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                ", taxaCompra=" + taxaCompra +
                ", status='" + status + '\'' +
                ", contaId=" + contaId +
                '}';
    }
}