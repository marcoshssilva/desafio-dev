package br.com.marcoshssilva.desafiodev.services;

import br.com.marcoshssilva.desafiodev.entities.Transacao;
import br.com.marcoshssilva.desafiodev.entities.enums.TipoTransacao;
import br.com.marcoshssilva.desafiodev.services.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class FileReaderTransacoesUtilService {

    Pattern cartaoPattern = Pattern.compile("\\d{4}[*]{4}\\d{4}");
    Pattern cpfPattern    = Pattern.compile("\\d{11}");

    public FileReaderTransacoesUtilService(){
        super();
    }

    public List<Transacao> parseTransacoesFromFile(File file) throws FileNotFoundException, ValidationException {
        Scanner scanner = new Scanner(file);
        List<Transacao> transacaos = new LinkedList<>();
        int numberLine = 0;

        while (scanner.hasNextLine()) {
            numberLine++;
            // linha dos dados transacao
            String transacaoString = scanner.nextLine();
            // caso não seja válido
            try {
                checkValidityTransacaoString(transacaoString);
            } catch (ValidationException e) {
                throw new ValidationException("Erro em linha: " + numberLine + " " + e.getMessage());
            }

            // valor deve ser divido por 100
            Double valor = Double.parseDouble(transacaoString.substring(9, 19)) / 100;

            String data = transacaoString.substring(1,9);
            String hora = transacaoString.substring(42, 48);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");
            LocalDateTime localDateTime = LocalDateTime.parse(data + " " + hora, dtf);

            String donoLoja = transacaoString.substring(48, 62).strip();
            String nomeLoja = transacaoString.substring(62, 80).strip();
            String cpf = transacaoString.substring(19, 30);
            String cartao = transacaoString.substring(30, 42);

            // ainda deve ser procurado pela Enum TipoTransacao
            int codeTipoTransacao = Integer.parseInt(transacaoString.substring(0,1));

            TipoTransacao tipoTransacao = Stream.of(TipoTransacao.values())
                    .filter(
                            tipo -> tipo.getCod() == codeTipoTransacao)
                    .findFirst()
                    .orElseThrow(RuntimeException::new);

            Transacao transacao = new Transacao();

            transacao.setData(localDateTime);
            transacao.setTipo(tipoTransacao);
            transacao.setValor(valor);

            transacao.setCpf(cpf);
            transacao.setCartao(cartao);
            transacao.setDonoLoja(donoLoja);
            transacao.setNomeLoja(nomeLoja);

            transacaos.add(transacao);
        }

        return transacaos;
    }

    /**
     * Qualquer linha que seja convertida deve ser validada de acordo com este padrão
     *
     * ÍNICIO
     * 1 dígito CÓDIGO TIPO TRANSAÇÃO
     * 8 dígitos numéricos -> DATA DA TRANSAÇÃO
     * 10 dígitos numéricos -> VALOR INTEIRO(2 últimos dígitos para centavos)
     * 11 dígitos numéricos -> CPF DO BENEFICIARIO
     * 12 dígitos numéricos || asterisco(*) -> CARTÃO UTILIZADO NA TRANSAÇÃO
     * 6 dígitos numéricos -> HORÁRIO DA TRANSAÇÃO
     * 14 dígitos alfa-numéricos e espaço -> DONO DA LOJA, pode conter espaços em branco
     * 19 dígitos alfa-numéricos e espaço -> NOME DA LOJA, pode conter espaços em branco
     * FIM
     *
     * TOTAL 80 dígitos
     */
    private void checkValidityTransacaoString(final String transacaoString) throws ValidationException {
        // valida comprimento
        final int TOTAL_QUE_DEVE_POSSUIR = 80;
        if (transacaoString.length() != TOTAL_QUE_DEVE_POSSUIR)
            throw new ValidationException("LENGTH invalido.");

        // valida tipo da transacao
        try {
            // código tipo transacao convertido em Integer
            int codeTipoTransacao = Integer.parseInt(transacaoString.substring(0, 1));
            // verifica se existem este código na enum.
            Stream.of(TipoTransacao.values())
                    .filter(tipo -> tipo.getCod() == codeTipoTransacao)
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
        } catch (Exception e) {
            throw new ValidationException("TIPO invalido.");
        }

        // valida valor
        try {
            // se puder converter para Integer então é valido
            Integer.valueOf(transacaoString.substring(9, 19));
        } catch (Exception e) {
            throw new ValidationException("VALOR invalido.");
        }

        // valida data
        try {
            String data = transacaoString.substring(1,9);
            LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e) {
            throw new ValidationException("DATA invalido.");
        }

        // valida hora
        try {
            String hora = transacaoString.substring(42, 48);
            LocalTime.parse(hora, DateTimeFormatter.ofPattern("HHmmss"));
        } catch (Exception e) {
            throw new ValidationException("HORA invalido.");
        }

        // valida cpf
        try {
            String cpf = transacaoString.substring(19, 30);
            Matcher matcherCpf = this.cpfPattern.matcher(cpf);
            if (!matcherCpf.find()) {
                throw new ValidationException("CPF invalido.");
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }

        // valida cartao
        try {
            String cartao = transacaoString.substring(30, 42);
            Matcher matcherCartao = this.cartaoPattern.matcher(cartao);

            if (!matcherCartao.find()) {
                throw new ValidationException("VALOR invalido.");
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
        /*
        Não necessário validação por aqui.
        String donoLoja = transacaoString.substring(48, 62);
        String nomeLoja = transacaoString.substring(62, 80);
         */
    }

}
