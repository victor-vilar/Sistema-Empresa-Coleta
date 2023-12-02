package com.victorvilar.projetoempresa.DAO;

import java.util.List;

import jakarta.persistence.EntityManager;

import com.victorvilar.projetoempresa.domain.Residue;

/**
 * Class to manager Residues in database
 * @since 2022-02-14
 * @author Victor Vilar
 */
@Deprecated
public class ResidueTypeDao {
    EntityManager em;

    public ResidueTypeDao(EntityManager em){
        this.em = em;

    }

    public void saveResidue(Residue residuo){
        this.em.getTransaction().begin();
        this.em.persist(residuo);
        this.em.getTransaction().commit();
    }

    public List<Residue> getAllResidues(){
        String sql = "SELECT r From ResidueType r";
        List<Residue> residues = this.em.createQuery(sql, Residue.class).getResultList();
        return residues;
    }

	public Residue findClient(long l) {
		return this.em.find(Residue.class, l);
	}

	public void updateClient(Residue residuo) {
		this.em.getTransaction().begin();
		this.em.merge(residuo);
		this.em.getTransaction().commit();
		
	}
    
   





}
