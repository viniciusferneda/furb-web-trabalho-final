package br.xereta.model.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "LOCALIZACAO")
@SequenceGenerator(name = "LOC_ID", sequenceName = "LOC_ID")
@NamedQueries({ @NamedQuery(name = "LocalizacaoEntity.selectAll", query = "select obj from LocalizacaoEntity obj ") })
public class LocalizacaoEntity extends Localizacao {
	
	private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public String getHorarioTexto() {
		return df.format(getHorario());
	}

}
