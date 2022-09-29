package me.balbino.wamotopecas.controller;

import me.balbino.wamotopecas.dto.FormUserDto;
import me.balbino.wamotopecas.model.User;
import me.balbino.wamotopecas.repository.UserRepository;
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
public class UsersController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/usuarios")
    public List<User> mostraUsuarios(){
        List<User> user = userRepository.findAll();
        return user;
    }

    @GetMapping("/usuarios/{id}")
    public User mostraUsuarios(@PathVariable Long id){
        Optional<User> usuario = userRepository.findById(id);
        if(usuario.isPresent()){
            return  usuario.get();
        } else {
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }
    }

    @PostMapping("/cadastraUsuario")
    public ResponseEntity<FormUserDto> cadastraUsuario(@RequestBody FormUserDto formUserDto, UriComponentsBuilder builder){
        User user = new User();
        user.converter(formUserDto);

        URI uri = builder.path("/produtos/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new FormUserDto(user));
    }

    @PutMapping("/usuario/{id}")
    @Transactional
    public ResponseEntity<FormUserDto> atualizaCadastro(@PathVariable Long id, @RequestBody FormUserDto form){
        User user = form.atualizar(id, userRepository);

        return ResponseEntity.ok(new FormUserDto(user));
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> deletarProdutos(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
