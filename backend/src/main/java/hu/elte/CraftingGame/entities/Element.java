package hu.elte.CraftingGame.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Element extends BaseEntity implements Serializable {

    @Column
    @NotNull
    private String elementName;
    
    @Column
    private Integer firstParent;

    @Column
    private Integer secondParent;

    @Column( length = 100000 )
    private String base64Icon;

    @ManyToMany
    @JoinColumn
    @JsonIgnore
    private List<User> users;


}
