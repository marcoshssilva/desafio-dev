package br.com.marcoshssilva.desafiodev.controllers;

import br.com.marcoshssilva.desafiodev.entities.Transacao;
import br.com.marcoshssilva.desafiodev.services.FileReaderTransacoesUtilService;
import br.com.marcoshssilva.desafiodev.services.TransacaoService;
import br.com.marcoshssilva.desafiodev.services.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @Autowired
    FileReaderTransacoesUtilService fileReaderTransacoesUtilService;

    @GetMapping()
    public ResponseEntity<List<Transacao>> getAllTransacoes() {
        List<Transacao> transacaos = this.transacaoService.getAll();
        return ResponseEntity.ok(transacaos);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadTransacoesByFile(@RequestParam(name = "file") MultipartFile multipartFile) throws IOException, ValidationException {
        List<Transacao> transacaos = this.fileReaderTransacoesUtilService.parseTransacoesFromFile(multipartFile.getResource().getFile());
        transacaos.forEach(this.transacaoService::save);
        return ResponseEntity.noContent().build();
    }

}
