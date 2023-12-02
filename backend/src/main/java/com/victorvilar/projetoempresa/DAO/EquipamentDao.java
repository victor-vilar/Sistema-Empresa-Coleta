package com.victorvilar.projetoempresa.DAO;

import java.util.List;

import jakarta.persistence.EntityManager;

import com.victorvilar.projetoempresa.domain.Equipment;

/**
 * Class to manager Equipament in database
 * @since 2022-02-14
 * @author Victor Vilar
 */
@Deprecated
public class EquipamentDao {
    EntityManager em;

    public EquipamentDao(EntityManager em){
        this.em = em;

    }

    public void saveEquipament(Equipment equipment){
        this.em.getTransaction().begin();
        this.em.persist(equipment);
        this.em.getTransaction().commit();
    }

    public List<Equipment> getAllEquipaments(){
        String sql = "SELECT e From Equipament e";
        List<Equipment> equipment = this.em.createQuery(sql, Equipment.class).getResultList();
        return equipment;
    }

	public Equipment findEquipament(long l) {
		return this.em.find(Equipment.class, l);
	}

	public void updateEquipament(Equipment equipment) {
		this.em.getTransaction().begin();
		this.em.merge(equipment);
		this.em.getTransaction().commit();
		
	}
    
   





}
