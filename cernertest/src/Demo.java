import model.Person;
import service.CardServiceImpl;

import java.util.HashMap;

public class Demo {
    public static void main(String[] args) throws Exception {

        HashMap<String, Person> cards = new HashMap<>();
        CardServiceImpl service = new CardServiceImpl(cards);

        Person kartik = new Person("5", "11", "12");
        Person pramod = new Person("11", "13", "14");

        Person raghu = new Person("13", "15", "16");
        Person budhi = new Person("14", "17", "18");

        Person babula = new Person("50", "51", "52");
        Person jhumuri = new Person("60", "71", "72");
        Person dasa = new Person("51", "13", "14");

        Person liza = new Person("100", "501", "502");
        Person lopa = new Person("101", "501", "502");

        service.saveCard(kartik);
        service.saveCard(pramod);
        service.saveCard(raghu);
        service.saveCard(budhi);
        service.saveCard(babula);
        service.saveCard(jhumuri);
        service.saveCard(dasa);
        service.saveCard(lopa);
        service.saveCard(liza);

//        System.out.println("Blood Related ? " + service.areBloodRelated(kartik, kartik));
        System.out.println("Blood Related ? " + service.areBloodRelated(kartik, liza));
//        System.out.println("Blood Related ? " + service.areBloodRelated(kartik, babula));
//        System.out.println("Blood Related ? " + service.areBloodRelated(kartik, dasa));
//        System.out.println("Blood Related ? " + service.areBloodRelated(kartik, jhumuri));

    }
}
