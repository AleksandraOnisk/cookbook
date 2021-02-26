package pl.olita.cookbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByCategoryOrderByTitleAsc(Category category);

    List<Recipe> findTop3ByOrderByLikesDesc();

    @Query("UPDATE Recipe recipe SET recipe.likes = recipe.likes + 1 WHERE recipe.id = :id")
    @Modifying
    @Transactional
    public void addLike(@Param("id") Long id);
}
