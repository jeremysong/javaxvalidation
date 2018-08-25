package org.jeremy;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class ContainsStringsValidator implements ConstraintValidator<ContainsStrings, Collection<String>> {

    private Set<String> values;

    @Override
    public void initialize(ContainsStrings constraintAnnotation) {
        values = Sets.newHashSet(constraintAnnotation.values());
    }

    @Override
    public boolean isValid(Collection<String> collection, ConstraintValidatorContext constraintValidatorContext) {
        Set<String> missingValue = values.stream().filter(v -> !collection.contains(v)).collect(Collectors.toSet());

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

