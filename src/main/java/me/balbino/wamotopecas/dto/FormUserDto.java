package me.balbino.wamotopecas.dto;

import me.balbino.wamotopecas.model.User;
import me.balbino.wamotopecas.repository.UserRepository;

public class FormUserDto {

    private String nome;
    private String email;
    private String senha;

    public FormUserDto(User user) {
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.senha = user.getSenha();
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

    public User atualizar(Long id, UserRepository userRepository) {
        User user = userRepository.getOne(id);

        user.setNome(this.nome);
        user.setSenha(this.senha);

        return user;
    }
}
