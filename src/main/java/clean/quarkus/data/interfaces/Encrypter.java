package clean.quarkus.data.interfaces;

public interface Encrypter {
    String hash(String plain);
}
