package hu.elte.CraftingGame.entities;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User extends BaseEntity implements Serializable {
	
    @Column(nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<Element> elements;
    
    public enum Role {
        ROLE_GUEST, ROLE_USER, ROLE_ADMIN
    }
}
