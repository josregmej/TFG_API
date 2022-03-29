package com.tfg.sotocafe.json;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteRest extends UserRest{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Boolean tieneIVA;

	public ClienteRest(Long id, Boolean tieneIva) {
		super();
		this.id = id;
		this.tieneIVA = tieneIva;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getTieneIVA() {
		return tieneIVA;
	}
	public void setTieneIVA(Boolean tieneIVA) {
		this.tieneIVA = tieneIVA;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteRest other = (ClienteRest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ClienteRest [id=" + id + ", tieneIVA=" + tieneIVA + "]";
	}

}
