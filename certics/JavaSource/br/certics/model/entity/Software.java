package br.certics.model.entity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.finf.dao.entity.AbstractEntity;

@MappedSuperclass
@SuppressWarnings("serial")
public class Software extends AbstractEntity{

	@Id
	@GeneratedValue(generator = "SOF_ID", strategy = GenerationType.IDENTITY)
	@Column(name="SOF_ID", nullable=false)
	private Long id;
	
	@Column(name="SOF_NOME", nullable=false)
	private String nome;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=OrganizacaoSolicitanteEntity.class)
	@JoinColumn(name="SOF_OSOID", nullable=false)
	private OrganizacaoSolicitanteEntity organizacaoSolicitante;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public OrganizacaoSolicitanteEntity getOrganizacaoSolicitante() {
		return organizacaoSolicitante;
	}

	public void setOrganizacaoSolicitante(OrganizacaoSolicitanteEntity organizacaoSolicitante) {
		this.organizacaoSolicitante = organizacaoSolicitante;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)  
            return true;  
        if (obj == null)  
            return false;  
        if (!(obj instanceof Software))  
            return false;  
        Software other = (Software) obj;  
        if (id == null){  
            if (other.id != null)  
                return false;  
        } else if (!id.equals(other.id))  
            return false;  
        return true;
	};

}
