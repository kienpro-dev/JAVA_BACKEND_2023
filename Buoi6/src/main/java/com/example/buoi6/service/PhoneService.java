package com.example.buoi6.service;

import com.example.buoi6.dto.PhoneDTO;
import com.example.buoi6.entity.Phone;
import org.springframework.stereotype.Service;

@Service
public interface PhoneService {
    Phone findPhoneById(Long id);

    void updatePhone(Long id, PhoneDTO phoneDTO);
}
