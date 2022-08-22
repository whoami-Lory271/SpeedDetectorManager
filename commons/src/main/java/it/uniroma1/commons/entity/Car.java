package it.uniroma1.commons.entity;

import it.uniroma1.commons.queue.enums.CarType;
import it.uniroma1.commons.queue.enums.FuelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "speed_cameras")
public class Car {
    @Id
    private String licensePlate;

    @Column(name = "car_type")
    private CarType carType;

    @Column(name = "fuel_type")
    private FuelType fuelType;

    @Column(name = "registration_date")
    private Date registrationDate;

    @OneToMany(mappedBy = "speedCamera")
    private List<Fine> fines;

    @Column(name = "road_type")
    private String roadType;
}