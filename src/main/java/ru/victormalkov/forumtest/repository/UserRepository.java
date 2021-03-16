package ru.victormalkov.forumtest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.victormalkov.forumtest.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User getUserByName(String name);
}
