package com.project.animalface_web.repository.mongoRepository;

import com.project.animalface_web.domain.mongoEntity.ProfileImage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileImageRepository extends MongoRepository<ProfileImage, String> {
}
