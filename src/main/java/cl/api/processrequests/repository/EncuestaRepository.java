package cl.api.processrequests.repository;

import cl.api.processrequests.exception.ResponseException;
import cl.api.processrequests.exception.StatusResponseEnum;
import cl.api.processrequests.pojo.EncuestaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EncuestaRepository {

    private static final String EXCEPTION = "Exception";

    @Autowired
    private final MongoOperations mongoOperations;

    public EncuestaRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public String  saveEncuesta(EncuestaEntity body) throws ResponseException {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(body.getEmail()));
            List<EncuestaEntity> lista = this.mongoOperations.find(query, EncuestaEntity.class);
            if (!lista.isEmpty()){
                return "Email ya existe en la base de datos";
            } else {
                this.mongoOperations.save(body);
                return "Registro ingresado exitosamente";
            }
        } catch (Exception ex) {
            throw new ResponseException(EXCEPTION, StatusResponseEnum.INTERNAL_SERVER_ERROR, true, "saveEncuesta");
        }
    }

    public List<EncuestaEntity> getEncuesta() throws ResponseException {
        try {
            List<EncuestaEntity> listaEncuestas = this.mongoOperations.find(new Query(), EncuestaEntity.class);
            return listaEncuestas;
        } catch (Exception ex) {
            throw new ResponseException(EXCEPTION, StatusResponseEnum.INTERNAL_SERVER_ERROR, true, "getEncuestas");
        }
    }

}
