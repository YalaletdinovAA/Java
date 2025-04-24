package ru.innopolis.blog.services.impl;

import ru.innopolis.blog.models.Television;
import ru.innopolis.blog.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    private final TelevisionRepository televisionRepository;

    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    public List<Television> getAllTelevisions() {
        return televisionRepository.findAll();
    }

    public Optional<Television> getTelevisionById(Long id) {
        return televisionRepository.findById(id);
    }

    public Television saveTelevision(Television television) {
        return televisionRepository.save(television);
    }

    public Television updateTelevision(Long id, Television televisionDetails) {
        Television television = televisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Телевизор не найден с id: " + id));

        television.setBrand(televisionDetails.getBrand());
        television.setModel(televisionDetails.getModel());
        television.setPrice(televisionDetails.getPrice());
        television.setDiagonal(televisionDetails.getDiagonal());
        television.setResolution(televisionDetails.getResolution());
        television.setSmartTv(televisionDetails.isSmartTv());

        return televisionRepository.save(television);
    }

    public void deleteTelevision(Long id) {
        Television television = televisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Телевизор не найден с id: " + id));
        televisionRepository.delete(television);
    }
}