package store.management.storemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@CompoundIndexes({
    @CompoundIndex(name = "fname_lname_username_status_index", def="{'fname': 1, 'lname': 1, 'username': 1, 'status': 1}")
})
public class User {
    @Id
    private ObjectId id;
    private String idString;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String firstName;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String lastName;

    private String password;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String username;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String status;

    private Integer roleId;
}