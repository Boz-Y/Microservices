package com.example.shared.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
public class GenericEntity {

    String name;
}
