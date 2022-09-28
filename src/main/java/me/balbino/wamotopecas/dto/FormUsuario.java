package me.balbino.wamotopecas.dto;

import me.balbino.wamotopecas.model.Produto;
import me.balbino.wamotopecas.model.Usuario;
import me.balbino.wamotopecas.repository.UsuarioRepository;

public class FormUsuario {

    private String nome;
    private String email;
    private String senha;

    public FormUsuario(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
        Usuario usuario = usuarioRepository.getOne(id);

        usuario.setNome(this.nome);
        usuario.setSenha(this.senha);

        return usuario;
    }
}
