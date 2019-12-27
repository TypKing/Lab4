public class Die extends RuntimeException {
    Die(String place){
        super("В " + place + " же есть люди! Какой газ?");
    }
}
