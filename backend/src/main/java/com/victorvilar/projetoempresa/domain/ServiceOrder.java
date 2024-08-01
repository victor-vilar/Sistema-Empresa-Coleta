package com.victorvilar.projetoempresa.domain;

import com.victorvilar.projetoempresa.enums.ServiceOrderStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "service_order")
public class ServiceOrder implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate emissionDate = LocalDate.now();

    private LocalDate serviceExpectedDate;

    private LocalDate serviceExecutedDate;

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name="item_id",nullable = false)
    private ItemContract itemContract;

    @ManyToOne
    @JoinColumn(name="customer_id",nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="address_id",nullable = false)
    private Address address;


    private Long amountCollected;

    private String ineaManifest;

    private LocalTime serviceTime;

    private String observation;

    /**
     * place in the filesystem where the scan of the physical copy is stored.
     * This url file must have the already executed and filled service order.
     */
    private String osFileUrl;

    private Integer serviceOrderStatus = ServiceOrderStatus.UNDONE.getId();

    public ServiceOrder() {
    }

    public ServiceOrder(Long id, LocalDate emissionDate, LocalDate serviceExpectedDate, LocalDate serviceExecutedDate, Vehicle vehicle, ItemContract itemContract, Customer customer, Address address, Long amountCollected, String ineaManifest, LocalTime serviceTime, String observation, String osFileUrl, Integer serviceOrderStatus) {
        this.id = id;
        this.emissionDate = emissionDate;
        this.serviceExpectedDate = serviceExpectedDate;
        this.serviceExecutedDate = serviceExecutedDate;
        this.vehicle = vehicle;
        this.itemContract = itemContract;
        this.customer = customer;
        this.address = address;
        this.amountCollected = amountCollected;
        this.ineaManifest = ineaManifest;
        this.serviceTime = serviceTime;
        this.observation = observation;
        this.osFileUrl = osFileUrl;
        this.serviceOrderStatus = serviceOrderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(LocalDate localDate){this.emissionDate = localDate;}

    public LocalDate getServiceExpectedDate() {
        return serviceExpectedDate;
    }

    public void setServiceExpectedDate(LocalDate serviceExpectedDate) {
        this.serviceExpectedDate = serviceExpectedDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getServiceExecutedDate() {
        return serviceExecutedDate;
    }

    public void setServiceExecutedDate(LocalDate serviceExecutedDate) {
        this.serviceExecutedDate = serviceExecutedDate;
    }

    public ItemContract getItemContract() {
        return itemContract;
    }

    public void setItemContract(ItemContract itemContract) {
        this.itemContract = itemContract;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getIneaManifest() {
        return ineaManifest;
    }

    public void setIneaManifest(String ineaManifest) {
        this.ineaManifest = ineaManifest;
    }

    public LocalTime getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(LocalTime serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getOsFileUrl() {
        return osFileUrl;
    }

    public void setOsFileUrl(String osFileUrl) {
        this.osFileUrl = osFileUrl;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getAmountCollected() {
        return amountCollected;
    }

    public void setAmountCollected(Long amountCollected) {
        this.amountCollected = amountCollected;
    }

    public ServiceOrderStatus getServiceOrderStatus() {
        return ServiceOrderStatus.getById(this.serviceOrderStatus);
    }

    public void setServiceOrderStatus(ServiceOrderStatus serviceOrderStatus) {
        this.serviceOrderStatus = serviceOrderStatus.getId();
    }

    public static ServiceOrderBuilder builder(){
        return new ServiceOrderBuilder();
    }

    public static class ServiceOrderBuilder{

        private Long id;
        private LocalDate emissionDate;
        private LocalDate serviceExpectedDate;
        private LocalDate serviceExecutedDate;
        private Vehicle vehicle;
        private ItemContract itemContract;
        private Customer customer;
        private String ineaManifest;
        private LocalTime serviceTime;
        private String observation;
        private String osFileUrl;
        private Address address;
        private Long amountCollected;


        public ServiceOrderBuilder id(Long id){
            this.id = id;
            return this;
        }

        public ServiceOrderBuilder emissionDate(LocalDate date){
            this.emissionDate=date;
            return this;
        }
        public ServiceOrderBuilder serviceExpectedDate(LocalDate date){
            this.serviceExpectedDate=date;
            return this;
        }

        public ServiceOrderBuilder serviceExecutedDate(LocalDate date){
            this.serviceExecutedDate = date;
            return this;
        }

        public ServiceOrderBuilder vehicle(Vehicle vehicle){
            this.vehicle = vehicle;
            return this;
        }
        public ServiceOrderBuilder itemContract(ItemContract item){
            this.itemContract = item;
            return this;
        }
        public ServiceOrderBuilder customer(Customer customer){
            this.customer = customer;
            return this;
        }
        public ServiceOrderBuilder ineaManifest(String ineaManifest){
            this.ineaManifest = ineaManifest;
            return this;
        }
        public ServiceOrderBuilder serviceTime(LocalTime serviceTime){
            this.serviceTime =serviceTime;
            return this;
        }
        public ServiceOrderBuilder observation(String observation){
            this.observation = observation;
            return this;
        }
        public ServiceOrderBuilder osFileUrl(String osFileUrl){
            this.osFileUrl = osFileUrl;
            return this;
        }
        public ServiceOrderBuilder address(Address address){
            this.address = address;
            return this;
        }
        public ServiceOrderBuilder amountCollected(Long amount){
            this.amountCollected = amount;
            return this;
        }

        public ServiceOrder build(){
            ServiceOrder order = new ServiceOrder();
            order.setId(this.id);
            order.setEmissionDate(this.emissionDate);
            order.setServiceExpectedDate(this.serviceExpectedDate);
            order.setServiceExecutedDate(this.serviceExecutedDate);
            order.setVehicle(this.vehicle);
            order.setItemContract(this.itemContract);
            order.setCustomer(this.customer);
            order.setIneaManifest(this.ineaManifest);
            order.setServiceTime(this.serviceTime);
            order.setObservation(this.observation);
            order.setOsFileUrl(this.osFileUrl);
            order.setAddress(this.address);
            order.setAmountCollected(this.amountCollected);
            return order;
        }
    }
}
