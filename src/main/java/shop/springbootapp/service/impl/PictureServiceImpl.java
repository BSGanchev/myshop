package shop.springbootapp.service.impl;

import org.springframework.stereotype.Service;
import shop.springbootapp.model.entity.Picture;
import shop.springbootapp.repository.PictureRepository;
import shop.springbootapp.service.PictureService;

import java.util.UUID;
@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Picture getPictureDataById(UUID id) {
        return this.pictureRepository.findById(id).get();
    }
}
