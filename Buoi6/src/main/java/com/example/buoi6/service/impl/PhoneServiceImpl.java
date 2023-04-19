package com.example.buoi6.service.impl;

import com.example.buoi6.dto.PhoneDTO;
import com.example.buoi6.entity.Phone;
import com.example.buoi6.exception.InternalServerException;
import com.example.buoi6.exception.NotFoundException;
import com.example.buoi6.repository.PhoneRepository;
import com.example.buoi6.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public Phone findPhoneById(Long id) {
        return phoneRepository.findById(id).orElseThrow(() -> {throw new NotFoundException("Not Found Phone has ID: " + id);});
    }

    @Override
    public void updatePhone(Long id, PhoneDTO phoneDTO) {
        Phone phoneFind = this.findPhoneById(id);
        try {
            phoneRepository.updatePhone(id, phoneDTO.getName(), phoneDTO.getBrand(), phoneDTO.getStudent_id());
        } catch (Exception e) {
            throw new InternalServerException("Data error updating phone");
        }
    }
}
