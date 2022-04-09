package br.com.marcoshssilva.desafiodev.services;

import br.com.marcoshssilva.desafiodev.services.exceptions.ValidationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileReaderTransacoesUtilServiceTest {

    FileReaderTransacoesUtilService fileReaderTransacoesUtilService;
    File file;
    FileOutputStream fos;

    @BeforeEach
    void setup() throws IOException {
        file = new File("test.txt");
        file.createNewFile();
        file.deleteOnExit();
        fos = new FileOutputStream(file);

        this.fileReaderTransacoesUtilService = new FileReaderTransacoesUtilService();
    }

    @Test
    void shouldRunOk() {
        Assertions.assertDoesNotThrow( () -> {
            final String VALID_TRANSACAO = "3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO       ";
            this.fos.write(VALID_TRANSACAO.getBytes());
            this.fileReaderTransacoesUtilService.parseTransacoesFromFile(file);
        });
    }

    @Test
    void shouldPrintValidationErrorInDate() {
        Assertions.assertThrows(ValidationException.class,
                () -> {
                    final String INVALID_DATE_TRANSACAO = "5201960010000013200556418150633123****7687145607MARIA JOSEFINALOJA DO Ó - MATRIZ";
                    this.fos.write(INVALID_DATE_TRANSACAO.getBytes());
                    this.fileReaderTransacoesUtilService.parseTransacoesFromFile(file);
                });
    }

    @Test
    void shouldPrintValidationErrorInTime() {
        Assertions.assertThrows(ValidationException.class,
                () -> {
                    final String INVALID_TIME_TRANSACAO = "3201903010000012200845152540736777****1313179912MARCOS PEREIRAMERCADO DA AVENIDA";
                    this.fos.write(INVALID_TIME_TRANSACAO.getBytes());
                    this.fileReaderTransacoesUtilService.parseTransacoesFromFile(file);
                });
    }

    @Test
    void shouldPrintValidationErrorInLength() {
        Assertions.assertThrows(ValidationException.class,
                () -> {
                    final String NO_LENGTH_TRANSACAO = "2201903010000011200096206760173648****0099234234JOÃO MACEDOBAR DO JOÃO";
                    this.fos.write(NO_LENGTH_TRANSACAO.getBytes());
                    this.fileReaderTransacoesUtilService.parseTransacoesFromFile(file);
                });
    }

    void shouldPrintValidationErrorInCartao() {
        Assertions.assertThrows(ValidationException.class,
                () -> {
                    final String INVALID_CARD = "32019030100000142000962067601747c3*e**3a53153453JOÃO MACEDO   BAR DO JOÃO       ";
                    this.fos.write(INVALID_CARD.getBytes());
                    this.fileReaderTransacoesUtilService.parseTransacoesFromFile(file);
                });
    }

    void shouldPrintValidationErrorInCpf() {
        Assertions.assertThrows(ValidationException.class,
                () -> {
                    final String VALID_TRANSACAO = "3201903010000014200abcdefghijk4753****3153153453JOÃO MACEDO   BAR DO JOÃO       ";
                    this.fos.write(VALID_TRANSACAO.getBytes());
                    this.fileReaderTransacoesUtilService.parseTransacoesFromFile(file);
                });
    }

    @Test
    void shouldPrintValidationErrorInTipo() {
        Assertions.assertThrows(ValidationException.class,
                () -> {
                    final String INVALID_TIPO_TRANSACAO = "0201903010000011200096206760173648****0099234234JOÃO MACEDO   BAR DO JOÃO       ";
                    this.fos.write(INVALID_TIPO_TRANSACAO.getBytes());
                    this.fileReaderTransacoesUtilService.parseTransacoesFromFile(file);
                });
    }
}
