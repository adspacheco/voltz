package com.voltz;

public class TransacaoVenda extends Transacao {
    private double lucro;
    private double taxaVenda;
    
    public TransacaoVenda(String simboloCripto, double quantidade, double valor, int contaId) {
        super("VENDA", simboloCripto, quantidade, valor, contaId);
        this.taxaVenda = 0.015;
        this.lucro = 0;
    }
    
    @Override
    public void processar() {
        this.status = "CONCLUIDA";
        System.out.println("Venda processada com sucesso!");
        System.out.println("Taxa aplicada: " + (taxaVenda * 100) + "%");
        if (lucro > 0) {
            System.out.println("Lucro obtido: R$ " + String.format("%.2f", lucro));
        }
    }
    
    @Override
    public double calcularTaxa() {
        return valor * taxaVenda;
    }
    
    public void calcularLucro(double valorCompra) {
        this.lucro = this.valor - valorCompra;
    }
    
    public void calcularLucro(double precoCompra, double quantidade) {
        double valorCompra = precoCompra * quantidade;
        this.lucro = this.valor - valorCompra;
    }
    
    public double getLucro() {
        return lucro;
    }
    
    public void setLucro(double lucro) {
        this.lucro = lucro;
    }
    
    public double getTaxaVenda() {
        return taxaVenda;
    }
    
    public void setTaxaVenda(double taxaVenda) {
        this.taxaVenda = taxaVenda;
    }
    
    @Override
    public String toString() {
        return "TransacaoVenda{" +
                "id=" + id +
                ", simboloCripto='" + simboloCripto + '\'' +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                ", lucro=" + lucro +
                ", taxaVenda=" + taxaVenda +
                ", status='" + status + '\'' +
                ", contaId=" + contaId +
                '}';
    }
}