package br.com.locadora.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Carro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String modelo;
	@OneToOne(cascade=CascadeType.ALL)
	private Marca marca;
	private String placa;
	private boolean alugado;
	
	public Carro() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Marca getMarca() { 
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public String getPlaca() {

		return placa;
	}

	public void setPlaca(String placa) throws IOException {
		Pattern pattern = Pattern.compile("[A-Z]{3}\\d{4}");

		if (!pattern.matcher(placa).matches()) {
			throw new IllegalArgumentException("Placa Inv�lida");
		}
		this.placa = placa;
	}

	public boolean isAlugado() {
		return alugado;
	}

	public void setAlugado(boolean alugado) {
		this.alugado = alugado;
	}

}
