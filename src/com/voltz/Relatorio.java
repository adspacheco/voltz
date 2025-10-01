package com.voltz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Relatorio {
    private int id;
    private String tipoRelatorio;
    private String conteudo;
    private LocalDateTime dataGeracao;
    private String status;
    private static List<Relatorio> relatorios = new ArrayList<>();
    private static int proximoId = 1;

    public Relatorio() {
    }

    public Relatorio(String tipoRelatorio, String conteudo) {
        this.id = proximoId++;
        this.tipoRelatorio = tipoRelatorio;
        this.conteudo = conteudo;
        this.dataGeracao = LocalDateTime.now();
        this.status = "GERADO";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoRelatorio() {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(String tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean relatorioSaldo() {
        try {
            StringBuilder relatorio = new StringBuilder();
            relatorio.append("=== RELATÓRIO DE SALDO ===\n");
            relatorio.append("Data de Geração: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))).append("\n\n");
            
            relatorio.append("Saldo em Conta Corrente: R$ 15.750,00\n");
            relatorio.append("Saldo em Poupança: R$ 8.200,00\n");
            relatorio.append("Saldo Total em Reais: R$ 23.950,00\n\n");
            
            relatorio.append("CARTEIRA DE CRIPTOATIVOS:\n");
            relatorio.append("- Bitcoin (BTC): 0.5 BTC = R$ 125.000,00\n");
            relatorio.append("- Ethereum (ETH): 2.3 ETH = R$ 23.000,00\n");
            relatorio.append("- Solana (SOL): 50 SOL = R$ 3.500,00\n");
            relatorio.append("Total em Criptoativos: R$ 151.500,00\n\n");
            
            relatorio.append("PATRIMÔNIO TOTAL: R$ 175.450,00\n");
            
            Relatorio novoRelatorio = new Relatorio("SALDO", relatorio.toString());
            relatorios.add(novoRelatorio);
            
            System.out.println("Relatório de saldo gerado com sucesso! ID: " + novoRelatorio.getId());
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatório de saldo: " + e.getMessage());
            return false;
        }
    }

    public boolean relatorioTransacoes() {
        try {
            StringBuilder relatorio = new StringBuilder();
            relatorio.append("=== RELATÓRIO DE TRANSAÇÕES ===\n");
            relatorio.append("Data de Geração: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))).append("\n\n");
            
            relatorio.append("ÚLTIMAS TRANSAÇÕES:\n");
            relatorio.append("01/10/2024 - COMPRA - Bitcoin - 0.1 BTC - R$ 25.000,00\n");
            relatorio.append("28/09/2024 - VENDA - Ethereum - 0.5 ETH - R$ 5.000,00\n");
            relatorio.append("25/09/2024 - COMPRA - Solana - 20 SOL - R$ 1.400,00\n");
            relatorio.append("22/09/2024 - COMPRA - Ethereum - 1.0 ETH - R$ 10.000,00\n");
            relatorio.append("20/09/2024 - DEPÓSITO - Conta Corrente - R$ 5.000,00\n\n");
            
            relatorio.append("RESUMO DO MÊS:\n");
            relatorio.append("Total de Compras: R$ 36.400,00\n");
            relatorio.append("Total de Vendas: R$ 5.000,00\n");
            relatorio.append("Saldo Líquido: -R$ 31.400,00\n");
            relatorio.append("Número de Transações: 5\n");
            
            Relatorio novoRelatorio = new Relatorio("TRANSACOES", relatorio.toString());
            relatorios.add(novoRelatorio);
            
            System.out.println("Relatório de transações gerado com sucesso! ID: " + novoRelatorio.getId());
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatório de transações: " + e.getMessage());
            return false;
        }
    }

    public boolean relatorioLucros() {
        try {
            StringBuilder relatorio = new StringBuilder();
            relatorio.append("=== RELATÓRIO DE LUCROS E PERDAS ===\n");
            relatorio.append("Data de Geração: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))).append("\n\n");
            
            relatorio.append("ANÁLISE POR CRIPTOATIVO:\n\n");
            
            relatorio.append("Bitcoin (BTC):\n");
            relatorio.append("  Valor Investido: R$ 75.000,00\n");
            relatorio.append("  Valor Atual: R$ 125.000,00\n");
            relatorio.append("  Lucro/Prejuízo: +R$ 50.000,00 (+66.67%)\n\n");
            
            relatorio.append("Ethereum (ETH):\n");
            relatorio.append("  Valor Investido: R$ 25.000,00\n");
            relatorio.append("  Valor Atual: R$ 23.000,00\n");
            relatorio.append("  Lucro/Prejuízo: -R$ 2.000,00 (-8.00%)\n\n");
            
            relatorio.append("Solana (SOL):\n");
            relatorio.append("  Valor Investido: R$ 4.000,00\n");
            relatorio.append("  Valor Atual: R$ 3.500,00\n");
            relatorio.append("  Lucro/Prejuízo: -R$ 500,00 (-12.50%)\n\n");
            
            relatorio.append("RESUMO GERAL:\n");
            relatorio.append("Total Investido: R$ 104.000,00\n");
            relatorio.append("Valor Atual da Carteira: R$ 151.500,00\n");
            relatorio.append("Lucro Total: +R$ 47.500,00 (+45.67%)\n");
            relatorio.append("Melhor Performance: Bitcoin (+66.67%)\n");
            relatorio.append("Pior Performance: Solana (-12.50%)\n");
            
            Relatorio novoRelatorio = new Relatorio("LUCROS", relatorio.toString());
            relatorios.add(novoRelatorio);
            
            System.out.println("Relatório de lucros gerado com sucesso! ID: " + novoRelatorio.getId());
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatório de lucros: " + e.getMessage());
            return false;
        }
    }

    public boolean visualizar(int id) {
        try {
            for (Relatorio relatorio : relatorios) {
                if (relatorio.getId() == id) {
                    System.out.println("\n" + relatorio.getConteudo());
                    relatorio.setStatus("VISUALIZADO");
                    return true;
                }
            }
            System.out.println("Relatório não encontrado com ID: " + id);
            return false;
        } catch (Exception e) {
            System.out.println("Erro ao visualizar relatório: " + e.getMessage());
            return false;
        }
    }

    public List<Relatorio> listarTodos() {
        try {
            if (relatorios.isEmpty()) {
                System.out.println("Nenhum relatório encontrado");
                return new ArrayList<>();
            }
            
            System.out.println("\n=== RELATÓRIOS DISPONÍVEIS ===");
            for (Relatorio relatorio : relatorios) {
                System.out.println("ID: " + relatorio.getId() + 
                                 " | Tipo: " + relatorio.getTipoRelatorio() + 
                                 " | Status: " + relatorio.getStatus() + 
                                 " | Gerado em: " + relatorio.getDataGeracao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            }
            return new ArrayList<>(relatorios);
        } catch (Exception e) {
            System.out.println("Erro ao listar relatórios: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public String toString() {
        return "Relatorio{" +
                "id=" + id +
                ", tipoRelatorio='" + tipoRelatorio + '\'' +
                ", dataGeracao=" + dataGeracao +
                ", status='" + status + '\'' +
                '}';
    }
}
