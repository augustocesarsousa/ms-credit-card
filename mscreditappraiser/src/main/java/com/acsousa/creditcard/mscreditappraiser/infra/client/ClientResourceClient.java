package com.acsousa.creditcard.mscreditappraiser.infra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.acsousa.creditcard.mscreditappraiser.domain.models.ClientData;

@FeignClient(value = "msclients", path = "/clients")
public interface ClientResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<ClientData> getClientByCpf(@RequestParam("cpf") String cpf);
}