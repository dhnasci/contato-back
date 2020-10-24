package br.com.creathus.contato.domain.enums;

public enum Sexo {
	
	MASCULINO(1, "Masculino"),
	FEMININO(2, "Feminino");
	
	private int cod;
	private String genero;
	
	private Sexo(int cod, String genero) {
		this.cod = cod;
		this.genero = genero;
	}

	public int getCod() {
		return cod;
	}

	public String getGenero() {
		return genero;
	}
	
	public static Sexo toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (Sexo x : Sexo.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
