package br.com.creathus.contato.domain.enums;

public enum Perfil {
	
	ADM(1, "Administrador"),
	FIN(2, "Financeiro"),
	COMP(3, "Compras"),
	COLB(4, "Colaborador");
	
	private int id;
	private String perfil;
	
	private Perfil(int id, String perfil) {
		this.id = id;
		this.perfil = perfil;
	}

	public int getId() {
		return id;
	}

	public String getPerfil() {
		return perfil;
	}
	
	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (Perfil x : Perfil.values()) {
			if (cod.equals(x.getId())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
