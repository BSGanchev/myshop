package shop.springbootapp.service;

import shop.springbootapp.model.entity.Picture;

import java.util.UUID;

public interface PictureService {

    Picture getPictureDataById(UUID id);
}
