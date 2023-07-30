package local.loeches.webkey.models;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class Enterprise {

    private @Id @GeneratedValue Long id;
    private String name;
    private String projectLeader;


    Enterprise(String name, String projectLeader)
    {

        this.name = name;
        this.projectLeader = projectLeader;

    }

}
