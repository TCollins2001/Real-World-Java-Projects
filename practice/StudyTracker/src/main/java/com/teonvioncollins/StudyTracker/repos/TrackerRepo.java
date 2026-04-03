package com.teonvioncollins.StudyTracker.repos;
import com.teonvioncollins.StudyTracker.models.TrackerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackerRepo extends JpaRepository<TrackerModel, Long> {
}
