package pl.olita.cookbook;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByCategoryOrderByTitleAsc(Category category);

    List<Recipe> findTop3ByOrderByLikesDesc();
}
