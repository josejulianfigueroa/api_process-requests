package cl.api.processrequests.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "tEncuestas")
public class EncuestaEntity {

    @Id
    private String id;

    @Email(message = "Debe ser un email valido")
    @NotBlank(message = "Debe informar el email")
    @NotEmpty(message = "Debe informar el email, es obligatorio")
    private String email;


    @NotBlank(message = "Debe informar el estilo de musica, es obligatorio")
    @NotEmpty(message = "Debe informar el estilo de musica, es obligatorio")
    @Pattern(regexp = "ROCK|POP|JAZZ|CLASICA|OTRO", message = "Los valores posibles para los estilos de musica son: " +
            "ROCK, POP, JAZZ, CLASICA U OTRO")
    private String estiloMusica;


    public String getId() {
        return id;
    }

    public EncuestaEntity setId(final String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EncuestaEntity setEmail(final String email) {
        this.email = email;
        return this;
    }

    public String getEstiloMusica() {
        return estiloMusica;
    }

    public EncuestaEntity setEstiloMusica(final String estiloMusica) {
        this.estiloMusica = estiloMusica;
        return this;
    }
}
