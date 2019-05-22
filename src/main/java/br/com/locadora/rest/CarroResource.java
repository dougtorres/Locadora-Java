package br.com.locadora.rest;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.locadora.model.Carro;
import br.com.locadora.model.Marca;
import br.com.locadora.repository.CarroRepository;
import br.com.locadora.repository.MarcaRepository;

@RestController
public class CarroResource {
	
	@Autowired
	private CarroRepository cr;
	
	@Autowired
	private MarcaRepository mr;
	
	@GetMapping("api/carros")
	public ResponseEntity<Iterable<Carro>> listarCarros(){
		Iterable<Carro> carros = cr.findAll();
		
		return new ResponseEntity<Iterable<Carro>>(carros, HttpStatus.OK);
	}
	
	@PostMapping("api/cadastrarCarro")
	public ResponseEntity<HttpStatus> formCadastrarCarro(HttpServletRequest request) throws IOException {

		Marca novaMarca = new Marca();
		novaMarca.setNome(request.getParameter("marca"));
		mr.save(novaMarca);

		Carro novoCarro = new Carro();
		novoCarro.setMarca(novaMarca);
		novoCarro.setModelo(request.getParameter("modelo"));
		try {
			novoCarro.setPlaca(request.getParameter("placa"));
			cr.save(novoCarro);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/mensagem")
	public ResponseEntity<String> exibeMsg(){
		return new ResponseEntity<String>("Olá, estou perdida", HttpStatus.OK);
		}

}
