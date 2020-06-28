package com.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.test.model.Farm;
import com.test.web.dto.FarmDTO;

@Component
public class FarmToFarmDTO implements Converter<Farm, FarmDTO> {

	@Override
	public FarmDTO convert(Farm source) {

		if(source == null) return null;
		
		FarmDTO dto = new FarmDTO();
		dto.setId(source.getId());
		dto.setName(source.getName());
		dto.setAccountId(source.getAccount().getId());
		
		return dto;
	}
	
	public List<FarmDTO> convert(List<Farm> source){
		List<FarmDTO> converted = new ArrayList<>();
		
		for (Farm farm : source) {
			converted.add(convert(farm));
		}
		
		return converted;
	}

}
