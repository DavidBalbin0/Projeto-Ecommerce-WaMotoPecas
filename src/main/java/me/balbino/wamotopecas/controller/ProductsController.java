package me.balbino.wamotopecas.controller;

import me.balbino.wamotopecas.dto.FormProductDto;
import me.balbino.wamotopecas.dto.ProductDto;
import me.balbino.wamotopecas.model.Product;
import me.balbino.wamotopecas.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    //gera json
    @GetMapping("/produtos") @ResponseBody
    public Page<ProductDto> produtosHome(String productName, Pageable pageable){
        if(productName == null) {
            Page<Product> products = productRepository.findAll(pageable);
            return ProductDto.converterPage(products);
        } else{
            Page<Product> products = productRepository.findByNome(productName, pageable);
            return ProductDto.converterPage(products);
        }
    }
    @GetMapping("/produtos/{id}")@ResponseBody
    public ProductDto produtoPorId(@PathVariable Long id){
        Optional<Product> produto = productRepository.findById(id);
        if(produto.isPresent()){
            Product p = produto.get();
            return ProductDto.converter(p);
        }else {
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<FormProductDto> cadastrar(@RequestBody FormProductDto produtoForm, UriComponentsBuilder builder){
        Product product = new Product();
        System.out.println(produtoForm.getNomeProduto());
        product.converterParaProduto(produtoForm);
        productRepository.save(product);
        URI uri = builder.path("/produtos/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new FormProductDto(product));
    }

    @PutMapping("/produtos/{id}")
    @Transactional
    public ResponseEntity<FormProductDto> atualizaProdutos(@PathVariable Long id, @RequestBody FormProductDto form){
        Product product = form.atualizar(id, productRepository);

        return ResponseEntity.ok(new FormProductDto(product));
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<?> deletarProdutos(@PathVariable Long id){
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
//    @GetMapping("/painelcontrole")
//    public String painelDeControle(){
//        return "painelcontrole";
//
//    @GetMapping("/home")
//    public String home(){
//        return "home";
//    }

}

