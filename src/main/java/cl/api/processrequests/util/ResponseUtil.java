package cl.api.processrequests.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

public class ResponseUtil {
	
	/** The Constant Infor del Servicio. */
	public  static final Logger LOG_SERVICE = LoggerFactory.getLogger("restservice");
	private static final String RETORNO = "retorno";
	private static final String MENSAJE = "message";
	private static final String CODE = "code";
	private static final String RESULT = "result";
	private static final String RESPUESTA = "Respuesta exitosa";
	
	public static ModelAndView genericResponseFind(String data) {
		ModelAndView model = new ModelAndView("responseFind");
		model.addObject("data", data);
        model.addObject(CODE, "200");
        model.addObject(MENSAJE, RESPUESTA);
        model.addObject(RESULT, "true");
        return model;
	}

	public static ModelAndView genericResponse(String data) {
		ModelAndView model = new ModelAndView("responseGeneric");
		model.addObject(RETORNO, data);
        model.addObject(CODE, "200");
        model.addObject(MENSAJE, RESPUESTA);
        model.addObject(RESULT, "true");
		return model;
	}
	public static ModelAndView genericResponsePostPut() {
		ModelAndView model = new ModelAndView("responseGeneric");
        model.addObject(CODE, "201");
        model.addObject(MENSAJE, RESPUESTA);
        model.addObject(RESULT, "true");
		return model;
	}
	public static ModelAndView genericResponseNotify(JsonNode body) {
		ModelAndView model = new ModelAndView("responseFind");
		model.addObject("data", body);
        model.addObject(CODE, "200");
        model.addObject(MENSAJE, RESPUESTA);
        model.addObject(RESULT, "true");
        return model;
	}
	private ResponseUtil() {

	}
}
