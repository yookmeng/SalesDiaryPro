package com.SpringMVC.APIviewer;

import org.springframework.web.client.RestTemplate;

import com.SpringMVC.model.Model;

public class APIViewer {
	public static final String SERVER_URI = "http://localhost:8080/PersonalAssistant";
	
	public static void main(String args[]){
		testGetModel();
	}	

	private static void testGetModel() {
		RestTemplate restTemplate = new RestTemplate();
		Model model = restTemplate.getForObject(SERVER_URI+"/rest/model/1", Model.class);
		printModelData(model);
	}

	public static void printModelData(Model model){
		System.out.println("ID="+model.getmodelid()+",Name="+model.getmodelname());
	}
}
