package br.com.financeiro.portfolio.domain.type;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.financeiro.portfolio.core.util.MessagePropertiesUtil;

public enum ErroHttpType {

    HTTP400 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.400");
        }

        @Override
        public String getErrorCode() {
            return "400";
        }
    },
    HTTP401 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.401");
        }

        @Override
        public String getErrorCode() {
            return "401";
        }
    },
    HTTP402 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.402");
        }

        @Override
        public String getErrorCode() {
            return "402";
        }
    },
    HTTP403 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.403");
        }

        @Override
        public String getErrorCode() {
            return "403";
        }
    },
    HTTP404 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.404");
        }

        @Override
        public String getErrorCode() {
            return "404";
        }
    },
    HTTP405 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.405");
        }

        @Override
        public String getErrorCode() {
            return "405";
        }
    },
    HTTP406 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.406");
        }

        @Override
        public String getErrorCode() {
            return "406";
        }
    },
    HTTP407 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.407");
        }

        @Override
        public String getErrorCode() {
            return "407";
        }
    },
    HTTP408 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.408");
        }

        @Override
        public String getErrorCode() {
            return "408";
        }
    },
    HTTP409 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.409");
        }

        @Override
        public String getErrorCode() {
            return "409";
        }
    },
    HTTP410 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.410");
        }

        @Override
        public String getErrorCode() {
            return "410";
        }
    },
    HTTP411 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.411");
        }

        @Override
        public String getErrorCode() {
            return "411";
        }
    },
    HTTP412 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.412");
        }

        @Override
        public String getErrorCode() {
            return "412";
        }
    },
    HTTP413 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.413");
        }

        @Override
        public String getErrorCode() {
            return "413";
        }
    },
    HTTP414 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.414");
        }

        @Override
        public String getErrorCode() {
            return "414";
        }
    },
    HTTP415 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.415");
        }

        @Override
        public String getErrorCode() {
            return "415";
        }
    },
    HTTP416 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.416");
        }

        @Override
        public String getErrorCode() {
            return "416";
        }
    },
    HTTP417 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.417");
        }

        @Override
        public String getErrorCode() {
            return "417";
        }
    },
    HTTP500 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.500");
        }

        @Override
        public String getErrorCode() {
            return "500";
        }
    },
    HTTP501 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.501");
        }

        @Override
        public String getErrorCode() {
            return "501";
        }
    },
    HTTP502 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.502");
        }

        @Override
        public String getErrorCode() {
            return "502";
        }
    },
    HTTP503 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.503");
        }

        @Override
        public String getErrorCode() {
            return "503";
        }
    },
    HTTP504 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.504");
        }

        @Override
        public String getErrorCode() {
            return "504";
        }
    },
    HTTP505 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.505");
        }

        @Override
        public String getErrorCode() {
            return "505";
        }
    },
    HTTP511 {
        @Override
        public String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil) {
            return messagePropertiesUtil.obterMensagemPor("error.http.511");
        }

        @Override
        public String getErrorCode() {
            return "511";
        }
    };

    private static final Map<String, ErroHttpType> ENUM_MAP;
    static {
        Map<String, ErroHttpType> map = new ConcurrentHashMap<String, ErroHttpType>();
        for (ErroHttpType httpError : ErroHttpType.values()) {
            map.put(httpError.getErrorCode(), httpError);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static ErroHttpType from(String httpErrorCode) {
        ErroHttpType httpError = null;
        if (httpErrorCode != null) {
            httpError = ENUM_MAP.get(httpErrorCode);
        }
        return httpError;
    }

    public static String getMessageBy(String httpErrorCode, MessagePropertiesUtil messagePropertiesUtil) {
        ErroHttpType httpError = from(httpErrorCode);
        return httpError != null ? httpError.getErrorMessage(messagePropertiesUtil) : "";
    }

    public abstract String getErrorCode();

    public abstract String getErrorMessage(MessagePropertiesUtil messagePropertiesUtil);

}