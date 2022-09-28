package me.balbino.wamotopecas.dto;

import me.balbino.wamotopecas.model.Produto;
import org.springframework.data.domain.Page;

public class ProdutoDto {

    private Long id;
    private String nome;
    private String imagemUrl;
    private Long preco;
    private String descricao;

    public static Page<ProdutoDto> converterPage(Page<Produto> produtos) {
       return produtos.map(ProdutoDto::new);
    }

    public static ProdutoDto converter(Produto produto){
        return new ProdutoDto(produto);
    }


    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.imagemUrl = produto.getImagemUrl();
        this.preco = produto.getPreco();
        this.descricao = produto.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
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
}