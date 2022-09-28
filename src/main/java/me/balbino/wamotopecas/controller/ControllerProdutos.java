package me.balbino.wamotopecas.controller;

import me.balbino.wamotopecas.dto.FormProduto;
import me.balbino.wamotopecas.dto.ProdutoDto;
import me.balbino.wamotopecas.model.Produto;
import me.balbino.wamotopecas.repository.ProdutoRepository;
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
public class ControllerProdutos {
    @Autowired
    private ProdutoRepository produtoRepository;

    //gera json
    @GetMapping("/produtos") @ResponseBody
    public Page<ProdutoDto> produtosHome(String nomeProduto, Pageable paginacao){
        if(nomeProduto == null) {
            Page<Produto> produtos = produtoRepository.findAll(paginacao);
            return ProdutoDto.converterPage(produtos);
        } else{
            Page<Produto> produtos = produtoRepository.findByNome(nomeProduto, paginacao);
            return ProdutoDto.converterPage(produtos);
        }
    }
    @GetMapping("/produtos/{id}")@ResponseBody
    public ProdutoDto produtoPorId(@PathVariable Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()){
            Produto p = produto.get();
            return ProdutoDto.converter(p);
        }else {
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<FormProduto> cadastrar(@RequestBody FormProduto produtoForm, UriComponentsBuilder builder){
        Produto produto = new Produto();
        System.out.println(produtoForm.getNomeProduto());
        produto.converterParaProduto(produtoForm);
        produtoRepository.save(produto);
        URI uri = builder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new FormProduto(produto));
    }

    @PutMapping("/produtos/{id}")
    @Transactional
    public ResponseEntity<FormProduto> atualizaProdutos(@PathVariable Long id, @RequestBody FormProduto form){
        Produto produto = form.atualizar(id, produtoRepository);

        return ResponseEntity.ok(new FormProduto(produto));
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<?> deletarProdutos(@PathVariable Long id){
        produtoRepository.deleteById(id);
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

