package br.xereta.model.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "COMPUTADOR")
@SequenceGenerator(name = "CMP_ID", sequenceName = "CMP_ID")
@NamedQueries({ 
	@NamedQuery(name = "ComputadorEntity.selectAll", query = "select obj from ComputadorEntity obj "), //
	@NamedQuery(name = "ComputadorEntity.getAllComputadorByUsuarioId", query = "select obj from ComputadorEntity obj where obj.usuario = ? "), //
	@NamedQuery(name = "ComputadorEntity.getComputadorById", query = "select obj from ComputadorEntity obj where obj.id = ? "), //
	@NamedQuery(name = "ComputadorEntity.getComputadorByMac", query = "select obj from ComputadorEntity obj where obj.mac like ? ")
	})
public class ComputadorEntity extends Computador {
	
}
