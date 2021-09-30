package cl.api.processrequests.dto;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tEncuestas")
public class EncuestaDto {

    private String id;

    private String email;

    private String estiloMusica;

    public String getId() {
        return id;
    }

    public EncuestaDto setId(final String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EncuestaDto setEmail(final String email) {
        this.email = email;
        return this;
    }

    public String getEstiloMusica() {
        return estiloMusica;
    }

    public EncuestaDto setEstiloMusica(final String estiloMusica) {
        this.estiloMusica = estiloMusica;
        return this;
    }
}
