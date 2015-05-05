package com.aerospike.db.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
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
import com.aerospike.db.model.Credential;
import com.aerospike.db.validator.FormInputValidator;

/**
 * This is a controller class of spring mvc which is exposed as a REST API and used to login to 
 * the application based on username/password authentication via lua module after opening a DB connection.
 * 
 * @author anandprakash
 *
 */
@Controller
@RequestMapping("/credential.htm")
public class AerospikeDBController {
	
	FormInputValidator formInputValidator;
	private RestTemplate restTemplate;
	private AerospikeDBService  aerospikeDBService;

	private static Logger log = Logger.getLogger(AerospikeDBController.class);

	@Autowired
	public AerospikeDBController(FormInputValidator formInputValidator){
		this.formInputValidator = formInputValidator;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String loginSubmit(@ModelAttribute("credential") Credential credential,
			BindingResult result, SessionStatus status,ModelMap model){
		ResponseEntity<String> response = null;
		
		formInputValidator.validate(credential, result);
		
		if (result.hasErrors()) {
			//if validator failed
			return "UserLoginPage";
		} else {
			// response = restAPICall(paymentService);
			//response = restTemplate.getForEntity(new URI(), new Object());

			//test oauth
			Object resp = null;
			try {
				aerospikeDBService.createAeroDBConnection(credential.getHostName(), credential.getPort());
				resp = aerospikeDBService.authenticateUser(credential);
				
				if(resp != null){
					status.setComplete();
					model.addAttribute("message", ""
							+ resp);
					System.out.println("****Response from the server: "+model.get("message"));
					return "CreateRecord";
				}else{
					model.addAttribute("message","Wrong username/password");
					return "paymentresponse";
				}
				
			} catch (AerospikeException e) {
				log.error("Exception in loginSubmit while making DB call:::: "+e.getLocalizedMessage());
				//System.out.println("Exception in loginSubmit while making DB call:::: "+e.getLocalizedMessage());
				e.printStackTrace();
				model.addAttribute("message", "DB call Failed with error:\n"
						+ e.getLocalizedMessage());
				return "paymentresponse";
			}

		}
		
	}
	
	public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
	public void setAerospikeDBService(AerospikeDBService aerospikeDBService) {
		this.aerospikeDBService = aerospikeDBService;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model){
		
		Credential payObj = new Credential();

		
		payObj.setUsername("your username");
		
		//initilize a hidden value
		payObj.setSecretValue("secret");
		
		//payObj.setPassword("your password");
		
		//command object
		model.addAttribute("credential", payObj);
		
		//return form view
		return "UserLoginPage";
	}

}
