package com.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.Farm;
import com.test.service.FarmService;
import com.test.support.FarmDtoToFarm;
import com.test.support.FarmToFarmDTO;
import com.test.web.dto.FarmDTO;

@RestController
@RequestMapping(value = "/api/farms")
public class ApiFarmController {

	@Autowired
	private FarmService farmService;
	@Autowired
	private FarmToFarmDTO toDto;
	@Autowired
	private FarmDtoToFarm toUser;

	// get all
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<FarmDTO>> getAll() {

		List<Farm> farms = farmService.findAll();

		return new ResponseEntity<>(toDto.convert(farms), HttpStatus.OK);
	}

	// get one
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<FarmDTO> getOne(@PathVariable Long id) {

		Farm farm = farmService.findOne(id);
		if (farm == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDto.convert(farm), HttpStatus.OK);
	}

	// add
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<FarmDTO> add(@RequestBody FarmDTO toSave) {

		Farm saved = farmService.save(toUser.convert(toSave));

		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
	}

	// delete
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<FarmDTO> delete(@PathVariable Long id) {

		Farm deleted = farmService.delete(id);

		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDto.convert(deleted), HttpStatus.NO_CONTENT);
	}

	// edit
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	ResponseEntity<FarmDTO> edit(@PathVariable Long id, @RequestBody FarmDTO toEdit) {

		if (id != toEdit.getId())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Farm edited = farmService.save(toUser.convert(toEdit));

		return new ResponseEntity<>(toDto.convert(edited), HttpStatus.OK);
	}
}
