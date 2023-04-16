package store.management.storemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@CompoundIndexes({
    @CompoundIndex(name = "name_category_price_index", def="{'name':1, 'categoryId':1, 'price':1}")
})
public class Product{
    @Id
    @Indexed
    private ObjectId id;
    @Indexed
    private String name;
    private String image;
    private int quantity;
    @Indexed
    private int price;
    @Indexed
    private Integer categoryId;

    public Product(String name, String image, int quantity, int price, Integer categoryId){
        this.id = new ObjectId();
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.categoryId = categoryId;
    }

    public String getIdString() {
        return this.id.toString();
    }
}
