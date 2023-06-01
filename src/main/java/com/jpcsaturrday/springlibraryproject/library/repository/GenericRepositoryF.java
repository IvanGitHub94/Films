package com.jpcsaturrday.springlibraryproject.library.repository;

import com.jpcsaturrday.springlibraryproject.library.model.GenericModelF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Абстрактный репозиторий
 * Необходим для работы абстрактного сервиса,
 * т.к. в абстрактном сервисе мы не можем использовать конкретный репозиторий,
 * а должны указывать параметризованный (GenericRepositoryF)
 *
 * @param <T> - Сущность с которой работает репозиторий
 */
@NoRepositoryBean
public interface GenericRepositoryF<T extends GenericModelF>
        extends JpaRepository<T, Long> {
}
