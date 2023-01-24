package filemanager.utils.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('User')")
@Target(ElementType.METHOD)
public @interface AllowUser {
}
