package br.com.financeiro.portfolio.domain.type;

import java.util.Objects;

import br.com.financeiro.portfolio.domain.entity.Ativo;

public enum AtivoType {

    ACAO {
        @Override
        public Ativo obterInstancia(String ticker) {
            return new Ativo(ACAO, validar(ticker));
        }

        @Override
        public String validar(String ticker) {
            if (!Objects.requireNonNull(ticker).matches("^[A-Za-z]{4}(3|4|5|6|7|8|11)$")) {
                throw new IllegalArgumentException("Formato inválido para o código da Ação: " + ticker);
            }
            return ticker;
        }
    },
    FUNDO_IMOBILIARIO {
        @Override
        public Ativo obterInstancia(String ticker) {
            return new Ativo(FUNDO_IMOBILIARIO, validar(ticker));
        }

        @Override
        public String validar(String ticker) {
            if (!Objects.requireNonNull(ticker).matches("^[A-Za-z]{4}11$")) {
                throw new IllegalArgumentException("Formato inválido para o código do Fundo Imobiliário: " + ticker);
            }
            return ticker;
        }
    },
    STOCK {
        @Override
        public Ativo obterInstancia(String ticker) {
            return new Ativo(STOCK, validar(ticker));
        }

        @Override
        public String validar(String ticker) {
            if (!Objects.requireNonNull(ticker).matches("[A-Za-z]{1,4}")) {
                throw new IllegalArgumentException("Formato inválido para o código do Stock: " + ticker);
            }
            return ticker;
        }
    },
    REIT {
        @Override
        public Ativo obterInstancia(String ticker) {
            return new Ativo(REIT, validar(ticker));
        }

        @Override
        public String validar(String ticker) {
            if (!Objects.requireNonNull(ticker).matches("[A-Za-z]{1,4}")) {
                throw new IllegalArgumentException("Formato inválido para o código do Reit: " + ticker);
            }
            return ticker;
        }
    };

    public abstract Ativo obterInstancia(String ticker);

    public abstract String validar(String ticker);

}