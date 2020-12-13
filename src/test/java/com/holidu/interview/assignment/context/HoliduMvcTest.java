package com.holidu.interview.assignment.context;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.TestPropertySource;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(TestMvcConfig.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.main.banner-mode=off")
public @interface HoliduMvcTest {
	@AliasFor(annotation = SpringBootTest.class, attribute = "classes")
	Class<?>[] value() default {};

	@AliasFor(annotation = SpringBootTest.class, attribute = "classes")
	Class<?>[] classes() default {};

	@AliasFor(annotation = AutoConfigureMockMvc.class, attribute = "secure")
	boolean secure() default false;
}
