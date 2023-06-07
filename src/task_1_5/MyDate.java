package task_1_5;

public class MyDate {
    /* Реализовать класс Дата (операции +/- n дней/месяцев/лет, сравнение дат,
    форматирование даты по шаблону, разбор даты из строки и т.п.). Дата внутри хранится в
    виде целого числа - кол-во дней с начала летоисчисления (01.01.0001)*/
    public int days = 0;
    private final int[] months = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int[] daysBeforeMonth = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};

    public MyDate(int day, int month, int year) {
        this.days += day - 1;
        this.days += monthToDays(month, year);
        this.days += yearsToDays(year);
    }

    public MyDate() {
        this(1, 1, 1);
    }

    public String format(String toFormat) {
        /*
         * "%YY" - year
         * "%MM" - month
         * "%DD" - day
         * */
        int year = daysToYear();
        int month = daysToMonth(year) + 1;
        int day = this.days - 365 * (year - 1) - countVisocos(year) - daysBeforeMonth[daysToMonth(year)] + 1 - (isVisocos(year) && (month - 1) > 1 ? 1 : 0);

        String formated = toFormat.replace("%YY", Integer.toString(year));
        formated = formated.replace("%MM", (month < 10 ? '0' : "") + Integer.toString(month));
        formated = formated.replace("%DD", (day < 10 ? '0' : "") + Integer.toString(day));

        return formated;
    }

    public String format() {
        return this.format("%DD.%MM.%YY");
    }

    public boolean isVisocos(int year) {
        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        if (year % 4 == 0) return true;
        return false;
    }

    public int countVisocos(int year) {
        year--; // кол-во уже прошедших годов
        int count;
        count = year / 4;
        count -= year / 100;
        count += year / 400;
        return count;
    }

    public int monthToDays(int month, int year) {
        month--; // месяцев уже прошло
        int days = daysBeforeMonth[month];
        if (month >= 2 && isVisocos(year)) days++; // День в феврале
        return days;
    }

    public int daysToMonth(int year) {
        int daysOfYear = this.days - (year - 1) * 365 - countVisocos(year);
        int monthNum = 0;
        for (; monthNum < 13 && daysOfYear >= daysBeforeMonth[monthNum] + (isVisocos(year) && monthNum > 1 ? 1 : 0); monthNum++)
            ;
        monthNum--;
        return monthNum;
    }

    public int daysToYear() {
        int year = 1, days = this.days;
        while (days >= 365) {
            if (isVisocos(year)) {
                if (days == 365) break;
                days -= 366;
                year++;
            } else {
                days -= 365;
                year++;
            }
        }
        return year;
    }

    public int yearsToDays(int year) {
        return 365 * (year - 1) + countVisocos(year);
    }

    public void add(MyDate other) {
        this.days += other.days;
    }

    public void sub(MyDate other) {
        if(compareTo(other) < 0) throw new RuntimeException("Дата меньше вычитаемой");
        this.days -= other.days;
    }

    public static MyDate fromString(String stringDate, String separator) {
        // "day<sep>month<sep>year"
        String[] dateSplit = stringDate.split(separator);
        int day = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int year = Integer.parseInt(dateSplit[2]);
        return new MyDate(day, month, year);
    }

    public int compareTo(MyDate other) {
        return Integer.compare(this.days, other.days);
    }
}
