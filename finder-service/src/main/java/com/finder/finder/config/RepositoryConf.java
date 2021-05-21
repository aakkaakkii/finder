package com.finder.finder.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
        value = "com.finder.finder.adapter.persistence.repository",
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.finder.finder.adapter.persistence.repository.search.*"))
public class RepositoryConf {
}
