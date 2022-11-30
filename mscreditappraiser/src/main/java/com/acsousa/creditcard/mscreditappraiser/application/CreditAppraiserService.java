package com.acsousa.creditcard.mscreditappraiser.application;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acsousa.creditcard.mscreditappraiser.domain.models.ClientData;
import com.acsousa.creditcard.mscreditappraiser.domain.models.ClientSituation;
import com.acsousa.creditcard.mscreditappraiser.infra.client.ClientResourceClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final ClientResourceClient clientResourceClient;

    public ClientSituation getClientSituation(String cpf){
        ResponseEntity<ClientData> clientDataResponse = clientResourceClient.getClientByCpf(cpf);
        return ClientSituation                
                .builder()
                .client(clientDataResponse.getBody())
                .build();
    }
}
