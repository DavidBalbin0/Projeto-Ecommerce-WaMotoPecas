package me.balbino.wamotopecas.dto;

import me.balbino.wamotopecas.model.Produto;
import me.balbino.wamotopecas.repository.ProdutoRepository;

public class FormProduto {

    private String nomeProduto;
    private String imagemUrl;
    private Long preco;
    private String descricao;

    public FormProduto(Produto produto) {
        this.nomeProduto = produto.getNome();
        this.imagemUrl = produto.getImagemUrl();
        this.preco = produto.getPreco();
        this.descricao = produto.getDescricao();
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
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

    public Produto atualizar(Long id, ProdutoRepository produtoRepository) {
        Produto produto = produtoRepository.getOne(id);

        produto.setNome(this.nomeProduto);
        produto.setPreco(this.preco);

        return produto;
    }
}
