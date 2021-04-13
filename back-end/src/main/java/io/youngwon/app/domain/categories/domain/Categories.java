package io.youngwon.app.domain.categories.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.products.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Categories extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    @Getter
    @Setter
    private Categories parent;


//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categories")
//    private List<Product> products = new ArrayList<Product>();


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Setter
    private List<Categories> children;



    public Categories(Long id) {
        this.id = id;
    }

    @Builder
    public Categories(String title, Categories parent) {
        this.title = title;
        this.parent = parent;
    }

    public void update(String title){
        this.title = title;
    }
}
