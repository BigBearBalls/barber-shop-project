package eu.senla.userservice.specification;

import eu.senla.userservice.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<User> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                firstName == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),
                        "%" + firstName.toLowerCase() + "%");
    }

    public static Specification<User> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                lastName == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),
                        "%" + lastName.toLowerCase() + "%");
    }
}
