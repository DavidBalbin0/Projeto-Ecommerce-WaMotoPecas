package me.balbino.wamotopecas.dto;

import me.balbino.wamotopecas.model.Product;
import me.balbino.wamotopecas.repository.ProductRepository;

public class FormProductDto {

    private String nomeProduto;
    private String imagemUrl;
    private Long preco;
    private String descricao;

    public FormProductDto(Product product) {
        this.nomeProduto = product.getNome();
        this.imagemUrl = product.getImagemUrl();
        this.preco = product.getPreco();
        this.descricao = product.getDescricao();
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

    public Product atualizar(Long id, ProductRepository productRepository) {
        Product product = productRepository.getOne(id);

        product.setNome(this.nomeProduto);
        product.setPreco(this.preco);

        return product;
    }
}
