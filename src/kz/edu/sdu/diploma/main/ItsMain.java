package kz.edu.sdu.diploma.main;

import java.util.List;
import java.util.Map;

import kz.edu.sdu.diploma.divisor.Divisor;
import kz.edu.sdu.diploma.domain.Syllable;
import kz.edu.sdu.diploma.domain.Text;
import kz.edu.sdu.diploma.domain.Word;

public class ItsMain {

	public static void main(String[] args) {
		
		Divisor divisor = new Divisor();
//		String text = "Рахымжанов";
		String text = "Өкінішке    орай көркем әдебиетке деген өз бетімен балалардың қызығушылығы азайды. Әдебиетке деген құлықсыздық пайда болды. Оның себептерін күнделікті қолданысқа енген ғалам торабын, компьютер секілді құралдардың әсері деп білеміз. Бұдан соңғы себеп, аттаған әр қадамыңыз ақша керек болатын күнделікті тіршілік қамы адамдардың кітапқа деген ынтасын төмендетіп жіберетіндігі. Бала кітап оқып, ойын қорытудың орнына теледидардан дайын өнімдерді көруге бейім. Мұның барлығы балалардың кітап оқуына үлкен кедергі келтіреді. Ал ата – аналар тым жұмысбасты. Баланың теледидар қарауын белгілі бір уақытқа ғана шектеп, кітап оқуына арнайы көңіл бөліп, қадағалап отыруға олар уақыт таппайды. Әрине, мұны жауап деуге де келе қоймас, өйткені, бұл себеп көзіқарақты адамның бәріне мәлім. Қоғамымыздың рухани әлемін жұтаңдатып әкетіп бара жатқан осы мәселеге ден қоюымыз да тегін емес. Мақсатымыз – кітап неге оқылмайды деген сауалдың жауабын жан – жақты іздеу.";
		Text itext = new Text(text);
		
		divisor.setText(itext);
		divisor.yReplacement();
		divisor.divideBySyllables();
		
		for(Word w : divisor.getText().getWordList()) {
			List<Syllable> syllableList = w.getSyllableList();
			for(int i = 0; i < syllableList.size(); i++) {
				System.out.print(syllableList.get(i).getChunk()+((i != syllableList.size()-1)?"-":""));
			}
			System.out.print(" ");
		}
		
		Map<Syllable, Word> syllableMap = divisor.getSyllableMap();
		
		for(Syllable syllable : syllableMap.keySet()) {
			System.out.println(syllable.getChunk() + " <=> [" + syllable.getPosition().toString() + "] -> " + syllableMap.get(syllable).getWord() );
		}
	}

}
