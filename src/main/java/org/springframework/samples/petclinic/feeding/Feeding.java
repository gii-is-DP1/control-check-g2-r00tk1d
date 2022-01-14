package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class Feeding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "start_date")
    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    LocalDate startDate;

    @Column(name = "weeks_duration")
    @NotNull
    @DecimalMin("1.0")
    double weeksDuration;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    @NotNull
    Pet pet;

    @ManyToOne
    @JoinColumn(name = "feeding_type_id")
    FeedingType feedingType;
}
