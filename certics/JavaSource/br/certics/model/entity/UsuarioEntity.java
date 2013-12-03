package br.certics.model.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "USUARIO")
@SequenceGenerator(name = "USR_ID", sequenceName = "USR_ID")
@NamedQueries({ 
	@NamedQuery(name = "UsuarioEntity.selectAll", query = "select obj from UsuarioEntity obj "), //
	@NamedQuery(name = "UsuarioEntity.selectCountByEmail", query = "select count(obj) from UsuarioEntity obj where obj.email like ? "), //
	@NamedQuery(name = "UsuarioEntity.selectUsuarioByEmailESenha", 
				query = "select obj from UsuarioEntity obj " +
						"inner join fetch obj.pessoaFisica pes " +
						"where obj.email like ? " +
						"and obj.senha like ?")
	})
public class UsuarioEntity extends Usuario {
	
}
