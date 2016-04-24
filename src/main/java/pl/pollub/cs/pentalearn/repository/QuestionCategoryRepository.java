package pl.pollub.cs.pentalearn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.cs.pentalearn.domain.QuestionCategory;

/**
 * Created by pglg on 24-04-2016.
 */
@Repository
public interface QuestionCategoryRepository extends CrudRepository<QuestionCategory,Long>{
}
