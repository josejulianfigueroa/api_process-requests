package cl.api.processrequests.controller;

import cl.api.processrequests.dto.EncuestaDtoIn;
import cl.api.processrequests.exception.ErrorResponseJson;
import cl.api.processrequests.exception.ResponseException;
import cl.api.processrequests.dto.EncuestaDto;
import cl.api.processrequests.util.ResponseUtil;
import cl.api.processrequests.repository.EncuestaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ApiProcessRequestController {

    /**
     * The Constant Service Error.
     */
    public static final Logger LOG_ERROR = LoggerFactory.getLogger("error");

    @Autowired
    private EncuestaRepository encuestaRepository;

    @ControllerAdvice
    public static class ErrorHandler {

        @ExceptionHandler(ResponseException.class)
        public ResponseEntity<ErrorResponseJson> methodCatchResponseException(HttpServletRequest request,
                                                                              ResponseException e) {
            LOG_ERROR.error("Error info ", e);
            ErrorResponseJson errorInfo = new ErrorResponseJson(e);
            return new ResponseEntity<>(errorInfo,
                    HttpStatus.valueOf(Integer.parseInt(e.getStatusResponseEnum().getStatusCode())));
        }

        @ExceptionHandler(Exception.class)
        protected ResponseEntity<ErrorResponseJson> handleMethodArgumentNotValid(HttpServletRequest request,
                                                                                 MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach(error -> {
                String errorMessage = error.getDefaultMessage();
                errors.put("errorMessage", errorMessage);
            });
            ErrorResponseJson errorInfo = new ErrorResponseJson(errors.get("errorMessage"));
            return new ResponseEntity<>(errorInfo, HttpStatus.PRECONDITION_FAILED);
        }
    }

    /*
     * Obtener resultados de las encuestas
    */
    @GetMapping(value = "/process_requests/${info.version}/list/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public ModelAndView getlistEncuesta()
            throws ResponseException, IOException {
        List<EncuestaDto> response = encuestaRepository.getEncuesta();
        return ResponseUtil.genericResponseFind(new ObjectMapper().writeValueAsString(response));
    }

    /*
     * Guardar Encuesta
*/
    @PostMapping(value = "/process_requests/${info.version}/save/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public ModelAndView saveEncuesta(@Valid @RequestBody() EncuestaDtoIn body) throws ResponseException, JsonProcessingException {
        return ResponseUtil.genericResponseFind(new ObjectMapper().writeValueAsString(encuestaRepository.saveEncuesta(body)));
    }

}
