package me.balbino.wamotopecas.model;

import me.balbino.wamotopecas.dto.FormProductDto;

import javax.persistence.*;

@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String imagemUrl;
    private Long preco;
    private String descricao;


    public Product(String nome, String imagemUrl, Long preco, String descricao) {
        this.nome = nome;
        this.imagemUrl = imagemUrl;
        this.preco = preco;
        this.descricao = descricao;
    }

    public Product() {

    }

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public Long getPreco() {
        return preco;
    }

    public void setPreco(Long preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void converterParaProduto(FormProductDto novoProduto){
        this.nome = novoProduto.getNomeProduto();
        this.imagemUrl = novoProduto.getImagemUrl();
        this.preco = novoProduto.getPreco();
        this.descricao = novoProduto.getDescricao();
    }
}
