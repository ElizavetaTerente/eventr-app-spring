package at.qe.skeleton.repositories;

import at.qe.skeleton.model.Tag;

public interface TagRepository extends AbstractRepository<Tag,String>{
    Tag findByName (String name);
}
