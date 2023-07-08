package com.vallabha.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vallabha.bindings.CitizenData;
import com.vallabha.entity.CitizenAppEntity;
import com.vallabha.repo.CitizenAppRepository;

@Service
public class CitizenAppServiceImpl implements CitizenAppService {

	@Autowired
	private CitizenAppRepository citizenAppRepo;

	@Override
	public Integer createApp(CitizenData citizenData) {
		Long ssn = citizenData.getSsn();

		// Make Rest-call to SSN-web api to verify citizen state information.
		String url = "https://ssn-web-api.herokuapp.com/ssn/{ssn}";

		// Using RestTemplate class we r trying to make a req.
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class, ssn);
		String stateName = entity.getBody();

		if (stateName.equalsIgnoreCase("New Jersey")) {
			// Create Application
			CitizenAppEntity citizenAppEntity = new CitizenAppEntity();
			BeanUtils.copyProperties(citizenData, citizenAppEntity);
			citizenAppEntity.setStateName(stateName);
			CitizenAppEntity save = citizenAppRepo.save(citizenAppEntity);
			return save.getAppId();
		}
		return 0;
	}

}
