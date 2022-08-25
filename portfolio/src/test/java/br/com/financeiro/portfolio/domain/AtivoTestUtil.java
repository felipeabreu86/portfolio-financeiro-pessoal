package br.com.financeiro.portfolio.domain;

public class AtivoTestUtil {

    public static String[] codigosInvalidosParaAcoes = { "ABCD", "12", "123", "1245", "ABC1", "ABCDAS", "ABCD12",
            "ABCD12", "ABCD!3", "@BCD12", "AB D12", "ABCD0", "ABCD1", "ABCD2", "ABCD9", "ABCD10", "ABCD12" };

    public static String[] codigosInvalidosParaFundosImobiliarios = { "ABCD", "12", "123", "1245", "ABC1", "ABCDAS",
            "ABCD123", "ABCD12$", "ABCD!3", "@BCD12", "AB D12", "ABCD0", "ABCD1", "ABCD2", "ABCD3", "ABCD4", "ABCD5",
            "ABCD6", "ABCD7", "ABCD8", "ABCD9", "ABCD10", "ABCD12" };

    public static String[] codigosInvalidosParaAtivosNoExterior = { "", " ", "1", "123", "12345", "abcde", "@!#$",
            "abc$", "ãbc" };

}
