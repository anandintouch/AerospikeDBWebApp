package com.aerospike.db.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;

import com.aerospike.client.AerospikeException;
import com.aerospike.db.model.AerospikeDBService;
import com.aerospike.db.model.Record;
import com.aerospike.db.validator.RecordDataValidator;

/**
 * This is a controller class of spring mvc which is exposed as a REST API used to create Record in
 * DB based on input from the user.
 *  
 * @author anandprakash
 *
 */
@Controller
@RequestMapping("/record.htm")
public class AerospikeRecordController {
	
	RecordDataValidator recordDataValidator;
	private RestTemplate restTemplate;
	private AerospikeDBService  aerospikeDBService;


	@Autowired
	public AerospikeRecordController(RecordDataValidator recordDataValidator){
		this.recordDataValidator = recordDataValidator;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String recordSubmit(@ModelAttribute("record") Record record,
			BindingResult result, SessionStatus status,ModelMap model){
		ResponseEntity<String> response = null;
		String loadTime;
		
		if(record.getRecordCount() != null && !record.getRecordCount().isEmpty()){
			try {
				loadTime = aerospikeDBService.loadRecordForPerformanceTest(record);
				
				model.addAttribute("message1","Time taken to load '"+record.getRecordCount()+"' records is: "
									+"  "+loadTime);
				return "LoadTestResult";
			} catch (AerospikeException e) {
				e.printStackTrace();
				model.addAttribute("message1", "DB call Failed with error:\n"
						+ e.getLocalizedMessage());
				return "CreateRecord";
			}
		}
		recordDataValidator.validate(record, result);
		
		if (result.hasErrors()) {
			//if validator failed
			System.out.println("Inside If has errors");
			return "CreateRecord";
		} else {
			System.out.println("Inside else block");
			
			try {
				//AerospikeDBService service = new AerospikeDBService();
				//service.insertRecord(record);
				aerospikeDBService.insertRecord(record);
				Object returnedData = aerospikeDBService.fetchRecord(record);
				//Object returnedData = service.fetchRecord(record);
				model.addAttribute("message","Record saved successfully!");
				List<String> dataList = new ArrayList<String>();
				dataList.add("Record saved successfully!");
				dataList.add(returnedData.toString());
				
				model.addAttribute("message",returnedData.toString());
				return "ShowRecord";
				
			} catch (AerospikeException e) {
				e.printStackTrace();
				model.addAttribute("message", "DB call Failed with error:\n"
						+ e.getLocalizedMessage());
				return "ShowRecord";
			}

		}
		
	}
	
	
	
	public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
	public AerospikeDBService getAerospikeDBService() {
		return aerospikeDBService;
	}

	public void setAerospikeDBService(AerospikeDBService aerospikeDBService) {
		this.aerospikeDBService = aerospikeDBService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model){
		
		com.aerospike.db.model.Record payObj = new com.aerospike.db.model.Record();

		
		payObj.setPrimaryKey("primary key value");
		
		//initilize a hidden value
		payObj.setSecretValue("secret");
		
		//payObj.setPassword("your password");
		
		//command object
		model.addAttribute("record", payObj);
		
		//return form view
		return "CreateRecord";
	}
	
/*	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		
	}*/

}
