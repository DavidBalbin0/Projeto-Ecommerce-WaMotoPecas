package me.balbino.wamotopecas.service;

import me.balbino.wamotopecas.ViaCepClient;
import me.balbino.wamotopecas.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchCepService  {

    @Autowired
    private ViaCepClient client;

    public void setCep(String numberCep) {
        Address cep = client.searchCep(numberCep);
        System.out.println(cep);
    }
}
