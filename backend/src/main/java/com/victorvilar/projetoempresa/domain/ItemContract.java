package com.victorvilar.projetoempresa.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.victorvilar.projetoempresa.enums.MeasurementUnit;
import jakarta.persistence.*;


@Entity
@Table(name = "itens_contract")
public class ItemContract implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Residue residue;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Equipment equipment;

	@Column(nullable = false)
	private Integer equipmentQuantity;
	
	//the quantity estimated for  year
	@Column(nullable = false, name="max_qtd_year")
	private Double qtdOfResidue;

	@Column(nullable=false)
	private BigDecimal itemValue;

	private String description;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="frequency_id")
	private CollectionFrequency collectionFrequency;

	private Integer measurementUnit;

	@ManyToOne
	@JoinColumn(name="contract_id", nullable=false)
	private Contract contract;

	public ItemContract() {
		
	}

	public ItemContract(Residue residue, Equipment equipment, double qtd, BigDecimal value, String description,Integer equipmentQuantity, CollectionFrequency collectionFrequency, MeasurementUnit measurementUnit) {
		this.residue = residue;
		this.equipment = equipment;
		this.qtdOfResidue = qtd;
		this.itemValue = value;
		this.description = description;
		this.equipmentQuantity = equipmentQuantity;
		this.collectionFrequency = collectionFrequency;
		this.measurementUnit = measurementUnit.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Contract getContract() {
		return this.contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Residue getResidue() {
		return residue;
	}

	public void setResidue(Residue residue) {
		this.residue = residue;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Double getQtdOfResidue() {
		return qtdOfResidue;
	}

	public void setQtdOfResidue(Double qtdOfResidue) {
		this.qtdOfResidue = qtdOfResidue;
	}

	public BigDecimal getItemValue() {
		return itemValue;
	}

	public void setItemValue(BigDecimal value) {
		this.itemValue = value;
	}

	public String getDescription() {return this.description;}

	public void setDescription(String description){this.description = description;}

	public CollectionFrequency getCollectionFrequency(){return this.collectionFrequency;}

	public void setCollectionFrequency(CollectionFrequency collectionFrequency){this.collectionFrequency = collectionFrequency;}

	public Integer getEquipmentQuantity() {
		return equipmentQuantity;
	}

	public void setEquipmentQuantity(Integer equipmentQuantity) {
		this.equipmentQuantity = equipmentQuantity;
	}

	public MeasurementUnit getMeasurementUnit() {
		return MeasurementUnit.getById(this.measurementUnit);
	}

	public void setMeasurementUnit(MeasurementUnit measurementUnit) {
		this.measurementUnit = measurementUnit.getId();
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("Item:\n");
		string.append("Tipo de Resíduo: " + this.residue.getType() + "\n");
		string.append("Equipamento: " + this.equipment.getEquipmentName()+ "\n");
		string.append("Valor Unitário: R$" + this.itemValue + "\n");
		string.append("Qtd Anual: " + this.qtdOfResidue + "\n");
		return string.toString();
	}




}
