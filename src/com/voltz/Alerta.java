package com.voltz;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Alerta {
    private int id;
    private String criptoativo;
    private double precoAlvo;
    private String tipoComparacao;
    private boolean lido;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataLeitura;
    private static List<Alerta> alertas = new ArrayList<>();
    private static int proximoId = 1;

    public Alerta() {
    }

    public Alerta(String criptoativo, double precoAlvo, String tipoComparacao) {
        this.id = proximoId++;
        this.criptoativo = criptoativo;
        this.precoAlvo = precoAlvo;
        this.tipoComparacao = tipoComparacao;
        this.lido = false;
        this.dataCriacao = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCriptoativo() {
        return criptoativo;
    }

    public void setCriptoativo(String criptoativo) {
        this.criptoativo = criptoativo;
    }

    public double getPrecoAlvo() {
        return precoAlvo;
    }

    public void setPrecoAlvo(double precoAlvo) {
        this.precoAlvo = precoAlvo;
    }

    public String getTipoComparacao() {
        return tipoComparacao;
    }

    public void setTipoComparacao(String tipoComparacao) {
        this.tipoComparacao = tipoComparacao;
    }

    public boolean isLido() {
        return lido;
    }

    public void setLido(boolean lido) {
        this.lido = lido;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataLeitura() {
        return dataLeitura;
    }

    public void setDataLeitura(LocalDateTime dataLeitura) {
        this.dataLeitura = dataLeitura;
    }

    public boolean criarAlertaPreco(String criptoativo, double precoAlvo, String tipoComparacao) {
        try {
            if (criptoativo == null || criptoativo.trim().isEmpty()) {
                System.out.println("Erro: Nome do criptoativo é obrigatório");
                return false;
            }
            if (precoAlvo <= 0) {
                System.out.println("Erro: Preço alvo deve ser maior que zero");
                return false;
            }
            if (!tipoComparacao.equals("maior") && !tipoComparacao.equals("menor")) {
                System.out.println("Erro: Tipo de comparação deve ser 'maior' ou 'menor'");
                return false;
            }

            Alerta novoAlerta = new Alerta(criptoativo, precoAlvo, tipoComparacao);
            alertas.add(novoAlerta);
            System.out.println("Alerta criado com sucesso para " + criptoativo + " quando preço for " + tipoComparacao + " que R$ " + precoAlvo);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao criar alerta: " + e.getMessage());
            return false;
        }
    }

    public boolean verificarAlertas(String criptoativo, double precoAtual) {
        try {
            boolean alertaDisparado = false;
            for (Alerta alerta : alertas) {
                if (alerta.getCriptoativo().equalsIgnoreCase(criptoativo) && !alerta.isLido()) {
                    boolean condicaoAtendida = false;
                    if (alerta.getTipoComparacao().equals("maior") && precoAtual > alerta.getPrecoAlvo()) {
                        condicaoAtendida = true;
                    } else if (alerta.getTipoComparacao().equals("menor") && precoAtual < alerta.getPrecoAlvo()) {
                        condicaoAtendida = true;
                    }
                    
                    if (condicaoAtendida) {
                        System.out.println("🚨 ALERTA DISPARADO! " + criptoativo + " atingiu R$ " + precoAtual + 
                                         " (meta: " + alerta.getTipoComparacao() + " que R$ " + alerta.getPrecoAlvo() + ")");
                        alertaDisparado = true;
                    }
                }
            }
            return alertaDisparado;
        } catch (Exception e) {
            System.out.println("Erro ao verificar alertas: " + e.getMessage());
            return false;
        }
    }

    public boolean marcarComoLido(int id) {
        try {
            for (Alerta alerta : alertas) {
                if (alerta.getId() == id) {
                    alerta.setLido(true);
                    alerta.setDataLeitura(LocalDateTime.now());
                    System.out.println("Alerta ID " + id + " marcado como lido");
                    return true;
                }
            }
            System.out.println("Alerta não encontrado com ID: " + id);
            return false;
        } catch (Exception e) {
            System.out.println("Erro ao marcar alerta como lido: " + e.getMessage());
            return false;
        }
    }

    public List<Alerta> listar() {
        try {
            if (alertas.isEmpty()) {
                System.out.println("Nenhum alerta encontrado");
                return new ArrayList<>();
            }
            
            System.out.println("\n=== ALERTAS ===");
            for (Alerta alerta : alertas) {
                String status = alerta.isLido() ? "LIDO" : "NÃO LIDO";
                System.out.println("ID: " + alerta.getId() + 
                                 " | Cripto: " + alerta.getCriptoativo() + 
                                 " | Preço: " + alerta.getTipoComparacao() + " que R$ " + alerta.getPrecoAlvo() + 
                                 " | Status: " + status + 
                                 " | Criado: " + alerta.getDataCriacao().toLocalDate());
            }
            return new ArrayList<>(alertas);
        } catch (Exception e) {
            System.out.println("Erro ao listar alertas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean deletar(int id) {
        try {
            for (int i = 0; i < alertas.size(); i++) {
                if (alertas.get(i).getId() == id) {
                    alertas.remove(i);
                    System.out.println("Alerta ID " + id + " deletado com sucesso");
                    return true;
                }
            }
            System.out.println("Alerta não encontrado com ID: " + id);
            return false;
        } catch (Exception e) {
            System.out.println("Erro ao deletar alerta: " + e.getMessage());
            return false;
        }
    }

    public String toString() {
        return "Alerta{" +
                "id=" + id +
                ", criptoativo='" + criptoativo + '\'' +
                ", precoAlvo=" + precoAlvo +
                ", tipoComparacao='" + tipoComparacao + '\'' +
                ", lido=" + lido +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}
