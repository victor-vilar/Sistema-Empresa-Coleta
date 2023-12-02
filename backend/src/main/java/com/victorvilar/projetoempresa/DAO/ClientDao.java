package com.victorvilar.projetoempresa.DAO;

import com.victorvilar.projetoempresa.domain.Customer;

import jakarta.persistence.EntityManager;

import java.util.List;

/**
 * Class to manager Client in database
 * @since 2022-02-14
 * @author Victor Vilar
 */
@Deprecated
public class ClientDao {
    EntityManager em;

    public ClientDao(EntityManager em){
        this.em = em;

    }

    public void saveClient(Customer customer){
        this.em.getTransaction().begin();
        this.em.persist(customer);
        this.em.getTransaction().commit();
    }

    public List<Customer> getAllClients(){
        String sql = "SELECT c From Client c";
        List<Customer> customers = this.em.createQuery(sql, Customer.class).getResultList();
        return customers;
    }

	public Customer findClient(long l) {
		return this.em.find(Customer.class, l);
	}
	
	/**
	 * using criteria
	 * @param name
	 * @param id
	 * @return
	 */
	public Customer searchClientbyName(String name) {
		String jpql = "SELECT c FROM Client c where c.nameCompanyName = :name";
		return this.em.createQuery(jpql, Customer.class ).setParameter("name", name).getSingleResult();
	}

	public void updateClient(Customer cliente) {
		this.em.getTransaction().begin();
		this.em.merge(cliente);
		this.em.getTransaction().commit();
		
	}
    
   





}
