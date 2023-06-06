package org.acme.test01.satheesh.controller;


import lombok.extern.slf4j.Slf4j;
import org.acme.test01.satheesh.services.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping(value = "/useraccount",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listAccount() {
        return new ResponseEntity<>(accountService.listAccount(), HttpStatus.OK);
    }
}
