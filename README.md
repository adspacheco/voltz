# Projeto VOLTZ - Sistema de Investimento em Criptoativos

## FIAP - Engenharia de Software - 2ESOR

### Equipe
- **Anderson Pacheco de Souza** (RM 559938) - Classes: Usuario, Autenticacao, Alerta e Relatorio
- **Mateus Felipe Silva de Macedo** (RM 560064) - Classes: Criptoativo e Transacao
- **Pedro Cavion Zuffo** (RM 560084) - Classes: Conta e Carteira

## 📂 Estrutura do Projeto

```
com.voltz/
│
├── Main.java           # Classe principal com menu
├── Usuario.java        # Anderson
├── Autenticacao.java   # Anderson
├── Criptoativo.java    # Mateus
├── Transacao.java      # Mateus
├── Conta.java          # Pedro
├── Carteira.java       # Pedro  
├── Alerta.java         # Anderson
├── Relatorio.java      # Anderson
└── README.md           # Este arquivo
```

## 🚀 Como Usar

### 1. Configuração Inicial

```bash
# Clonar o repositório
git clone https://github.com/adspacheco/voltz

# Criar sua branch pessoal
git checkout -b feature/nome-rm_numero
# Exemplo: git checkout -b usuario/anderson-559938
```

### 2. Fluxo de Trabalho Git

```bash
# Após implementar suas classes
git add .
git commit -m "feat: implementa classe Usuario e Autenticacao"
git push origin feature/nome-rm_numero

# Criar Pull Request no GitHub para merge com main
```

## 📝 Tarefas por Membro

### Anderson (RM 559938)
- [x] Implementar classe **Usuario**
    - [x] Método cadastrar()
    - [x] Método buscar()
    - [x] Método atualizar()
    - [x] Método deletar()
    - [x] Método listar()

- [x] Implementar classe **Autenticacao**
    - [x] Método login()
    - [x] Método logout()
    - [x] Método criarSenha()
    - [x] Método verificarUsuarioLogado()
    - [x] Método alterarSenha()

- [x] Implementar classe **Alerta**
    - [x] Método criarAlertaPreco()
    - [x] Método verificarAlertas()
    - [x] Método marcarComoLido()
    - [x] Método listar()
    - [x] Método deletar()

- [x] Implementar classe **Relatorio**
    - [x] Método relatorioSaldo()
    - [x] Método relatorioTransacoes()
    - [x] Método relatorioLucros()
    - [x] Método visualizar()
    - [x] Método listarTodos()

### Mateus (RM 560064)
- [x] Implementar classe **Criptoativo**
    - [x] Método cadastrar()
    - [x] Método buscar()
    - [x] Método atualizar()
    - [x] Método listarTodos()
    - [x] Método obterCotacao()

- [x] Implementar classe **Transacao**
    - [x] Método comprar()
    - [x] Método vender()
    - [x] Método consultar()
    - [x] Método listarHistorico()
    - [x] Método cancelar()

### Pedro (RM 560084)
- [x] Implementar classe **Conta**
    - [x] Método criar()
    - [x] Método consultar()
    - [x] Método depositar()
    - [x] Método sacar()
    - [x] Método encerrar()

- [x] Implementar classe **Carteira**
    - [x] Método criar()
    - [x] Método consultar()
    - [x] Método adicionarCripto()
    - [x] Método removerCripto()
    - [x] Método obterSaldo()

