package org.jeremy;

import com.google.common.collect.ImmutableSet;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class ContainsIntValidator implements ConstraintValidator<ContainsInts, Collection<Integer>> {

    private Set<Integer> values;

    @Override
    public void initialize(ContainsInts constraintAnnotation) {
        values = Arrays.stream(constraintAnnotation.values()).boxed().collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(Collection<Integer> collection, ConstraintValidatorContext constraintValidatorContext) {
        Set<Integer> missingValue = values.stream().filter(v -> !collection.contains(v)).collect(Collectors.toSet());

        if (missingValue.isEmpty()) {
            return true;
        }

        if (constraintValidatorContext instanceof HibernateConstraintValidatorContext) {
            constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class)
                    .addMessageParameter("missingValues", missingValue.stream().map(String::valueOf).collect(Collectors.joining(",")))
                    .withDynamicPayload(ImmutableSet.copyOf(missingValue));
        }

        return false;
    }
}

