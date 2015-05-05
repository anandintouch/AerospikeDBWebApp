package com.aerospike.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;

import com.aerospike.client.AerospikeException;
import com.aerospike.db.model.AerospikeDBService;
import com.aerospike.db.model.PerformanceTest;
import com.aerospike.db.validator.RecordDataValidator;

@Controller
@RequestMapping("/performanceTest.htm")
public class AerospikePerformanceTestController {
	
	RecordDataValidator recordDataValidator;
	private RestTemplate restTemplate;
	private AerospikeDBService  aerospikeDBService;

	@Autowired
	public AerospikePerformanceTestController(RecordDataValidator recordDataValidator){
		this.recordDataValidator = recordDataValidator;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String loadTest(@ModelAttribute("performanceTest") PerformanceTest perfTest,
			BindingResult result, SessionStatus status,ModelMap model){
		
		ResponseEntity<String> response = null;
		String loadTime;
		
		if(perfTest.getRecordCount() != null && !perfTest.getRecordCount().isEmpty()){
			try {
				//loadTime = aerospikeDBService.loadRecordForPerformanceTest(perfTest);
				
				model.addAttribute("message1","Time taken to load '"+perfTest.getRecordCount()+"' records is: "
									+"  "+perfTest);
				return "LoadTestResult";
			} catch (AerospikeException e) {
				e.printStackTrace();
				model.addAttribute("message1", "DB call Failed with error:\n"
						+ e.getLocalizedMessage());
				return "LoadTestData";
			}
		}else{
			return "LoadTestData";
		}
		
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
	
}
