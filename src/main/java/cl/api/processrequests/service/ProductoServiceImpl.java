package cl.api.processrequests.service;

import cl.api.processrequests.dto.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	private RestTemplate clienteRest;
	
	@Override
	public List<UserData> findAll(){
			List<UserData> usuarios = Arrays.asList(clienteRest.getForObject("https://jsonplaceholder.typicode.com/todos", UserData[].class));
			// return usuarios.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
			return usuarios;
	}

}
