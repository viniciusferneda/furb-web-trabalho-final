package br.xereta.model.entity;

import java.util.Date;

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
public abstract class Localizacao extends AbstractEntity {

	@Id
	@GeneratedValue(generator = "LOC_ID", strategy = GenerationType.IDENTITY)
	@Column(name = "LOC_ID", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = ComputadorEntity.class)
	@JoinColumn(name = "LOC_COMPUTADOR", nullable = false)
	private ComputadorEntity computador;

	@Column(name = "LOC_LATITUDE", nullable = false)
	private Double latitude;

	@Column(name = "LOC_LONGITUDE", nullable = false)
	private Double longitude;

	@Column(name = "LOC_HORARIO", nullable = false)
	private Date horario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ComputadorEntity getComputador() {
		return computador;
	}

	public void setComputador(ComputadorEntity computador) {
		this.computador = computador;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

}
