package hu.elte.CraftingGame.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Element extends BaseEntity implements Serializable {

    @Column
    @NotNull
    private String elementName;
    
    @Column
    private Element firstParent;

    @Column
    private Element secondParent;
}
