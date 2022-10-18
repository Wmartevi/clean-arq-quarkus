package clean.quarkus.infra.util;

import clean.quarkus.data.interfaces.Encrypter;
import jodd.crypt.BCrypt;

public class BCryptAdapter implements Encrypter {

    @Override
    public String hash(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt());
    }
}
