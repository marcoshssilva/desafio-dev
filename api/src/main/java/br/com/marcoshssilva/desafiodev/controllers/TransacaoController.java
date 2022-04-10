package br.com.marcoshssilva.desafiodev.controllers;

import br.com.marcoshssilva.desafiodev.entities.Transacao;
import br.com.marcoshssilva.desafiodev.models.LojaModelDto;
import br.com.marcoshssilva.desafiodev.services.FileReaderTransacoesUtilService;
import br.com.marcoshssilva.desafiodev.services.TransacaoService;
import br.com.marcoshssilva.desafiodev.services.exceptions.ValidationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Api("Transacoes")
@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @Autowired
    FileReaderTransacoesUtilService fileReaderTransacoesUtilService;

    public TransacaoController(TransacaoService transacaoService, FileReaderTransacoesUtilService fileReaderTransacoesUtilService) {
        this.transacaoService = transacaoService;
        this.fileReaderTransacoesUtilService = fileReaderTransacoesUtilService;
    }

    @ApiOperation("Retorna uma lista de transacoes")
    @ApiResponses(
            @ApiResponse(code = 200, message = "OK")
    )
    @GetMapping()
    public ResponseEntity<List<Transacao>> getAllTransacoes() {
        List<Transacao> transacaos = this.transacaoService.getAll();
        return ResponseEntity.ok(transacaos);
    }

    @ApiOperation("Retorna uma lista de lojas com suas correspondentes transacoes e saldo")
    @GetMapping("/lojas")
    public ResponseEntity<List<LojaModelDto>> getAllTransacoesResumedByLoja() {
        List<LojaModelDto> lojas = this.transacaoService.getAllResumedByLoja();
        return ResponseEntity.ok(lojas);
    }

    @ApiOperation(code = 204, value = "Le e salva as transações de um arquivo TXT recebido.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Todas as transações foram lidas e salvas com sucesso."),
            @ApiResponse(code = 422, message = "Houve erro na validação de algum dado do arquivo.")
    })
    @PostMapping("/upload")
    public ResponseEntity<?> uploadTransacoesByFile(@RequestParam(name = "file") MultipartFile multipartFile) throws IOException, ValidationException {

        if (!Objects.equals(multipartFile.getContentType(), "text/plain")) {
            throw new ValidationException("Arquivo não é um formato válido.");
        }

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
        // se tudo der ok, sera enviada resposta Status 204
        return ResponseEntity.noContent().build();
    }

    // getters e setters

    public TransacaoService getTransacaoService() {
        return transacaoService;
    }

    public void setTransacaoService(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    public FileReaderTransacoesUtilService getFileReaderTransacoesUtilService() {
        return fileReaderTransacoesUtilService;
    }

    public void setFileReaderTransacoesUtilService(FileReaderTransacoesUtilService fileReaderTransacoesUtilService) {
        this.fileReaderTransacoesUtilService = fileReaderTransacoesUtilService;
    }
}
