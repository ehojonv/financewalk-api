package br.com.fiap.financewalk.filter;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.financewalk.model.Transaction;
import br.com.fiap.financewalk.model.TransactionFilters;

public class TransactionSpecification {

    public static Specification<Transaction> build(TransactionFilters filters) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (filters.description() != null) {
                var predicateDescription = cb.like(
                        cb.upper(root.get("description")),
                        "%" + filters.description().toUpperCase() + "%");

                predicates = cb.and(predicates, predicateDescription);
            }

            if (filters.startDate() != null) {
                var predicateStartDate = cb.greaterThanOrEqualTo(
                        root.get("date"),
                        filters.startDate());
                predicates = cb.and(predicates, predicateStartDate);
            }

            if (filters.endDate() != null) {
                var predicateEndDate = cb.lessThanOrEqualTo(
                        root.get("date"),
                        filters.endDate());
                predicates = cb.and(predicates, predicateEndDate);
            }

            if (filters.minAmount() != null) {
                var predicateMinAmount = cb.lessThanOrEqualTo(root.get("amount"), filters.minAmount());

                predicates = cb.and(predicates, predicateMinAmount);

            }

            if (filters.maxAmount() != null) {
                var predicateMaxAmount = cb.lessThanOrEqualTo(root.get("amount"), filters.maxAmount());

                predicates = cb.and(predicates, predicateMaxAmount);

            }

            if (filters.categoryId() != null) {
                var predicateCategory = cb.equal(root.get("category").get("id"), filters.categoryId());
                predicates = cb.and(predicates, predicateCategory);
            }

            return predicates;
        };
    }

}
