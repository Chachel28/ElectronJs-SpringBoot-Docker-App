package net.juanxxiii.rest.controller;

import net.juanxxiii.dto.TelephoneDto;
import net.juanxxiii.services.JoinQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    @Autowired
    private JoinQueryService joinQueryService;

    @GetMapping("/clients")
    public ResponseEntity<List<TelephoneDto>> getDtoList(){
        return new ResponseEntity<List<TelephoneDto>>(joinQueryService.getDtoList(), HttpStatus.OK);
    }
}
