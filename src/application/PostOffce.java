package application;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by liuda on 2016/10/6.
 */

// 使用enum的责任链

class Mail {
    enum GeneralDelivery {YES, NO1, NO2, NO3}

    enum Scannability {UNSCANNABLE, YES1, YES2, YES3}

    enum Readability {ILLEGIBLE, YES1, YES2}

    enum Address {INCORRECT, OK1, OK2}

    enum ReturnAddress {MISSING, OK1, OK2, OK3}

    GeneralDelivery generalDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAddress returnAddress;
    static long counter = 0;
    long id = counter++;

    public String toString() {
        return "Mail " + id;
    }

    public String details() {
        return toString() +
                ",General Delivery : " + generalDelivery +
                ", Address Scanability: " + scannability +
                ",Address Readability:" + readability +
                ",Address address:" + address +
                ",Return address: " + returnAddress;
    }

    public static Mail randomMail() {
        Mail m = new Mail();
        m.generalDelivery = Enums.random(GeneralDelivery.class);
        m.scannability = Enums.random(Scannability.class);
        m.readability = Enums.random(Readability.class);
        m.address = Enums.random(Address.class);
        m.returnAddress = Enums.random(ReturnAddress.class);
        return m;
    }

    public static Iterable<Mail> generator(final int count) {
        return new Iterable<Mail>() {
            int n = count;

            @Override
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    @Override
                    public boolean hasNext() {
                        return n-- > 0;
                    }

                    @Override
                    public Mail next() {
                        return randomMail();
                    }

                    @Override
                    public void remove() {
                        //不实现
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}

public class PostOffce {
    enum MailHandler {
        GENERAL_DELIVERY {  //送货

            boolean handle(Mail m) {
                switch (m.generalDelivery) {
                    case YES:
                        System.out.println("Using general delivery for " + m);
                        return true;
                    default:
                        return false;
                }
            }
        },
        MACHINE_SCAN {  //机器扫描

            boolean handle(Mail m) {
                switch (m.scannability) {
                    case UNSCANNABLE:
                        return false;
                    default:
                        System.out.println("Delivering " + m + " automatically");
                        return true;
                }
            }
        },
        VISUAL_INSPECTION{
            boolean handle(Mail m) {
                switch (m.readability) {
                    case ILLEGIBLE:return false;
                    default:
                        System.out.println("Delivering " + m + "normally");
                        return true;

                }
            }
        },
        REURN_SENDER{
            boolean handle(Mail m) {
                switch (m.returnAddress) {
                    case MISSING:return false;
                    default:
                        System.out.println("Returning" + m + "to sender");
                        return true;

                }
            }
        };
        abstract  boolean handle(Mail m);
    }
    static void handle(Mail m){
        for(MailHandler handler : MailHandler.values()){
            if(handler.handle(m))
                return;
        }
    }
    public static void main(String[] args){
        for(Mail mail : Mail.generator(10)){
            System.out.println(mail.details());
            handle(mail);
            System.out.println("------------");
        }
    }

}

class Enums {
    private static Random rand = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}

/*
职责由 enum MailHandler实现，而enum定义的次序决定了各个解决策略在应用时的次序
对每一封邮件，都要按此顺序尝试每个解决策略，直到其中一个能够成功处理该邮件，如果所有的策略都失败了，那么
该邮件可判定为一封死信息。
 */