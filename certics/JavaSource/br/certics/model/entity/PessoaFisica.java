package br.certics.model.entity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.certics.model.enums.Sexo;
import br.finf.dao.entity.AbstractEntity;

@MappedSuperclass
@SuppressWarnings("serial")
public class PessoaFisica extends AbstractEntity{

	@Id
	@GeneratedValue(generator="PES_ID", strategy=GenerationType.IDENTITY)
	@Column(name="PES_ID", nullable=false)
	private Long id;
	
	@Column(name="PES_NOME", nullable=false)
	private String nome;
	
	@Column(name="PES_CPF", nullable=false)
	private String cpf;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PES_SEXO", nullable = false)
	private Sexo sexo;
	
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public boolean equals(Object obj) {
		if (this == obj)  
            return true;  
        if (obj == null)  
            return false;  
        if (!(obj instanceof PessoaFisica))  
            return false;  
        PessoaFisica other = (PessoaFisica) obj;  
        if (id == null){  
            if (other.id != null)  
                return false;  
        } else if (!id.equals(other.id))  
            return false;  
        return true;
	};
}
