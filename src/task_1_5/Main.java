package task_1_5;

public class Main {
    public static void main(String[] args) {
        MyDate date = new MyDate(29,2,2100);
        for (int year = 400; year <= 1000; year+=40) {
            date = new MyDate(29, 2, year);
            System.out.println(date.format("%DD.%MM of %YY year"));
        }
        System.out.println();
        System.out.println();

        String tmp = "32.12.2004";
        System.out.println("Из строки " + tmp + ":");
        MyDate fromStr = MyDate.fromString(tmp, "\\.");
        System.out.println(fromStr.format());

        System.out.println("Добавление");
        System.out.println(date.format());
        date.add(fromStr);
        System.out.println(date.format());

        System.out.println("Сравнение + вычитание");
        System.out.println(date.format());
        System.out.println(fromStr.format());
        System.out.println(date.compareTo(fromStr));
        date.sub(fromStr);
        System.out.println(date.format());


    }
}
