package store.management.storemanagement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Data

public class Category {
    @Id
    private String id;
    private String name;
    private Integer intId;
}
