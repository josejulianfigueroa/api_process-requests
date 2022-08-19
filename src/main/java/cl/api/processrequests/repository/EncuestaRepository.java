package cl.api.processrequests.repository;

import cl.api.processrequests.dto.EncuestaDtoIn;
import cl.api.processrequests.dto.UserData;
import cl.api.processrequests.exception.ResponseException;
import cl.api.processrequests.exception.StatusResponseEnum;
import cl.api.processrequests.dto.EncuestaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class EncuestaRepository {

    private static final String EXCEPTION = "Exception";

    @Autowired
    private RestTemplate clienteRest;

    @Autowired
    private final MongoOperations mongoOperations;

    public EncuestaRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public String  saveEncuesta(EncuestaDtoIn body) throws ResponseException {
        try {
            EncuestaDto bodyDocument = new EncuestaDto().setEmail(body.getEmail()).setEstiloMusica(body.getEstiloMusica());
            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(bodyDocument.getEmail()));
            List<EncuestaDto> lista = this.mongoOperations.find(query, EncuestaDto.class);
            if (!lista.isEmpty()){
                return "Email ya existe en la base de datos";
            } else {
                this.mongoOperations.save(bodyDocument);
                return "Registro ingresado exitosamente";
            }
        } catch (Exception ex) {
            throw new ResponseException(EXCEPTION, StatusResponseEnum.INTERNAL_SERVER_ERROR, true, "saveEncuesta");
        }
    }

    public List<EncuestaDto> getEncuesta() throws ResponseException {
        try {
            return this.mongoOperations.find(new Query(), EncuestaDto.class);
        } catch (Exception ex) {
            throw new ResponseException(EXCEPTION, StatusResponseEnum.INTERNAL_SERVER_ERROR, true, "getEncuestas");
        }
    }
    public List<UserData> getDataUserImpl() throws ResponseException {
        try {
            List<UserData> usuarios = Arrays.asList(clienteRest.getForObject("https://jsonplaceholder.typicode.com/todos", UserData[].class));

            // return usuarios.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
            return usuarios;
        } catch (Exception ex) {
            throw new ResponseException(EXCEPTION, StatusResponseEnum.INTERNAL_SERVER_ERROR, true, "getDataUser");
        }
    }
}