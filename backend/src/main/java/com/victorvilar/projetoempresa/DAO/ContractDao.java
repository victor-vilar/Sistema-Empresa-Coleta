package com.victorvilar.projetoempresa.DAO;

import java.util.List;

import jakarta.persistence.EntityManager;

import com.victorvilar.projetoempresa.domain.Contract;

/**
 * Class to manager Contract in database
 * @since 2022-02-14
 * @author Victor Vilar
 */
@Deprecated
public class ContractDao {
    EntityManager em;

    public ContractDao(EntityManager em){
        this.em = em;

    }

    public void saveContract(Contract contract){
        this.em.getTransaction().begin();
        this.em.persist(contract);
        this.em.getTransaction().commit();
    }

    public List<Contract> getAllContracts(){
        String sql = "SELECT c From Contract c";
        List<Contract> contracts = this.em.createQuery(sql,Contract.class).getResultList();
        return contracts;
    }

	public Contract findContract(long l) {
		return this.em.find(Contract.class, l);
	}

	public void updateContract(Contract contract) {
		this.em.getTransaction().begin();
		this.em.merge(contract);
		this.em.getTransaction().commit();
		
	}
	/**
	 * Find a contract with its client.
	 * @param l id from contract
	 * @return contract with his client
	 */
	public Contract findContractWithClient(Long l) {
		String jpql = "SELECT c from Contract c JOIN FETCH c.client WHERE c.id = :id";
		return this.em.createQuery(jpql,Contract.class).setParameter("id", l).getSingleResult();
	}
	
	
    
   





}
