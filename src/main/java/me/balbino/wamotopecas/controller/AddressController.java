package me.balbino.wamotopecas.controller;

import me.balbino.wamotopecas.service.SearchCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressController {

    @Autowired
    SearchCepService searchCepService;

    @GetMapping("/cep")
    public ResponseEntity<?> getAddres(){
        searchCepService.setCep("13385052");
        return ResponseEntity.ok().build();
    }


}
