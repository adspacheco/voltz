# Projeto VOLTZ - Sistema de Investimento em Criptoativos

## FIAP - Engenharia de Software - 2ESOR

### Equipe
- **Anderson Pacheco de Souza** (RM 559938) - Classes: Usuario e Autenticacao
- **Mateus Felipe Silva de Macedo** (RM 560064) - Classes: Criptoativo e Transacao
- **Pedro Cavion Zuffo** (RM 560084) - Classes: Conta e Carteira
- **Rodrigo Oliveira Maia Piñeiro** (RM 559230) - Classes: Alerta e Relatorio

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
├── Alerta.java         # Rodrigo
├── Relatorio.java      # Rodrigo
└── README.md           # Este arquivo
```

## 🚀 Como Usar

### 1. Configuração Inicial

```bash
# Clonar o repositório
git clone [URL_DO_REPOSITORIO]
cd voltz

# Criar sua branch pessoal
git checkout -b feature/nome-rm_numero
# Exemplo: git checkout -b feature/anderson-559938
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
- [ ] Implementar classe **Usuario**
    - [ ] Método cadastrar()
    - [ ] Método buscar()
    - [ ] Método atualizar()
    - [ ] Método deletar()
    - [ ] Método listar()

- [ ] Implementar classe **Autenticacao**
    - [ ] Método login()
    - [ ] Método logout()
    - [ ] Método criarSenha()
    - [ ] Método verificarUsuarioLogado()
    - [ ] Método alterarSenha()

### Mateus (RM 560064)
- [ ] Implementar classe **Criptoativo**
    - [ ] Método cadastrar()
    - [ ] Método buscar()
    - [ ] Método atualizar()
    - [ ] Método listarTodos()
    - [ ] Método obterCotacao()

- [ ] Implementar classe **Transacao**
    - [ ] Método comprar()
    - [ ] Método vender()
    - [ ] Método consultar()
    - [ ] Método listarHistorico()
    - [ ] Método cancelar()

### Pedro (RM 560084)
- [ ] Implementar classe **Conta**
    - [ ] Método criar()
    - [ ] Método consultar()
    - [ ] Método depositar()
    - [ ] Método sacar()
    - [ ] Método encerrar()

- [ ] Implementar classe **Carteira**
    - [ ] Método criar()
    - [ ] Método consultar()
    - [ ] Método adicionarCripto()
    - [ ] Método removerCripto()
    - [ ] Método obterSaldo()

### Rodrigo (RM 559230)
- [ ] Implementar classe **Alerta**
    - [ ] Método criarAlertaPreco()
    - [ ] Método verificarAlertas()
    - [ ] Método marcarComoLido()
    - [ ] Método listar()
    - [ ] Método deletar()

- [ ] Implementar classe **Relatorio**
    - [ ] Método relatorioSaldo()
    - [ ] Método relatorioTransacoes()
    - [ ] Método relatorioLucros()
    - [ ] Método visualizar()
    - [ ] Método listarTodos()
