package cl.api.processrequests.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EncuestaDtoIn {

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

    public EncuestaDtoIn setId(final String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EncuestaDtoIn setEmail(final String email) {
        this.email = email;
        return this;
    }

    public String getEstiloMusica() {
        return estiloMusica;
    }

    public EncuestaDtoIn setEstiloMusica(final String estiloMusica) {
        this.estiloMusica = estiloMusica;
        return this;
    }
}
