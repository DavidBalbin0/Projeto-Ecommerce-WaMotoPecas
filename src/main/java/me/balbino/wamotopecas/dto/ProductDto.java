package me.balbino.wamotopecas.dto;

import me.balbino.wamotopecas.model.Product;
import org.springframework.data.domain.Page;

public class ProductDto {

    private Long id;
    private String nome;
    private String imagemUrl;
    private Long preco;
    private String descricao;

    public static Page<ProductDto> converterPage(Page<Product> produtos) {
       return produtos.map(ProductDto::new);
    }

    public static ProductDto converter(Product product){
        return new ProductDto(product);
    }


    public ProductDto(Product product) {
        this.id = product.getId();
        this.nome = product.getNome();
        this.imagemUrl = product.getImagemUrl();
        this.preco = product.getPreco();
        this.descricao = product.getDescricao();
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