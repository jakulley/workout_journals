package workout.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import workout.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {

}

