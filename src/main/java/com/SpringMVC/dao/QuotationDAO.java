package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Quotation;
@Repository 
public interface QuotationDAO {
    public void save(Quotation quotation);

    public void update(Quotation quotation);
     
    public void delete(int quotationid);
     
    public Quotation get(int quotationid);
         
    public List<Quotation> list(int prospectid);    
}