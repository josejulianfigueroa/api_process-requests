package cl.api.processrequests.util;

import cl.api.processrequests.exception.ResponseException;
import cl.api.processrequests.exception.StatusResponseEnum;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceUtil {

    public static List<String[]> parametersFromUrl(String cadena) {
        List<String> lista = new ArrayList<>();
        List<String[]> param = new ArrayList<>();
        String[] a = cadena.split(",");
        Collections.addAll(lista, a);
        for (String s : lista) {
            param.add(s.split("="));
        }
        return param;
    }

    public static boolean isNumeric(String string) {
        return string.matches("^[-+]?\\d+(\\.\\d+)?$");
    }

    public static boolean specialCharacter(String value, String expresion) {
        Pattern pattern = Pattern.compile(expresion);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

    private ServiceUtil() {
        // No requiere inicializar variables.
    }

    /**
     * @param url del servicio Web provider
     * @return
     * @throws IOException
     * @throws ResponseException
     */
    public static JsonNode consumeRESTfulWebService(String url, HttpMethod httpMethod) throws IOException, ResponseException {
        JsonNode jsonNodeDoc;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.emptyList());
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, httpMethod, entity, String.class);
            ObjectMapper mapper = new ObjectMapper();
            jsonNodeDoc = mapper.readTree(response.getBody());

        } catch (IOException ex) {
            throw new ResponseException("IOException", StatusResponseEnum.INTERNAL_SERVER_ERROR, true, "consumeRESTfulWebService");
        } catch (Exception ex) {
            throw new ResponseException("Exception", StatusResponseEnum.INTERNAL_SERVER_ERROR, true, "consumeRESTfulWebService");
        }
        return jsonNodeDoc;
    }
}
