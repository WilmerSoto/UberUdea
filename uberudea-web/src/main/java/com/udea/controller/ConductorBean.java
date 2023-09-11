/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controller;

import com.udea.ejb.ConductorFacadeLocal;
import com.udea.entity.Conductor;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author Wilmer Soto
 */
public class ConductorBean {

    @EJB
    private ConductorFacadeLocal conductorFacade;

    public UIComponent myButton;
    private String cedula;
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String vehicule_details;
    private String license_plate;
    boolean disable = true;

    private List<Conductor> conductores;

    public List<Conductor> getConductores() {
        if ((conductores == null) || (conductores.isEmpty())) {
            refresh();
        }
        return conductores;
    }

    private void refresh() {
        conductores = conductorFacade.getAllConductores();
    }

    public ConductorFacadeLocal getConductorFacade() {
        return conductorFacade;
    }

    public void setConductorFacade(ConductorFacadeLocal ConductorFacade) {
        this.conductorFacade = ConductorFacade;
    }

    public UIComponent getMyButton() {
        return myButton;
    }

    public void setMyButton(UIComponent myButton) {
        this.myButton = myButton;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDisable() {
        return disable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVehicule_details() {
        return vehicule_details;
    }

    public void setVehicule_details(String vehicule_details) {
        this.vehicule_details = vehicule_details;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public ConductorBean() {
    }

    public String guardar() {
        Conductor c = new Conductor();
        c.setCedula(cedula);
        c.setName(name);
        c.setLastname(lastname);
        c.setEmail(email);
        c.setPhone(phone);
        c.setVehiculeDetails(vehicule_details);
        c.setLicensePlate(license_plate);
        this.conductorFacade.create(c);
        return "ConductorCreate";
    }

    public void validar() {
        if(conductorFacade.doesCedulaExist(cedula)){
            disable = true;
            FacesMessage message = new FacesMessage("CEDULA YA REGISTRADA");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(myButton.getClientId(context), message);
        }else{
            disable = false;
            FacesMessage message = new FacesMessage("CEDULA NO REGISTRADA");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(myButton.getClientId(context), message);
        }
        
    }

    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void changeLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }

}
