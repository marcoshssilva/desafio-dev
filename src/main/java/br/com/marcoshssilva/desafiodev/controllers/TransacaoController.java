package br.com.marcoshssilva.desafiodev.controllers;

import br.com.marcoshssilva.desafiodev.entities.Transacao;
import br.com.marcoshssilva.desafiodev.services.FileReaderTransacoesUtilService;
import br.com.marcoshssilva.desafiodev.services.TransacaoService;
import br.com.marcoshssilva.desafiodev.services.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

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
        Random random = new Random();
        // cria um arquivo temporario de nome aleatorio
        File f = File.createTempFile("upload-" + System.currentTimeMillis() + "-" +random.nextInt(), "txt");
        // sobrescreve o arquivo com o conteudo do upload
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(multipartFile.getBytes());
        fos.close();
        // faz o processamento do arquivo
        List<Transacao> transacaos = this.fileReaderTransacoesUtilService.parseTransacoesFromFile(f);
        // salva no banco de dados
        transacaos.forEach(this.transacaoService::save);
        // se tudo der ok, sera enviada resposta Status 201
        return ResponseEntity.noContent().build();
    }

}
