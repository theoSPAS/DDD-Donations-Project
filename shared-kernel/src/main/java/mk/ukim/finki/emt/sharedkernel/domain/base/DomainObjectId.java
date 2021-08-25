package mk.ukim.finki.emt.sharedkernel.domain.base;

import lombok.Getter;
import org.springframework.lang.NonNull;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@Embeddable
@Getter
public class DomainObjectId implements Serializable {

    private String id;

    public DomainObjectId(){}

    protected DomainObjectId(@NonNull String uuid) {
        this.id = Objects.requireNonNull(uuid, "uuid must not be null");
    }

    @NonNull
    public static <ID extends DomainObjectId> ID randomId(@NonNull Class<ID> idClass) {
        Objects.requireNonNull(idClass, "Id-Class can't be null");
        try {
            return idClass.getConstructor(String.class).newInstance(UUID.randomUUID().toString());
        } catch (Exception ex) {
            throw new RuntimeException("Can't create new instance of " + idClass, ex);
        }
    }


}
