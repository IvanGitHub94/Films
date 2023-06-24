package com.jpcsaturrday.springlibraryproject.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "orders_seq", allocationSize = 1)
public class Orders extends GenericModelF {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_ORDERS_USER"))
    private UserF user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "film_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_ORDERS_FILM"))
    private Film film;

    @Column(name = "rent_date", nullable = false)
    private LocalDateTime rentDate;

    //поле автоматически должно рассчитываться из rent_date + rent_perion
    @Column(name = "return_date", nullable = false)
    private LocalDateTime returnDate;

    //Количество дней аренды, по умолчанию 14
    @Column(name = "rent_period", nullable = false)
    private Integer rentPeriod;

    @Column(name = "returned", nullable = false)
    private Boolean returned;
}

/*import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "orders_seq", allocationSize = 1)
public class Orders extends GenericModelF {

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_ORDERS_USER"))
    private UserF user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "film_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_ORDERS_FILM"))
    private Film film;

    @Column(name = "rent_date", nullable = false)
    private LocalDateTime rentDate;

    @Column(name = "rent_period", nullable = false)
    private Integer rentPeriod;

    @Column(name = "purchase", nullable = false)
    private Boolean purchase;

}*/
