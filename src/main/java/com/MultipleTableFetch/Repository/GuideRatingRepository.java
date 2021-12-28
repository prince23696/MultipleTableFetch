package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Entity.GuideRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideRatingRepository extends JpaRepository<GuideRating, Long> {

    @Query(value = "select count(guide_rating_id) from guide_rating ", nativeQuery = true)
    public int countRecords();

}
