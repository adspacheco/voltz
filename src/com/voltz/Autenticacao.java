package com.voltz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class Autenticacao {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static Usuario usuarioLogado = null;

    public static boolean registrarUsuario(Scanner scanner) {
        out.println("\n=== CADASTRO DE USUÁRIO ===");
        
        out.print("Nome: ");
        String nome = scanner.nextLine();
        
        out.print("Email: ");
        String email = scanner.nextLine();
        
        if (buscarUsuarioPorEmail(email) != null) {
            out.println("[ERRO] Email já cadastrado!");
            return false;
        }
        
        out.print("CPF: ");
        String cpf = scanner.nextLine();
        
        out.print("Senha: ");
        String senha = scanner.nextLine();
        
        Usuario novoUsuario = new Usuario(nome, email, senha, cpf);
        usuarios.add(novoUsuario);
        
        out.println("[SUCESSO] Usuário cadastrado com sucesso!");
        return true;
    }

    public static boolean fazerLogin(Scanner scanner) {
        out.println("\n=== LOGIN ===");
        
        out.print("Email: ");
        String email = scanner.nextLine();
        
        out.print("Senha: ");
        String senha = scanner.nextLine();
        
        Usuario usuario = buscarUsuarioPorEmail(email);
        
        if (usuario != null && usuario.getSenha().equals(senha) && usuario.isAtivo()) {
            usuarioLogado = usuario;
            out.println("[SUCESSO] Login realizado! Bem-vindo, " + usuario.getNome());
            return true;
        } else {
            out.println("[ERRO] Email ou senha incorretos!");
            return false;
        }
    }

    public static void fazerLogout() {
        if (usuarioLogado != null) {
            out.println("Logout realizado. Até logo, " + usuarioLogado.getNome() + "!");
            usuarioLogado = null;
        }
    }

    public static boolean estaLogado() {
        return usuarioLogado != null;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static Usuario buscarUsuarioPorEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    public static List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public static boolean atualizarUsuario(Scanner scanner) {
        if (!estaLogado()) {
            out.println("[ERRO] Você precisa estar logado!");
            return false;
        }

        Usuario usuario = usuarioLogado;
        out.println("\n=== ATUALIZAR DADOS ===");
        
        out.print("Novo nome (atual: " + usuario.getNome() + "): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.trim().isEmpty()) {
            usuario.setNome(novoNome);
        }
        
        out.print("Novo email (atual: " + usuario.getEmail() + "): ");
        String novoEmail = scanner.nextLine();
        if (!novoEmail.trim().isEmpty()) {
            Usuario emailExistente = buscarUsuarioPorEmail(novoEmail);
            if (emailExistente != null && !emailExistente.equals(usuario)) {
                out.println("[ERRO] Email já está em uso!");
                return false;
            }
            usuario.setEmail(novoEmail);
        }
        
        out.println("[SUCESSO] Dados atualizados!");
        return true;
    }

    public static boolean deletarUsuario() {
        if (!estaLogado()) {
            out.println("[ERRO] Você precisa estar logado!");
            return false;
        }

        Usuario usuario = usuarioLogado;
        usuario.setAtivo(false);
        usuarioLogado = null;
        
        out.println("[SUCESSO] Conta desativada com sucesso!");
        return true;
    }

    public static boolean alterarSenha(Scanner scanner) {
        if (!estaLogado()) {
            out.println("[ERRO] Você precisa estar logado!");
            return false;
        }

        out.println("\n=== ALTERAR SENHA ===");
        
        out.print("Senha atual: ");
        String senhaAtual = scanner.nextLine();
        
        if (!usuarioLogado.getSenha().equals(senhaAtual)) {
            out.println("[ERRO] Senha atual incorreta!");
            return false;
        }
        
        out.print("Nova senha: ");
        String novaSenha = scanner.nextLine();
        
        out.print("Confirme a nova senha: ");
        String confirmaSenha = scanner.nextLine();
        
        if (!novaSenha.equals(confirmaSenha)) {
            out.println("[ERRO] Senhas não coincidem!");
            return false;
        }
        
        usuarioLogado.setSenha(novaSenha);
        out.println("[SUCESSO] Senha alterada com sucesso!");
        return true;
    }
}
