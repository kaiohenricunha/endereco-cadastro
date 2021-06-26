/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbp.client;

import com.tbp.model.Endereco;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ViaCEPClient { 
    public Endereco buscaEnderecoPor(String cep){
            RestTemplate template = new RestTemplate();
            return template.getForObject("https://viacep.com.br/ws/{cep}/json",Endereco.class, cep);
        }
    }