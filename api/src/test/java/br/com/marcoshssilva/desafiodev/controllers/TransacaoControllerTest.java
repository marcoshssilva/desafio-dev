package br.com.marcoshssilva.desafiodev.controllers;

import br.com.marcoshssilva.desafiodev.entities.Transacao;
import br.com.marcoshssilva.desafiodev.models.LojaModelDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@WebMvcTest(TransacaoController.class)
public class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransacaoController transacaoController;

    MockMultipartFile fileValid = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO       ".getBytes());
    MockMultipartFile file422 = new MockMultipartFile("file", "error.pdf", MediaType.APPLICATION_PDF_VALUE, "Hello, World!".getBytes());

    @Test
    public void shouldExecutePerfectlyAndReturnStatus200() throws Exception {
        List<Transacao> transacaosMock = new ArrayList<>();
        Mockito
            .when(transacaoController.getAllTransacoes())
            .thenReturn(ResponseEntity.ok(transacaosMock));

        this.mockMvc
                .perform(
                        get("/transacoes"))
                .andExpect(
                        MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(
                        MockMvcResultMatchers.content().contentType("application/json")
                );
    }

    @Test
    public void shouldExecutePerfectlyAndReturnStatus200AndContent() throws Exception {
        List<LojaModelDto> lojasMock = new ArrayList<>();
        Mockito
                .when(transacaoController.getAllTransacoesResumedByLoja())
                .thenReturn(ResponseEntity.ok(lojasMock));

        this.mockMvc
                .perform(
                        get("/transacoes/lojas"))
                .andExpect(
                        MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(
                        MockMvcResultMatchers.content().contentType("application/json")
                );
    }

    @Test
    public void shouldExecutePerfectlyAndReturnStatusOk() throws Exception {
        Mockito.when(this.transacaoController.uploadTransacoesByFile(file422))
                .thenCallRealMethod();

        this.mockMvc
                .perform(
                        multipart("/transacoes/upload").file(fileValid))
                .andExpect(
                        MockMvcResultMatchers.status().is2xxSuccessful()
                );
    }

    @Test
    public void shouldNotAcceptFileAndReturnValidationErrorMessage() throws Exception {
        Mockito.when(this.transacaoController.uploadTransacoesByFile(file422))
                .thenCallRealMethod();

        this.mockMvc
                .perform(
                        multipart("/transacoes/upload").file(file422))
                .andExpect(
                        MockMvcResultMatchers.status().isUnprocessableEntity()
                );
    }
}
