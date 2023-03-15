package ru.netology.moneytransferapplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import ru.netology.moneytransferapplication.model.Amount;
import ru.netology.moneytransferapplication.model.Operation;
import ru.netology.moneytransferapplication.model.OperationConfirmation;
import ru.netology.moneytransferapplication.repository.TransferRepositoryImpl;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MoneyTransferApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    TransferRepositoryImpl transferRepositoryImpl;
    private GenericContainer<?> myApp = new GenericContainer<>("transfermoneyapp:latest")
            .withExposedPorts(5500);

    @BeforeEach
    public void setUp(){
        myApp.start();
    }
    @Test
    void contextLoadsTranferApp() {
        Integer myAppPort = myApp.getMappedPort(5500);

        final String urlTransfer = "http://localhost:" + myAppPort + "/transfer";
        final String urlConfirm = "http://localhost:" + myAppPort + "/confirmOperation";

        try {
            URI uriTransfer = new URI(urlTransfer);
            URI uriConfirm = new URI(urlConfirm);

            Operation operation = new Operation("1234123412341234", "12/26", "123", "4321432143214321", new Amount(1000, "rub"));
            OperationConfirmation operationConfirmation = new OperationConfirmation("0", "0000");

            HttpEntity<Operation> requestTransfer = new HttpEntity<>(operation);
            HttpEntity<OperationConfirmation> requestConfirm = new HttpEntity<>(operationConfirmation);

            ResponseEntity<String> resultTransfer = restTemplate.postForEntity(uriTransfer, requestTransfer, String.class);
            ResponseEntity<String> resultConfirm = restTemplate.postForEntity(uriConfirm, requestConfirm, String.class);

            Assertions.assertEquals(HttpStatus.OK, resultTransfer.getStatusCode());
            Assertions.assertEquals(HttpStatus.OK, resultConfirm.getStatusCode());

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
