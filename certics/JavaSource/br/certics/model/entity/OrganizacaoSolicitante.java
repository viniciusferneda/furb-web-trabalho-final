package br.certics.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import br.finf.dao.entity.AbstractEntity;

@MappedSuperclass
@SuppressWarnings("serial")
public class OrganizacaoSolicitante extends AbstractEntity{

	@Id
	@GeneratedValue(generator = "OSO_ID", strategy = GenerationType.IDENTITY)
	@Column(name="OSO_ID", nullable=false)
	private Long id;
	
	@Column(name="OSO_NOME", nullable=false)
	private String nome;
	
	@Column(name="OSO_CNPJ", nullable=false)
	private String cnpj;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="organizacaoSolicitante", targetEntity=SoftwareEntity.class)
	private List<SoftwareEntity> software;
	
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<SoftwareEntity> getSoftware() {
		return software;
	}

	public void setSoftware(List<SoftwareEntity> software) {
		this.software = software;
	}

	public boolean equals(Object obj) {
		if (this == obj)  
            return true;  
        if (obj == null)  
            return false;  
        if (!(obj instanceof OrganizacaoSolicitante))  
            return false;  
        OrganizacaoSolicitante other = (OrganizacaoSolicitante) obj;  
        if (id == null){  
            if (other.id != null)  
                return false;  
        } else if (!id.equals(other.id))  
            return false;  
        return true;
	};

}
