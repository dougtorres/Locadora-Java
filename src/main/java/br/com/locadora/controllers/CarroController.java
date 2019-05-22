package br.com.locadora.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.locadora.model.Carro;
import br.com.locadora.model.Marca;
import br.com.locadora.repository.CarroRepository;
import br.com.locadora.repository.MarcaRepository;

@RestController
public class CarroController {

	@Autowired
	private CarroRepository cr;

	@Autowired
	private MarcaRepository mr;

	@RequestMapping(value = "/cadastrarCarro", method = RequestMethod.GET)
	public String formCadastrarCarro() {
		return "carro/cadastrarCarro";
	}

	@RequestMapping(value = "/cadastrarCarro", method = RequestMethod.POST)
	public String formCadastrarCarro(HttpServletRequest request) throws IOException {

		Marca novaMarca = new Marca();
		novaMarca.setNome(request.getParameter("marca"));
		mr.save(novaMarca);

		Carro novoCarro = new Carro();
		novoCarro.setMarca(novaMarca);
		novoCarro.setModelo(request.getParameter("modelo"));
		try {
			novoCarro.setPlaca(request.getParameter("placa"));
			cr.save(novoCarro);
		} catch (Exception e) {

		}
		return "redirect:/cadastrarCarro";
	}

	@RequestMapping(value = "/alterarCarro", method = RequestMethod.GET)
	public String formAlterarCarro() {
		return "carro/alterarCarro";
	}

	@RequestMapping(value = "/carros")
	public ModelAndView listaCarros() {
		
		RestTemplate restTemplate = new RestTemplate();
		Iterable<Carro> carros = restTemplate.getForObject("http://localhost:8080/api/carros", Iterable.class);
		ModelAndView mv = new ModelAndView("carro/listaCarros");
		mv.addObject("carros", carros);
		return mv;
		
	}

}
