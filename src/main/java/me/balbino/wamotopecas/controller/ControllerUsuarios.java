package me.balbino.wamotopecas.controller;

import me.balbino.wamotopecas.dto.FormProduto;
import me.balbino.wamotopecas.dto.FormUsuario;
import me.balbino.wamotopecas.model.Produto;
import me.balbino.wamotopecas.model.Usuario;
import me.balbino.wamotopecas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
public class ControllerUsuarios {

    @Autowired
    UsuarioRepository usuarioRepository;


    @GetMapping("/usuarios")
    public List<Usuario> mostraUsuarios(){
        List<Usuario> usuario = usuarioRepository.findAll();
        return  usuario;
    }

    @GetMapping("/usuarios/{id}")
    public Usuario mostraUsuarios(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            return  usuario.get();
        } else {
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }
    }

    @PostMapping("/cadastraUsuario")
    public ResponseEntity<FormUsuario> cadastraUsuario(@RequestBody FormUsuario formUsuario, UriComponentsBuilder builder){
        Usuario usuario = new Usuario();
        usuario.converter(formUsuario);

        URI uri = builder.path("/produtos/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new FormUsuario(usuario));
    }

    @PutMapping("/usuario/{id}")
    @Transactional
    public ResponseEntity<FormUsuario> atualizaCadastro(@PathVariable Long id, @RequestBody FormUsuario form){
        Usuario usuario = form.atualizar(id, usuarioRepository);

        return ResponseEntity.ok(new FormUsuario(usuario));
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> deletarProdutos(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
