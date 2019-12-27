public class PoliceException extends Exception{
    PoliceException(Police police){
        super(police.getName() + " попал в тюрячку. Не повезло парню, не фортануло... Ладно, у нас они неприкосновенны, " +
                "их нельзя закидывать в тюрячку!");
    }
}
