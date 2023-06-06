package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TarotDescriptionManager {
    private static final Map<String, String> tarotDescriptions = new HashMap<>();
    private static final String[] tarotCards = {
            "Шут, Чарівник", "Колесо Фортуни, Чотири Монети", "Два Келихів,Король Монет,Королева Чаш", "Сонце, Десять Мечів", "Ворони, Вежа",
            "Художник, Маг", "Король або Королева мечів", "Король або Королева чаш", "Хмари та Місяць", "Король або Королева монет",
            "Маг та Високий Жрець", "Королева Келихів та Діва Монет", "Трансформація та Високий Жрець", "Місяць та Шут", "Чотири Монет та Повітряний Король",
            "Віщун та Дев'ять Мечів", "Маг та Оборона", "Сонце та Високий Жрець", "Два Мечі та Ворожка", "Десять Келихів та Зірка"
    };

    static {

        tarotDescriptions.put("Шут, Чарівник", "Ця комбінація вказує на можливість нових початків, творчий потенціал і можливості змін у вашому житті. Вам необхідно відкритися новим ідеям та використовувати свою інтуїцію для досягнення успіху.");
        tarotDescriptions.put("Колесо Фортуни, Чотири Монети", " Ця комбінація може вказувати на зміну в фінансовій ситуації або вдалий фінансовий виграш. Вона може означати збільшення матеріальних благ і процвітання.");
        tarotDescriptions.put("Два Келихів,Король Монет,Королева Чаш", "Ця комбінація може вказувати на ситуацію, де виникають конфлікти та невирішені почуття у романтичних відносинах. Вона може означати потребу в збалансованості, відкритому спілкуванні та розумінні між всіма сторонами.");
        tarotDescriptions.put("Сонце, Десять Мечів", "Ця комбінація може означати перебороття труднощів, перемогу над перешкодами та відчуття радості. Вона показує, що після складних часів наставає світле майбутнє та нові можливості.");
        tarotDescriptions.put("Ворони, Вежа", "Ця комбінація може вказувати на нестабільність, розпад старих структур або потребу в зміні. Вона може означати необхідність перегляду поглядів, звільнення від обмежень або пошук нових шляхів.");
        tarotDescriptions.put("Художник, Маг", "Ця комбінація вказує на розвиток внутрішньої мудрості, самопізнання та духовного зростання. Вона може означати досягнення глибокого розуміння себе і світу навколо.");
        tarotDescriptions.put("Король або Королева мечів", "Ця комбінація вказує на успішне вирішення проблем або справ у вашому житті. Вона означає активність, розум і прийняття рішень на основі логіки та аналізу.");
        tarotDescriptions.put("Король або Королева чаш", "Ця комбінація вказує на настання романтичних відносин або зближення в дружбі. Вона може означати інтенсивні почуття, гармонію і співчуття в вашому особистому житті.");
        tarotDescriptions.put("Хмари та Місяць", "Ця комбінація вказує на нестабільність або незрозумілість у вашому житті. Вона може означати перешкоди, які потрібно подолати, або певні аспекти, які залишаються прихованими або неясними.");
        tarotDescriptions.put("Король або Королева монет", "Ця комбінація вказує на матеріальні блага, фінансову стабільність і успіх у бізнесі. Вона означає розумні інвестиції, ефективне управління фінансами та здатність досягати фінансових цілей.");
        tarotDescriptions.put("Маг та Високий Жрець", "Ця комбінація вказує на глибоку духовну мудрість та внутрішню силу. Вона може означати потребу в підтримці від вищих сил і використанні своїх дарів для досягнення мети.");
        tarotDescriptions.put("Королева Келихів та Діва Монет", "Ця комбінація вказує на гармонійні та стабільні відносини, які засновані на взаємному розумінні та підтримці. Вона може означати довготривалу і здорову любовну зв'язок або успішне партнерство.");
        tarotDescriptions.put("Трансформація та Високий Жрець", "Ця комбінація вказує на процес глибокої трансформації, духовного зростання та переосмислення життя. Вона може означати потребу відмовитися від старих парадигм і пристосуватися до нових реалій.");
        tarotDescriptions.put("Місяць та Шут", "Ця комбінація вказує на необхідність довіряти своїй інтуїції та відкритися новим можливостям. Вона може означати неочікувані зміни, які вимагають гнучкості і ризику.");
        tarotDescriptions.put("Чотири Монет та Повітряний Король", "Ця комбінація вказує на успіх у фінансах та матеріальну стабільність, що випливає з розумного управління та практичної мудрості.");
        tarotDescriptions.put("Віщун та Дев'ять Мечів", "Ця комбінація вказує на важкі моменти, де може бути почуття розчарування або втрати. Вона може означати необхідність перегляду ситуації та шукати способи примирення зі змінами.");
        tarotDescriptions.put("Маг та Оборона", "Ця комбінація вказує на потужну енергію творчості та необхідність захищати свої ідеї і візію. Вона може означати успіх через виявлення упевненості та відстоювання своїх цінностей.");
        tarotDescriptions.put("Сонце та Високий Жрець", " Ця комбінація вказує на посилення духовного зростання, освіти та пізнання. Вона може означати потребу в пошуку мудрості, наставництві та практиці духовних практик.");
        tarotDescriptions.put("Два Мечі та Ворожка", "Ця комбінація вказує на необхідність зробити важливий вибір або прийняти рішення. Вона може означати дослідження різних можливостей та потребу в уважному аналізі перед прийняттям рішення.");
        tarotDescriptions.put("Десять Келихів та Зірка", "Ця комбінація вказує на почуття глибокої задоволеності, радості та спокою. Вона може означати насолоду життям, реалізацію бажань та позитивну енергію у вашому житті.");

    }

    public static String getRandomTarotCard() {
        Random random = new Random();
        int index = random.nextInt(tarotCards.length);
        return tarotCards[index];
    }

    public static String getTarotDescription(String card) {
        String description = tarotDescriptions.get(card);
        if (description != null) {
            return description;
        } else {
            return "Вибачте,сталася помилка(Росклад таро)";
        }
    }
}