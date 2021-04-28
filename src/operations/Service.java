package operations;

import java.util.UUID;

public interface Service {

    default String genID(){
        return UUID.randomUUID().toString();
    }

    String getId();
}
