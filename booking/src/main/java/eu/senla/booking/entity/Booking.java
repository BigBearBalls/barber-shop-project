package eu.senla.booking.entity;

import eu.senla.booking.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private MasterTimeTable masterTimeTable;

    private UUID userId;

    private Long procedureId;

    @Enumerated(EnumType.STRING)
    private Status status;
}
