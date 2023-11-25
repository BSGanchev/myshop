package shop.springbootapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import java.util.Objects;

public class PasswordMatchValidator implements ConstraintValidator<PasswordValidator, Object> {
    private String first;
    private String second;
    private String message;

    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        this.first = constraintAnnotation.password();
        this.second = constraintAnnotation.confirmPassword();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
        Object password = beanWrapper.getPropertyValue(first);
        Object confirmPassword = beanWrapper.getPropertyValue(second);

        boolean isEquals = Objects.toString(password).equals(Objects.toString(confirmPassword));
        if (!isEquals) {
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(second)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return isEquals;
    }
}
