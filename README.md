__api_process_requests__
====================

	Descripción de que es lo que hace el servicio. Una resumen de cual es la principal funcionalidad.
	Api para la carga de encuestas sobre los estilos de musica con BD en MongoDB.
- - -
	
Listado de servicios:

	1. ../process_requests/${info.version}/{lIdtplCabezaCotiza}/ GET : Obtiene todos los registros de la tabla tEncuestas.
	4. ../process_requests/${info.version}/save POST: Inserta registro en colección tEncuestas de MongoDB.


## Descripción archivo YML

- -  -
    mongodb: 
      host: ip de bd.
      port: puerto.
      database: nombre de base de datos MongoDB.
- -   -
## JSON's con entradas y salidas para cada servicio


#### POST http://localhost:8171/api/process_requests/v1/save/


JSON entrada: body:
    {
    "email": "josejulianfigueroawe2@gmail.com",
    "estiloMusica": "ROCK"
      }

Ejemplo Respuesta: 
        {
    "status": {
    "code": "200",
    "message": "Respuesta exitosa"
        },
    "result": true,
    "data": "Registro ingresado exitosamente"
        }
		


#### GET http://localhost:8171/api/process_requests/v1/list/
	
Ejemplo Respuesta:
	
	{
    "status": {
        "code": "200",
        "message": "Respuesta exitosa"
    },
    "result": true,
    "data": [
        {
            "id": "6155ee0689ad2f303a06f983",
            "email": "jose@gmail.com",
            "estiloMusica": "POP"
        },
        {
            "id": "6155ee69177ee00f98108d76",
            "email": "josejulianfigueroa@gmail.com",
            "estiloMusica": "ROCK"
        }
    ]
}
